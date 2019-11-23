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
import java.lang.reflect.InvocationTargetException;
import java.sql.*;


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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("newUser") User newUser, Errors errors,
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

    public boolean loginCheck(@RequestParam String username, @RequestParam String password){
        String query;
        boolean login = false;

        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/budgetize4", "budgetize4", "money");
            Statement stmt = (Statement) con.createStatement();
            query = "SELECT userId WHERE password=password AND email=email FROM user;";
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


        //        String query;
//        String dbUsername, dbPassword;
//        boolean login = false;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/budgetize4", "budgetize4", "money");
//            Statement stmt = (Statement) con.createStatement();
//            query = "SELECT username, password FROM user;";
//            stmt.executeQuery(query);
//            ResultSet rs = stmt.getResultSet();
//
//            while(rs.next()){
//                dbUsername = rs.getString("username");
//                dbPassword = rs.getString("password");
//
//                if(dbUsername.equals(username) && dbPassword.equals(password)){
////                    System.out.println("OK");
//                    login = true;
//                }
////                System.out.println(username + password + " " + dbUsername + dbPassword);
//            }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return login;
    }


    @RequestMapping(value = "login", method=RequestMethod.POST)
    public String login(Model model, @RequestParam String username, @RequestParam String password) {
        if (loginCheck(username, password) == true) {
            return "expense/index";
        }

        return "expense/login";
    }

}





