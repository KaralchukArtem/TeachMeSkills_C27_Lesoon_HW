package lesson43.service;

import lesson43.model.UserModel;
import lesson43.pack.PostgresDriverManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserService {

    public boolean deleteUser(int id) {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?;");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserModel getUser(int id) {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;
        ResultSet preparedResultSet;
        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedResultSet = preparedStatement.executeQuery();
            if (preparedResultSet.next()) {
                UserModel user = new UserModel();
                user.setId(preparedResultSet.getInt("id"));
                user.setName(preparedResultSet.getString("name"));
                user.setSurname(preparedResultSet.getString("surname"));
                user.setAge(preparedResultSet.getInt("age"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
        return null;
    }

    public boolean createUser(UserModel userModel) {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("INSERT INTO person VALUES (DEFAULT,?,?,?,?);");
            preparedStatement.setString(1, userModel.getName());
            preparedStatement.setString(2, userModel.getSurname());
            preparedStatement.setInt(3, userModel.getAge());
            preparedStatement.setString(4, userModel.getPassport_number());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean changeLogin(String name, int id) {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;
        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("UPDATE person SET name = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
