package lesson43.controller;

import lesson43.model.UserModel;
import lesson43.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @PostMapping(path = "/create", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView createUser(UserModel model) {
        ModelAndView mav = new ModelAndView();
        System.out.println("create");
        model = userService.createUser(model);
        if (model != null) {
            mav.addObject("user", model);
            mav.setViewName("infopage");
        } else {
            mav.setViewName("start");
        }
        return mav;
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@RequestParam(value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        userService.deleteUser(id);
        mav.setViewName("start");
        return mav;
    }

    @PostMapping("/change-login")
    public ModelAndView changeLoginUser(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();
        userService.changeLogin(name, id);
        mav.setViewName("start");
        return mav;
    }

    @GetMapping("/get")
    public ModelAndView getUser(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();
        UserModel requestUser = userService.getUser(id);
        mav.addObject("user", requestUser);
        mav.setViewName("infopage");
        return mav;
    }
}
