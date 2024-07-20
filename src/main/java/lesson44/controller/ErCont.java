package lesson44.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.web.servlet.error.ErrorController;


@Controller
public class ErCont implements ErrorController{
    @RequestMapping("/error")
    @ResponseBody
    String error(HttpServletRequest request){
        return "<h1>Error occurred</h1>";
    }
}