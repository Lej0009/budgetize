package co.budgetize.budgetize.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int expenseId;

    @NotBlank(message = "Date must not be empty")
    private Date date;

    @NotBlank(message = "Category must not be empty")
    private String category;

    @NotBlank(message = "Description must not be empty")
    private String description;

    @NotBlank
    private Float amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Expense() { }

    public Expense(Date date, String category,
                   String description, Float amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date datepicker){
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
}
