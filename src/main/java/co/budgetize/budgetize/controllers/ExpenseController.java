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

        //if user in session:
        //TODO: figure out how to get all expenses for the current user
//        model.addAttribute("expenses", expenseDao.findById(USER_ID));

        //TODO:
        //HttpSession

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
                                        @ModelAttribute int userId, Errors errors, Model model) {


        //if user in session:
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Expense");
            return "expense/add";
        }
        newExpense.setUserId(userId);
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

//    @RequestMapping(value = "delete", method = RequestMethod.POST)
//    public String processRemoveExpenseForm(@RequestParam int[] expenseIds) {
//
//        //if user in session:
//        for (int expense : expenseIds) {
//            Expense delExpense = expenseDao.findOne(expense);
//            expenseDao.delete(delExpense);
//        }
//
//        return "redirect:";
//
//        //else:
//        //return "login";
//    }

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
