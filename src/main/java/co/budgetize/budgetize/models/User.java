package co.budgetize.budgetize.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
//@Table(name="User")
//public class User implements Serializable {
//
//    @Id
//    @Column(name="id")
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private int id;
//
//    @Column(name="email")
//    private String email;
//
//    @Column(name="password")
//    private String password;
//
//    @Column(name="verifypassword")
//    private String verifypassword;
//
//    public User(){
//        email=null;
//        password=null;
//        verifypassword=null;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getVerifyPassword() {
//        return verifypassword;
//    }
//
//    public void setVerifyPassword(String verifypassword) {
//        this.verifypassword = verifypassword;
//    }
//
//}

public class User {

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
}