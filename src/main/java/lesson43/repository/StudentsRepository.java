package lesson43.repository;

import lesson43.model.StudentModel;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentsRepository {
    @Getter
    private Map<Integer, StudentModel> integerPersonModelMap;
    private int COUNTER = 0;
    private StudentsRepository() {
        this.integerPersonModelMap = new HashMap<>();
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
}
