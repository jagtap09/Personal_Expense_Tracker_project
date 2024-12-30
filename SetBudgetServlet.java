package com.businesslogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SetBudgetServlet")
public class SetBudgetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String monthYear = request.getParameter("monthYear");
        double budgetLimit = Double.parseDouble(request.getParameter("budgetLimit"));
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker", "root", "root");

            // Insert or update budget for the month
            String upsertBudgetQuery = "INSERT INTO budgets (month_year, budget_limit, total_expenses) VALUES (?, ?, 0.0) " +
                                       "ON DUPLICATE KEY UPDATE budget_limit = ?";
            ps = conn.prepareStatement(upsertBudgetQuery);
            ps.setString(1, monthYear);
            ps.setDouble(2, budgetLimit);
            ps.setDouble(3, budgetLimit);
            ps.executeUpdate();

            // Calculate total expenses for the month
            String calculateExpensesQuery = "SELECT SUM(amount) AS total_expenses FROM expenses " +
                                            "WHERE DATE_FORMAT(expense_date, '%b %Y') = ?";
            ps = conn.prepareStatement(calculateExpensesQuery);
            ps.setString(1, monthYear);
            ResultSet rs = ps.executeQuery();

            double totalExpenses = 0.0;
            if (rs.next()) {
                totalExpenses = rs.getDouble("total_expenses");
            }

            // Update total expenses in the budgets table
            String updateExpensesQuery = "UPDATE budgets SET total_expenses = ? WHERE month_year = ?";
            ps = conn.prepareStatement(updateExpensesQuery);
            ps.setDouble(1, totalExpenses);
            ps.setString(2, monthYear);
            ps.executeUpdate();

            // Check if under budget or over budget
            String status = (totalExpenses <= budgetLimit) ? "Under Budget" : "Over Budget";

            out.println("<div style='display: flex; justify-content: center; align-items: center; height: 100vh; text-align: center; flex-direction: column;'>");
            out.println("<h2>Budget Status for " + monthYear + "</h2>");
            out.println("<p>Budget Limit: $" + budgetLimit + "</p>");
            out.println("<p>Total Expenses: $" + totalExpenses + "</p>");
            out.println("<p>Status: <strong>" + status + "</strong></p>");
            out.println("<button style='margin-top: 15px; padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;' onclick=\"location.href='AddExpense.html';\">Add Expense</button>");
            out.println("</div>");

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(out);
        } finally {
            try { if (ps != null) ps.close(); if (conn != null) conn.close(); } catch (Exception ex) {}
        }
    }
}
