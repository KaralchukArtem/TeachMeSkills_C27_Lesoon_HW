//package lesson44.dao;
//
//import lesson44.repository.BankingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class StudentDao {
//    @Autowired
//    private BankingRepository repository;
//    public List<StudentModel> getAllStudents() {
//        List<StudentModel> list = new ArrayList<>();
//        for (Map.Entry<Integer, StudentModel> entry : repository.getIntegerPersonModelMap().entrySet()){
//            list.add(entry.getValue());
//        }
//        return list;
//    }
//    public StudentModel getStudent(int id) {
//        return repository.getIntegerPersonModelMap().get(id);
//    }
//    public void addStudent(StudentModel student) {
//        this.repository.addStudent(student);
//    }
//    public void deleteStudent(int id) {
//        this.repository.getIntegerPersonModelMap().remove(id);
//    }
//}
