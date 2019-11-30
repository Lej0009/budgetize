package co.budgetize.budgetize.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @NotBlank
    @Size(min = 1, message = "Username must not be blank")
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    @Column(name = "status")
    private String status;

    @OneToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles;

//    @JoinColumn(name = "expense_id")
    @OneToMany
    @JoinTable(name = "user_expense", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "expense_id"))
    private List<Expense> expenses;

//    @OneToMany(mappedBy = "user")
//    private Map<Long, Expense> expenses = new HashMap<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getUserId()  { return userId; }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}