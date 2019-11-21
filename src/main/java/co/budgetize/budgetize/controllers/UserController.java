package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register");
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user,
                      Errors errors, String verify) {

        model.addAttribute(user);
        boolean passwordsMatch = true;
        if (user.getPassword() == null || verify == null
                || !user.getPassword().equals(verify)) {
            passwordsMatch = false;
            user.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
        }

        if (!errors.hasErrors() && passwordsMatch) {
            return "user/index";
        }

        return "user/add";

    }

}



