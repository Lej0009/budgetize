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

//public class UserController {
//
//    @Autowired
//    private UserDao userDao;
//
//    @RequestMapping(value = "login")
//    public String login(Model model) {
//        return "expense/login";
//    }
//
//    @RequestMapping(value = "register")
//    public String register(Model model) {
//        return "expense/register";
//    }
//
//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    public String processRegisterUserForm(@ModelAttribute @Valid User newUser,
//                                        Errors errors, @RequestParam String email, @RequestParam String password,
//                                        @RequestParam String verifypassword, Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Register User");
//            return "expense/register";
//        }
//
//        newUser.setEmail(email);
//        newUser.setPassword(password);
//        newUser.setVerifyPassword(verifypassword);
//        userDao.save(newUser);
//        return "redirect:";
//
//    }
//}


@Controller
@RequestMapping("budgetize")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register");
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute @Valid User newUser,
                      Errors errors, @RequestParam String email,
                      @RequestParam String password, @RequestParam String verifypassword) {

        model.addAttribute(newUser);
        boolean passwordsMatch = true;
        if (newUser.getPassword() == null || verifypassword == null
                || !newUser.getPassword().equals(verifypassword)) {
            passwordsMatch = false;
            newUser.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
        }

        if (!errors.hasErrors() && passwordsMatch) {

            newUser.setEmail(email);
            newUser.setPassword(password);
            userDao.save(newUser);

            return "index";
        }

        return "register";

    }

    @RequestMapping(value = "login")
    public String login(Model model) {
        return "expense/login";
    }


}



