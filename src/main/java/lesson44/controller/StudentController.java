package lesson44.controller;

import jakarta.validation.Valid;
import lesson44.dao.StudentDao;
import lesson44.model.StudentModel;
import lesson44.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private Validator validator;

    @GetMapping
    public ModelAndView start() {
        ModelAndView modelAndView = new ModelAndView("start");
        modelAndView.addObject("students", studentDao.getAllStudents());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getStudent(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("student");
        modelAndView.addObject("student", studentDao.getStudent(id));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateStudentPage(@ModelAttribute StudentModel student, BindingResult result) {
        return new ModelAndView("createStudent", "student", student);
    }

    @PostMapping("/create")
    public ModelAndView createStudent(@Valid @ModelAttribute StudentModel student, BindingResult result) {
        studentDao.addStudent(student);
        return new ModelAndView("createdStudent", "student", student);
    }

    @GetMapping("/delete")
    public ModelAndView showDeleteStudentPage() {
        return new ModelAndView("deleteStudent");
    }

    @PostMapping("/delete")
    public ModelAndView deleteStudent(@RequestParam int id) {
        if (this.validator.isIdValid(id)) {
            this.studentDao.deleteStudent(id);
            return new ModelAndView("deletedStudent").addObject("id", id);
        } else {
            return new ModelAndView("deleteStudent").addObject("message", "id " + id + " is not found");
        }
    }
}
