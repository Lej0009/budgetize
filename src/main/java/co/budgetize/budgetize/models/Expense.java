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

    private String strDate;

//    @ManyToOne
    @NotNull
    private String category;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @NotNull
    private Float amount;

    public Expense(String strDate, String category,
                   String description, Float amount) {
        this.strDate = strDate;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public Expense() { }

    public String getDate() {
        return strDate;
    }

    public void setDate(@RequestParam String date){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);

//        String strDate = SimpleDateFormat.format(date);
        this.strDate = strDate;
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
