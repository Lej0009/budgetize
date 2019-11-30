package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.dao.ExpenseDao;
import co.budgetize.budgetize.dao.UserDao;
import co.budgetize.budgetize.formbean.AddExpenseForm;
import co.budgetize.budgetize.formbean.RegisterUserForm;
import co.budgetize.budgetize.models.Expense;
import co.budgetize.budgetize.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class ExpenseController implements WebMvcConfigurer {

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/add")
    public String displayAddExpenseForm(Model model, Principal principal) {
        model.addAttribute("expenses", expenseDao.findByUser(principal));
        model.addAttribute("title", "Add Expense");
        return "register";
    }

    @PostMapping("/add")
    public String processAddExpenseForm(@Valid AddExpenseForm addExpenseForm,
                                        BindingResult bindingResult, Model model,
                                        @ModelAttribute @Valid Expense newExpense, Errors errors) {

        model.addAttribute(newExpense);

        if(!errors.hasErrors()) {
            expenseDao.save(newExpense);
            return "redirect:/index";
        }
        model.addAttribute("title", "Add Expense");
        return "add";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayRemoveExpenseForm(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Remove Expense");
        return "delete";
    }

    // TODO: throwing error
//    @RequestMapping(value = "delete", method = RequestMethod.POST)
//    public String processRemoveExpenseForm(@RequestParam Long[] expenseIds) {
//
//        for (Long expenseId : expenseIds) {
//            Expense delExpense = expenseDao.findById(expenseId);
//            expenseDao.delete(delExpense);
//        }
//
//        return "redirect:";
//    }

    @RequestMapping(value = "bymonth")
    public String displayExpensesByMonth(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Month");

        return "bymonth";
    }

    @RequestMapping(value = "bycategory")
    public String displayExpensesByCategory(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Category");

        return "bycategory";
    }
}
