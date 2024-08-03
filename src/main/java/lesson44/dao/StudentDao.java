package lesson44.dao;

import lesson44.model.StudentModel;
import lesson44.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StudentDao {
    @Autowired
    private StudentsRepository repository;

    public List<StudentModel> getAllStudents() {
        List<StudentModel> list = new ArrayList<>();
        for (Map.Entry<Integer, StudentModel> entry : repository.getUsers().entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
    public StudentModel getStudent(int id) {
        for (Map.Entry<Integer, StudentModel> entry : repository.getIntegerPersonModelMap().entrySet()) {
            if (entry.getValue().getId() == id) {
                return entry.getValue();
            }
        }
        return null;
    }
    public void addStudent(StudentModel student) {
        this.repository.addStudent(student);
    }
    public void deleteStudent(int id) {
        this.repository.deleteStudent(id);
    }
}
