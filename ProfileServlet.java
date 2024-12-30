package com.businesslogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // Import SQLException

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            resp.sendRedirect("Login.html");
            return;
        }

        int userId = (int) session.getAttribute("id");

        Connection c = null; // Initialize connection outside try-catch
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker", "root", "root");

            System.out.println("User ID from session: " + userId); // Print user ID for debugging

            String sql = "SELECT name, email, mobile_no, gender, age FROM register WHERE id = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, userId);

            rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("name", rs.getString("name"));
                System.out.println("Setting name attribute: " + rs.getString("name"));
                req.setAttribute("email", rs.getString("email"));
                System.out.println("Setting email attribute: " + rs.getString("email"));
                req.setAttribute("mobile", rs.getString("mobile_no"));
                System.out.println("Setting mobile attribute: " + rs.getString("mobile_no"));
                req.setAttribute("gender", rs.getString("gender"));
                System.out.println("Setting gender attribute: " + rs.getString("gender"));
                req.setAttribute("age", rs.getInt("age"));
                System.out.println("Setting age attribute: " + rs.getInt("age"));

                req.getRequestDispatcher("Profile.jsp").forward(req, resp);
            } else {
                System.err.println("No user found with ID: " + userId); // Log to server logs
                resp.getWriter().write("User not found."); // Or redirect to an error page
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.getWriter().write("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace(); // Very important for debugging SQL issues
            resp.getWriter().write("Database error: " + e.getMessage());
        } finally {
            // Close resources in finally block to ensure they are always closed
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Log closing errors as well
            }
        }
    }
}