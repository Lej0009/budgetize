package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.formbean.RegisterUserForm;
import co.budgetize.budgetize.models.User;
import co.budgetize.budgetize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @PostMapping("/register")
    public String processRegisterForm(@Valid RegisterUserForm registerUserForm,
                                      BindingResult bindingResult, Model model,
                                      @ModelAttribute @Valid User newUser, Errors errors,
                                      String verifypassword, Principal principal) {

        // if email exists in db, set registerValid to false
        if (userService.isUserAlreadyPresent(newUser) == true) {
            model.addAttribute( "duplicateEmailError", "This email is already registered with an account");
        }

        model.addAttribute(newUser);

        boolean passwordsMatch = true;
        if (newUser.getPassword() == null || verifypassword == null
                || !newUser.getPassword().equals(verifypassword)) {
            passwordsMatch = false;
            newUser.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
        }

        if (!errors.hasErrors() && passwordsMatch) {
            userService.saveUser(newUser);
//            userDao.save(newUser);
            return "redirect:/index";
        }

//        if (!bindingResult.hasErrors()) {
//            userDao.save(newUser);
//            return "redirect:/index";
//        }
        model.addAttribute("title", "Register");
        return "register";
    }

}


//package co.budgetize.budgetize.controllers;
//
//import co.budgetize.budgetize.dao.UserDao;
//import co.budgetize.budgetize.formbean.RegisterUserForm;
//import co.budgetize.budgetize.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.validation.Valid;
//import java.security.Principal;
//
//@Controller
//public class RegisterController implements WebMvcConfigurer {
//
//    @Autowired
//    private UserDao userDao;
//
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/index").setViewName("index");
////    }
//
//    @GetMapping("/register")
//    public String showForm(RegisterUserForm registerUserForm) {
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String processRegisterForm(@Valid RegisterUserForm registerUserForm,
//                                      BindingResult bindingResult, Model model,
//                                      @ModelAttribute @Valid User newUser, Errors errors,
//                                      String verifypassword, Principal principal) {
//
//        // if email exists in db, set registerValid to false
//        if (userDao.findByUsername(principal.getName()) == null) {
//            model.addAttribute( "duplicateEmailError", "This email is already registered with an account");
//        }
//
//        model.addAttribute(newUser);
//
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
//            return "redirect:/index";
//        }
//
////        if (!bindingResult.hasErrors()) {
////            userDao.save(newUser);
////            return "redirect:/index";
////        }
//        model.addAttribute("title", "Register");
//        return "register";
//    }
//}
