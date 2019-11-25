package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.models.User;
import co.budgetize.budgetize.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;


@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UserDao userDao;

    // Routing to view the register page
//    @RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping
    public String register(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register");
        return "main/register";
    }

    // validate user input and store user to database
//    @RequestMapping(value = "register", method = RequestMethod.POST)
    @PostMapping
    public String register(Model model, @ModelAttribute @Valid User newUser, Errors errors,
                           @RequestParam String verifypassword) {

        model.addAttribute(newUser);
        boolean passwordsMatch = true;
        if (newUser.getPassword() == null || verifypassword == null
                || !newUser.getPassword().equals(verifypassword)) {
            passwordsMatch = false;
            newUser.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
            return "main/register";
        }

        if (!errors.hasErrors() && passwordsMatch) {
            userDao.save(newUser);
            return "expense/index";
        }
        model.addAttribute("title", "Register");
        return "main/register";
    }

}





