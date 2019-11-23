package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.models.User;
import co.budgetize.budgetize.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSessionEvent;
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


    // TODO: must set up register in order to post exepnse because userId is needed DUH
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute User newUser, Errors errors,
                           @RequestParam String verifypassword) {



        model.addAttribute(newUser);
        boolean passwordsMatch = true;
        if (newUser.getPassword() == null || verifypassword == null
                || !newUser.getPassword().equals(verifypassword)) {
            passwordsMatch = false;
            newUser.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
            return "expense/register";
        }

        if (!errors.hasErrors() && passwordsMatch) {
            userDao.save(newUser);
            return "expense/index";
        }

        return "expense/register";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "expense/login";
    }

    @RequestMapping(value = "login", method= RequestMethod.POST)
    public String login(Model model, @RequestParam String email,
                        @RequestParam String password, Errors errors) {

        if (userDao.findOne(email) && userDao.findOne(password)) {
                return "expense/index";
            }

        return "expense/login";
        }
    }


    //TODO: SessionCreatedEvent for session activation upon login

    //TODO: this has to do with current session
    //TODO: see https://www.javacodegeeks.com/2013/04/spring-mvc-session-tutorial.html
//    @RequestMapping(value="/login")
//    public ModelAndView login() {
//        return new ModelAndView("expense/login");
//    }
//
//    @RequestMapping(value="/index")
//    public ModelAndView rememberBudgetize(@RequestParam String budgetizeParam) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("budgetize", budgetizeParam);
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }




