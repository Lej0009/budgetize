package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.models.Expense;
import co.budgetize.budgetize.models.User;
import co.budgetize.budgetize.models.data.ExpenseDao;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("budgetize")
public class ExpenseController {

    @Autowired
    private ExpenseDao expenseDao;

    // TODO: don't think I need this
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String welcome(Model model) {
        return "expense/welcome";
    }

    @RequestMapping(value = "welcome")
    public String welcome2(Model model) { return "expense/welcome";}

    @RequestMapping(value = "index")
    public String index(Model model, Integer USER_ID) {

        //TODO: figure out how to get all expenses for the current user
//        model.addAttribute("expenses", expenseDao.findById(USER_ID));

        //TODO:
        //HttpSession

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "My Expenses");

        return "expense/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddExpenseForm(Model model) {

        model.addAttribute("title", "Add Expense");
        model.addAttribute(new Expense());

        return "expense/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddExpenseForm(@ModelAttribute @Valid Expense newExpense, Errors errors,
                                        @RequestParam String datepicker, Model model) {
        model.addAttribute(newExpense);

        try {
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dt2 = dt1.parse(dt1.format(datepicker));
            newExpense.setDate(dt2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Expense");
            return "expense/add";
        } else {
            expenseDao.save(newExpense);
            return "expense/index";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayRemoveExpenseForm(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Remove Expense");
        return "expense/delete";
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

        return "expense/bymonth";
    }

    @RequestMapping(value = "bycategory")
    public String byCategory(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Category");

        return "expense/bycategory";
    }
}
