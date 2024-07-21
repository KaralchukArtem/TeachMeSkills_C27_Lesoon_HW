package lesson44.controller;

import jakarta.validation.Valid;
import lesson44.dao.StudentDao;
import lesson44.model.StudentModel;
import lesson44.repository.StudentsRepository;
import lesson44.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class UserController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private Validator validator;
    @Autowired
    private StudentsRepository repository;
    @GetMapping("")
    public ModelAndView start() {
        StudentModel model = repository.getUsers();
        System.out.println(model);
        ModelAndView modelAndView = new ModelAndView("start");
        modelAndView.addObject("students",this.studentDao.getAllStudents());
        return modelAndView;
    }
    @GetMapping("{id}")
    public ModelAndView getStudent(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("student");
        modelAndView.addObject("student",this.studentDao.getStudent(id));
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView showCreateStudentPage(@ModelAttribute StudentModel student,BindingResult result) {
        return new ModelAndView("createStudent","student",student);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ModelAndView createStudent(@Valid @ModelAttribute StudentModel student,BindingResult result) {
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
