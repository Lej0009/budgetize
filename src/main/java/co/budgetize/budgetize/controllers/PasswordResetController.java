package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.dao.UserDao;
import co.budgetize.budgetize.models.User;
import co.budgetize.budgetize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordResetController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "updatepassword", method = RequestMethod.GET)
    public ModelAndView viewUpdatePassword(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("title", "Reset Password");
        modelAndView.setViewName("updatepassword"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "updatepassword", method = RequestMethod.POST)
    public String processUpdatePassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail,
                                         Model model, String password, String verifypassword,
                                        User user) {
        // verify if user found in database
        if (userService.isUserAlreadyPresent(user) == false) {
            model.addAttribute("error", "User not found");
        }

        boolean passwordsMatch = true;
        if (password == null || verifypassword == null
                || !password.equals(verifypassword)) {
            passwordsMatch = false;
            model.addAttribute("verifyError", "Passwords must match");
        }

        if (passwordsMatch) {
            userService.changeUserPassword(user, password);
            return "redirect:/login";
        }
        model.addAttribute("title", "Reset Password");
        return "updatepassword";
    }
}

