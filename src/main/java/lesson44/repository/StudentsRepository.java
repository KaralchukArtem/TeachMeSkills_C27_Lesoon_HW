package lesson44.repository;

import lesson44.model.StudentModel;
import lesson44.pack.PostgresDriverManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentsRepository {
    @Getter
    private Map<Integer, StudentModel> integerPersonModelMap;
    @Autowired
    public PostgresDriverManager postgresDriverManager;
    private int COUNTER = 0;

    public StudentsRepository() {
        this.integerPersonModelMap = new HashMap<>();
//        System.out.println(getUsers());
        this.integerPersonModelMap.put(++COUNTER, new StudentModel("Ivan", "Ivanov", "C27-onl", 25, COUNTER));
        this.integerPersonModelMap.put(++COUNTER, new StudentModel("Roman", "Romanov", "C27-onl", 26, COUNTER));
        this.integerPersonModelMap.put(++COUNTER, new StudentModel("Alexander", "Alexandrov", "C27-onl", 27, COUNTER));
        this.integerPersonModelMap.put(++COUNTER, new StudentModel("Petr", "Petrov", "C27-onl", 28, COUNTER));
        this.integerPersonModelMap.put(++COUNTER, new StudentModel("Artem", "Artemov", "C27-onl", 29, COUNTER));
    }

    public void addStudent(StudentModel student) {
        student.setId(++COUNTER);
        this.integerPersonModelMap.put(COUNTER, student);
    }

    public StudentModel getUsers(){
        PreparedStatement preparedStatement;
        ResultSet preparedResultSet;
        try (Connection connection = postgresDriverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE ID = 2");
            preparedResultSet = preparedStatement.executeQuery();
            if (preparedResultSet.next()) {
                StudentModel user = new StudentModel();
                user.setId(preparedResultSet.getInt("id"));
                user.setName(preparedResultSet.getString("name"));
                user.setSurname(preparedResultSet.getString("surname"));
                user.setAge(preparedResultSet.getInt("age"));
                user.setGroup(preparedResultSet.getString("class"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
        return null;
    }
}
