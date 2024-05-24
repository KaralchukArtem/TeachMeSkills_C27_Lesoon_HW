package com.teachmeskills.lesson30.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegisteredServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        if (password.equals("1111")) {
            req.setAttribute("name", name);
            req.setAttribute("pass", password);
            getServletContext().getRequestDispatcher("/WEB-INF/registered.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
