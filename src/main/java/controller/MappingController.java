package controller;

import model.UserModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.PostgresDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class MappingController {
    @RequestMapping("")
    public String start() {
        return "/start";
    }

    @PostMapping(path = "/create", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView post(UserModel model) {
        ModelAndView mav = new ModelAndView();
        System.out.println("create");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;

        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("INSERT INTO person VALUES (DEFAULT,?,?,?,?);");
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getSurname());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.setString(4, model.getPassport_number());
            preparedStatement.executeUpdate();
            mav.setViewName("/start");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mav;
    }

    @PostMapping("/delete")
    public ModelAndView post(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();
        System.out.println("delete");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;

        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            mav.setViewName("/start");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mav;
    }

    @PostMapping("/change-login")
    public ModelAndView post(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();
        System.out.println("change-login");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;

        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("UPDATE person SET name = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            mav.setViewName("/start");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mav;
    }

    @GetMapping("/get")
    public ModelAndView get(@RequestParam(value = "id", required = false) Integer id) {

        ModelAndView mav = new ModelAndView();
        System.out.println("get");
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        PreparedStatement preparedStatement;
        ResultSet preparedResultSet;
        try (Connection connection = driverManager.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedResultSet = preparedStatement.executeQuery();
            while (preparedResultSet.next()) {
                mav.addObject("id", preparedResultSet.getInt("id"));
                mav.addObject("name", preparedResultSet.getString("name"));
                mav.addObject("surname", preparedResultSet.getString("surname"));
                mav.addObject("age", preparedResultSet.getInt("age"));
            }
            mav.setViewName("/infopage");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
        return mav;
    }
}
