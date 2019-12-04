package co.budgetize.budgetize.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/")
public class MainController implements WebMvcConfigurer {

    @RequestMapping(value = "")
    public String welcome(Model model) {
        return "welcome";
    }
}
