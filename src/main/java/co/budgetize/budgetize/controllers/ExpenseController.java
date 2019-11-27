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
import java.util.*;

@Controller
public class ExpenseController implements WebMvcConfigurer {

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private UserDao userDao;

    // main expense display page !!! using @GetMapping below instead
//    @RequestMapping(value = "index")
//    public String index(Model model, Long USER_ID) {

        //TODO: figure out how to get all expenses for the current user
        //TODO: filtered by current active session logged in user
//        model.addAttribute("expenses", expenseDao.findAll(USER_ID));

//        Iterable<Expense> expenses = expenseDao.findAll();
//        DateComparator comparator = new DateComparator();
//        expenses.sort(comparator):

//        model.addAttribute("expenses", expenseDao.findById(USER_ID));
//        model.addAttribute("title", "My Expenses");
//
//        return "index";
//    }

//    @RequestMapping(value = "add", method = RequestMethod.GET)
//    public String displayAddExpenseForm(Model model) {
//
//        model.addAttribute("title", "Add Expense");
//        model.addAttribute(new Expense());
//          don't think I need ^^^ line
//        return "add";
//    }

    @GetMapping("/add")
    public String showForm(AddExpenseForm addExpenseForm, Model model, Long USER_ID) {
        model.addAttribute("expenses", expenseDao.findById(USER_ID));
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
        return "add";
    }

//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddExpenseForm(@ModelAttribute @Valid Expense newExpense, Errors errors,
//                                        @RequestParam Date datepicker, Model model) {
//        model.addAttribute(newExpense);

        // TODO: getting error "Cannot format given Object as a Date" when this
        // TODO: part of code is included. With date set as a String or a Date
//        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        String dt2 = dt1.format(datepicker);
//        newExpense.setDate(datepicker);
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Expense");
//            return "add";
//        } else {
//            expenseDao.save(newExpense);
//            return "index";
//        }
//    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayRemoveExpenseForm(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Remove Expense");
        return "delete";
    }

//    @RequestMapping(value = "delete", method = RequestMethod.POST)
//    public String processRemoveExpenseForm(@RequestParam Integer[] expenseIds) {
//
//        for (int expenseId : expenseIds) {
//            Expense delExpense = expenseDao.findOne(expenseId);
//            expenseDao.delete(delExpense);
//        }
//
//        return "redirect:";
//    }

    @RequestMapping(value = "bymonth")
    public String byMonth(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Month");

        return "bymonth";
    }

    @RequestMapping(value = "bycategory")
    public String byCategory(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Category");

        return "bycategory";
    }
}
