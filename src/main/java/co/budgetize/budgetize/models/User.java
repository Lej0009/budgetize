package co.budgetize.budgetize.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    // TODO: is this mappedBy expenses or users?
    @OneToMany(mappedBy = "user")
    private Map<Long, Expense> expenses = new HashMap<>();

    @NotNull
    @Size(min = 1, message = "Username must not be blank")
    private String username;

    @Email(message = "Invalid email address")
    private String email;

    @NotNull
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    // TODO: not sure what/why I have private boolean enabled...saw it
    // TODO: in a Spring Security tutorial, but not sure I need it
    private boolean enabled;
    private String encryptedPassword;

    // <-- constructors -->
    public User() {
    }

    public User(String username, String email, String password, String encryptedPassword,
                boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getUserId()  { return userId; }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}