package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.models.User;
import co.budgetize.budgetize.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;



@Controller
@RequestMapping("budgetize")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register");
        return "expense/register";
    }

//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    public String register(Model model, @ModelAttribute @Valid User newUser,
//                      Errors errors, @RequestParam String email,
//                      @RequestParam String password, @RequestParam String verifypassword) {
//
//        model.addAttribute(newUser);
//        boolean passwordsMatch = true;
//        if (newUser.getPassword() == null || verifypassword == null
//                || !newUser.getPassword().equals(verifypassword)) {
//            passwordsMatch = false;
//            newUser.setPassword("");
//            model.addAttribute("verifyError", "Passwords must match");
//        }
//
//        if (!errors.hasErrors() && passwordsMatch) {
//            userDao.save(newUser);
//            return "expense/index";
//        }
//
//        return "expense/register";
//    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
//        model.addAttribute(new User());
        model.addAttribute("title", "Login");
        return "expense/login";
    }


}



