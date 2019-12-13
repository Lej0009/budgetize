package co.budgetize.budgetize.formbean;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class AddExpenseForm {

//    @NotNull
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

    @NotNull
    private String category;

    @NotNull
    @Size(min=5, max=50)
    private String description;

    @NotNull
    private Float amount;

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
//    public void setDate(Date date) {
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

//    public String toString() {
//        return this.date + " | " + this.category + " | " + this.description + " | " + this.amount;
//    }
}
