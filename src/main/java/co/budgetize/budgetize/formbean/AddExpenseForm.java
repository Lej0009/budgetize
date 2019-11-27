package co.budgetize.budgetize.formbean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class AddExpenseForm {

    @NotNull
    private Date date;

    @NotNull
    private String category;

    @NotNull
    @Size(min=5, max=50)
    private String description;

    @NotNull
    private Float amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public String toString() {
        return this.date + " | " + this.category + " | " + this.description + " | " + this.amount;
    }
}
