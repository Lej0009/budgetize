package co.budgetize.budgetize.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.security.Principal;
import java.util.Date;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "expense_id")
    private int expenseId;

//    @NotNull
//    @Column(name = "date")
//    private Date date;

    @NotNull
    @Column(name = "month")
    @Min(1)
    @Max(12)
    private int month;

    @NotNull
    @Column(name = "day")
    @Min(1)
    @Max(31)
    private int day;

    @NotNull
    @Column(name = "year")
    @Min(2018)
    @Max(2020)
    private int year;

    @NotBlank(message = "Category must not be empty")
    @Column(name = "category")
    private String category;

    @NotBlank(message = "Description must not be empty")
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "amount")
    private Float amount;

//    @ManyToOne
    @Column(name = "user_id")
    private int user;

    public Expense() { }

    public Expense(Date date, String category,
                   String description, Float amount) {
//        this.date = date;
        this.month = month;
        this.day = day;
        this.year = year;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date){
//        this.date = date;
//    }

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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
