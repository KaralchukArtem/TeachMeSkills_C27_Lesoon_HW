package lesson44.repository;

import lesson44.model.StudentModel;
import lesson44.pack.PostgresDriverManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class StudentsRepository {
    @Getter
    private Map<Integer, StudentModel> integerPersonModelMap;
    @Autowired
    public PostgresDriverManager postgresDriverManager;
    private int COUNTER = 0;

    public void addStudent(StudentModel student) {
        PreparedStatement preparedStatement;
        try (Connection connection = postgresDriverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("INSERT INTO student VALUES (DEFAULT,?,?,?,?);");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGroup());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void deleteStudent(int id) {
        PreparedStatement preparedStatement;
        try (Connection connection = postgresDriverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Map<Integer, StudentModel> getUsers() {
        PreparedStatement preparedStatement;
        ResultSet preparedResultSet;
        Map<Integer, StudentModel> user = new HashMap<>();
        try (Connection connection = postgresDriverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM student");
            preparedResultSet = preparedStatement.executeQuery();
            StudentModel studentModel;
            while (preparedResultSet.next()) {
                studentModel = new StudentModel();
                studentModel.setName(preparedResultSet.getString("name"));
                studentModel.setSurname(preparedResultSet.getString("surname"));
                studentModel.setGroup(preparedResultSet.getString("class"));
                studentModel.setAge(preparedResultSet.getInt("age"));
                studentModel.setId(preparedResultSet.getInt("id"));
                user.put(++COUNTER, studentModel);
            }
            this.integerPersonModelMap = user;
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
        return null;
    }
}
