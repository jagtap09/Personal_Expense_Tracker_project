package com.businesslogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name1");
        String email = req.getParameter("email1");
        String mobile = req.getParameter("mobile1");
        String password = req.getParameter("pass1");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");

        // database connection 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker", "root", "root");
            PreparedStatement ps = c.prepareStatement("INSERT INTO register(name, email, mobile_no, password, gender, age) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, password);
            ps.setString(5, gender);
            ps.setString(6, age);

            ps.executeUpdate();
            resp.sendRedirect("Login.html");
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error occurred while registering: " + e.getMessage());
        }
    }
}
