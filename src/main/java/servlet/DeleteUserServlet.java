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

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteUserServlet");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;

        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?;");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();
            resp.sendRedirect("/");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
