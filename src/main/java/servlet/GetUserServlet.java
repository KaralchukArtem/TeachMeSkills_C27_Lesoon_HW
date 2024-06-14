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
import java.sql.ResultSet;

@WebServlet("/get")
public class GetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GetUserServlet");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;
        ResultSet preparedResultSet;
        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE ID = ?");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            preparedResultSet = preparedStatement.executeQuery();
            while (preparedResultSet.next()) {
                req.setAttribute("id", preparedResultSet.getInt("id"));
                req.setAttribute("name", preparedResultSet.getString("name"));
                req.setAttribute("surname", preparedResultSet.getString("surname"));
                req.setAttribute("age", preparedResultSet.getInt("age"));
            }
            getServletContext().getRequestDispatcher("/WEB-INF/infopage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
    }
}
