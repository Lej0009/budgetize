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

    @RequestMapping(value = "welcome")
    public String welcome2(Model model) { return "expense/welcome";}

    @RequestMapping(value = "login")
    public String login(Model model) {
        return "expense/login";
    }

    @RequestMapping(value = "register")
    public String register(Model model) {
        return "expense/register";
    }

    @RequestMapping(value = "index")
    public String index(Model model) {

        //if user in session:
        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "My Expenses");

        return "expense/index";

        // else:
        // return "login";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddExpenseForm(Model model) {

        //if user in session:
        model.addAttribute("title", "Add Expense");
        model.addAttribute(new Expense());

        return "expense/add";

        //else:
        //return "login";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddExpenseForm(@ModelAttribute @Valid Expense newExpense,
                                        Errors errors, Model model) {


        //if user in session:
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Expense");
            return "expense/add";
        }
//        Expense exp = expenseDao.findOne(id);

//        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat.format(date);

//        newExpense.setDate(strDate);
//        newExpense.setCategory(category);
//        newExpense.setDescription(description);
//        newExpense.setAmount(amount);
        expenseDao.save(newExpense);
        return "redirect:";

        //else:
        //return "login";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayRemoveExpenseForm(Model model) {

        //if user in session:
        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Remove Expense");
        return "expense/delete";

        //else:
        //return "login";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processRemoveExpenseForm(@RequestParam int[] expenseIds) {

        //if user in session:
        for (int expenseId : expenseIds) {
            expenseDao.delete(expenseId);
        }

        return "redirect:";

        //else:
        //return "login";
    }

    @RequestMapping(value = "bymonth")
    public String byMonth(Model model) {

        //if user in session:
        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Month");

        return "expense/bymonth";

        //else:
        //return "login";
    }

    @RequestMapping(value = "bycategory")
    public String byCategory(Model model) {

        //if user in session:
        model.addAttribute("expenses", expenseDao.findAll());
        model.addAttribute("title", "Expenses by Category");

        return "expense/bycategory";

        //else:
        //return "login";
    }
}