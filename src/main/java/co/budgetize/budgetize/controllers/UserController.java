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
import java.lang.reflect.InvocationTargetException;
import java.sql.*;


@Controller
@RequestMapping("budgetize")
public class UserController {

    @Autowired
    private UserDao userDao;

    // Routing to view the register page
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register");
        return "expense/register";
    }

    // validate user input and store user to database
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute @Valid User newUser, Errors errors,
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
        model.addAttribute("title", "Register");
        return "expense/register";
    }

    // Routing to view the login page
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "expense/login";
    }

    // Verify that the login email and password exists in the database
    // If userId is found with matching email and password, set login to True
    public boolean loginCheck(@RequestParam String username, @RequestParam String password){
        String query;
        boolean login = false;

        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/budgetize4", "budgetize4", "money");
            Statement stmt = (Statement) con.createStatement();
            query = "SELECT userId WHERE password=password AND username=username FROM user;";
            stmt.executeQuery(query);
            ResultSet result = stmt.getResultSet();

            if (result != null) {
                login = true;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return login;
    }

    // TODO: getting error that email parameter is not present
    @RequestMapping(value = "login", method=RequestMethod.POST)
    public String login(Model model, @RequestParam String email, @RequestParam String password) {
        if (loginCheck(email, password) == true) {
            return "expense/index";
        }
        model.addAttribute("title", "Login");
        return "expense/login";
    }

}





