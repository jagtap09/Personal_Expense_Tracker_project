package com.businesslogic;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddExpenseServlet")
public class AddExpenseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String expenseName = req.getParameter("expenseName");
        String amount = req.getParameter("amount");
        String date = req.getParameter("date");
        String category = req.getParameter("category");
        String description = req.getParameter("description");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Basic validation
        if (expenseName.isEmpty() || amount.isEmpty() || date.isEmpty()) {
            out.println("<h3 style='color:red;'>All fields are required!</h3>");
            return;
        }

        // Database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker", "root", "root");

            String sql = "INSERT INTO expenses (id, expense_name, amount,  expense_date, category, description) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, expenseName);
            ps.setDouble(3, Double.parseDouble(amount));
            ps.setString(4, date);
            ps.setString(5, category);
            ps.setString(6, description);

            int result = ps.executeUpdate();
            if (result > 0) {
                resp.sendRedirect("AddExpense.html?success=true");
                
            } else {
                out.println("<h3 style='color:red;'>Failed to add expense.</h3>");
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}
