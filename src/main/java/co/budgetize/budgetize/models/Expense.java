package co.budgetize.budgetize.models;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Entity
public class Expense {

    @Id
    @GeneratedValue
    private Integer expenseId;

    @NotNull
    @Size(min=10, message = "Date must not be empty")
    private String date;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @NotNull
    private String category;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @NotNull
    private Float amount;

    public Expense(String date, String category,
                   String description, Float amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public Expense() { }

    public String getDate() {
        return date;
    }

    public void setDate(@RequestParam String datepicker){
        this.date = datepicker;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }


    public int getExpenseId() {
        return expenseId;
    }

}
