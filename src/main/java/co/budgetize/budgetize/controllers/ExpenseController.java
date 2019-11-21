package co.budgetize.budgetize.controllers;

import co.budgetize.budgetize.models.Expense;
import co.budgetize.budgetize.models.data.ExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("budgetize")
public class ExpenseController {

    @Autowired
    private ExpenseDao expenseDao;

    @RequestMapping(value = "")
    public String welcome(Model model) {
        return "expense/welcome";
    }

    @RequestMapping(value = "login")
    public String login(Model model) {
        return "expense/login";
    }

    @RequestMapping(value = "register")
    public String register(Model model) {
        return "expense/register";
    }

    // Request path: budgetize/expense
    @RequestMapping(value = "index")
    public String index(Model model) {

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
    public String processAddExpenseForm(@ModelAttribute @Valid Expense newExpense,
                                        Errors errors, @RequestParam Date date, @RequestParam String category,
                                        @RequestParam String description, @RequestParam Float amount, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Expense");
            return "expense/add";
        }
//        Expense exp = expenseDao.findOne(id);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);

        newExpense.setDate(date);
        newExpense.setCategory(category);
        newExpense.setDescription(description);
        newExpense.setAmount(amount);
        expenseDao.save(newExpense);
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayRemoveExpenseForm(Model model) {
        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Remove Expense");
        return "expense/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processRemoveExpenseForm(@RequestParam int[] expenseIds) {

        for (int expenseId : expenseIds) {
            expenseDao.delete(expenseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "bymonth")
    public String byMonth(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "My Expenses");

        return "expense/bymonth";
    }

    @RequestMapping(value = "bycategory")
    public String byCategory(Model model) {

        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "My Expenses");

        return "expense/bycategory";
    }
}
