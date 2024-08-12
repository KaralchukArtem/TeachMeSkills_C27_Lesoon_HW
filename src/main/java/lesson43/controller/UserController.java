package lesson43.controller;

import lesson43.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private StudentDao studentDao;
    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/get")
    public ModelAndView getGroup(@RequestParam(value = "title",required = false) String title) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("group", studentDao.getGroups(title));
        mav.setViewName("getGroups");
        return mav;
    }
    @GetMapping("/desc")
    public ModelAndView getStudentsDesc() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("group", studentDao.getAllStudentsDesc());
        mav.setViewName("getGroups");
        return mav;
    }
    @GetMapping("/top")
    public ModelAndView getTopStudents() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("group", studentDao.getTopStudents());
        mav.setViewName("getTopStudents");
        return mav;
    }
    @GetMapping("/avg")
    public ModelAndView getAVGStudents() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("group", studentDao.getAVGStudentsByRating());
        mav.setViewName("getTopStudents");
        return mav;
    }
}
