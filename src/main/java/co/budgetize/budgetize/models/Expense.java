package co.budgetize.budgetize.models;

import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Expense {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
//    @Size(min=10, message = "Date must not be empty")
    private Date date;

//    @ManyToOne
    @NotNull
    private String category;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @NotNull
    private Float amount;

    public Expense(Date date, String category,
                   String description, Float amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public Expense() { }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        this.date = date;
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
