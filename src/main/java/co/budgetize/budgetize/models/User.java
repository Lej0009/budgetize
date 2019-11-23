package co.budgetize.budgetize.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId;

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses = new ArrayList<>();

    @Email(message = "Invalid email address")
    private String email;

    @NotNull
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId()  { return userId; }
}