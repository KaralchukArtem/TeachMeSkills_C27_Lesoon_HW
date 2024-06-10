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

@WebServlet("/change-login")
public class ChangeLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ChangeLoginServlet");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;

        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("UPDATE person SET name = ? WHERE id = ?");
            preparedStatement.setString(1, req.getParameter("name"));
            preparedStatement.setInt(2, Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();
            resp.sendRedirect("/");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
