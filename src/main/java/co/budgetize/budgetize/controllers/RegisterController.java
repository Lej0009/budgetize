package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.dao.UserDao;
import co.budgetize.budgetize.formbean.RegisterUserForm;
import co.budgetize.budgetize.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class RegisterController implements WebMvcConfigurer {

    @Autowired
    private UserDao userDao;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    @GetMapping("/register")
    public String showForm(RegisterUserForm registerUserForm) {
        return "register";
    }

    @PostMapping("/register")
    public String processRegisterForm(Model model, @Valid RegisterUserForm registerUserForm,
                                      BindingResult bindingResult, @ModelAttribute User newUser,
                                      Error errors) {

        // if email exists in db, set registerValid to false
//        if (userDao.findOne(newUser.getEmail()) == null) {
//            registerValid = false;
//        }

        // if form validation errors, set registerValid to false
        if (!bindingResult.hasErrors()) {
            userDao.save(newUser);
            return "redirect:index";
        }

        return "register";
    }
}




//@Controller
//@RequestMapping("register")
//public class RegisterController {
//
//    @Autowired
//    private UserDao userDao;
//
//    // Routing to view the register page
////    @RequestMapping(value = "", method = RequestMethod.GET)
//    @GetMapping
//    public String register(Model model) {
//        model.addAttribute(new User());
//        model.addAttribute("title", "Register");
//        return "main/register";
//    }
//
//    // validate user input and store user to database
////    @RequestMapping(value = "register", method = RequestMethod.POST)
//    @PostMapping("/register")
//    public String register(Model model, @ModelAttribute @Valid User newUser, Errors errors,
//                           @RequestParam String verifypassword) {
//
//        model.addAttribute(newUser);
//        boolean passwordsMatch = true;
//        if (newUser.getPassword() == null || verifypassword == null
//                || !newUser.getPassword().equals(verifypassword)) {
//            passwordsMatch = false;
//            newUser.setPassword("");
//            model.addAttribute("verifyError", "Passwords must match");
//            return "main/register";
//        }
//
//        if (!errors.hasErrors() && passwordsMatch) {
//            userDao.save(newUser);
//            return "expense/index";
//        }
//        model.addAttribute("title", "Register");
//        return "main/register";
//    }
//
//}





