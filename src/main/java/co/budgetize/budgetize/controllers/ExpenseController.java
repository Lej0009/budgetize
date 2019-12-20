package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.dao.ExpenseDao;
import co.budgetize.budgetize.dao.UserDao;
import co.budgetize.budgetize.models.Expense;
import co.budgetize.budgetize.models.User;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/")
public class ExpenseController implements WebMvcConfigurer {

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/home")
    public String displayExpenses(Model model, User user) {
        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "All Expenses");

        return "index";
    }

    @GetMapping("/home/add")
    public String displayAddExpenseForm(Model model, User user) {
        model.addAttribute("title", "Add Expense");
        return "add";
    }

    @PostMapping("/home/add")
    public String processAddExpenseForm(User user, Model model, @ModelAttribute @Valid Expense newExpense,
                                        Errors errors,  Principal principal) {

        model.addAttribute(newExpense);

        if(!errors.hasErrors()) {

//            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//            String name=auth.getName();
//            UserDetails userDetail= (UserDetails)auth.getPrincipal();
//
//            User currentUser= userDao.findByPassword(userDetail.getPassword());
//            Integer id= currentUser.getUserId();

            User currentUser = userDao.findByEmail(principal.getName());
            Integer id = currentUser.getUserId();

            newExpense.setUserId(id);
            expenseDao.save(newExpense);
            return "redirect:/home";
        }
        model.addAttribute("title", "Add Expense");
        return "add";
    }

    @GetMapping("/home/delete")
    public String displayRemoveExpenseForm(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Remove Expense");
        return "delete";
    }

    // TODO: throwing error
//    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @PostMapping("/home/delete")
    public String processRemoveExpenseForm(@RequestParam Integer[] expenseIds) {

        for (Integer expenseId : expenseIds) {
            expenseDao.deleteById(expenseId);
        }

        return "redirect:/home";
    }

    @GetMapping("/home/bymonth")
    public String displayExpensesByMonth(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Month");

        return "bymonth";
    }

    @GetMapping("/home/bycategory")
    public String displayExpensesByCategory(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Category");

        return "bycategory";
    }
}
