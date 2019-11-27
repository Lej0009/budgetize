package co.budgetize.budgetize.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "")
    public String welcome(Model model) {
        return "welcome";
    }

}
