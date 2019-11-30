package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    UserService userService;



    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView processLogin() {

    }


}















//package co.budgetize.budgetize.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.lang.reflect.InvocationTargetException;
//import java.sql.*;
//
//@Controller
//@RequestMapping("login")
//public class LoginController {
//
//    // Routing to view the login page
////    @RequestMapping(value = "login", method = RequestMethod.GET)
//    @GetMapping
//    public String login(Model model) {
//        model.addAttribute("title", "Login");
//        return "login";
//    }
//
//    // Verify that the login email and password exists in the database
//    // If userId is found with matching email and password, set login to True
//    public boolean loginCheck(@RequestParam String username, @RequestParam String password){
//        String query;
//        boolean allowLogin = false;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/budgetize4", "budgetize4", "money");
//            Statement stmt = (Statement) con.createStatement();
//            query = "SELECT userId WHERE password=password AND username=username FROM user;";
//            stmt.executeQuery(query);
//            ResultSet result = stmt.getResultSet();
//
//            if (result != null) {
//                allowLogin = true;
//            }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return allowLogin;
//    }
//
//    // TODO: getting error that email parameter is not present
////    @RequestMapping(value = "login", method=RequestMethod.POST)
//    @PostMapping
//    public String login(Model model, @RequestParam String email, @RequestParam String password) {
//        if (loginCheck(email, password) == true) {
//            return "index";
//        }
//        model.addAttribute("title", "Login");
//        return "login";
//    }
//}

