package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pack.PostgresDriverManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CreateUserServlet");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("INSERT INTO person VALUES (?,?,?,?,?);");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            preparedStatement.setString(2, req.getParameter("name"));
            preparedStatement.setString(3, req.getParameter("surname"));
            preparedStatement.setInt(4, Integer.parseInt(req.getParameter("age")));
            preparedStatement.setString(5, req.getParameter("passport_number"));
            preparedStatement.executeUpdate();
            resp.sendRedirect("/");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
