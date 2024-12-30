package com.businesslogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginform")
public class LogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email1");
        String password = req.getParameter("pass1");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker", "root", "root");
            PreparedStatement ps = c.prepareStatement("SELECT id FROM register WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("id", rs.getInt("id"));
                resp.sendRedirect("Profile.jsp");
            } else {
                req.setAttribute("errorMessage", "Invalid email or password.");
                req.getRequestDispatcher("Login.html").forward(req, resp);
            }

            rs.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
