package co.budgetize.budgetize.models;

import javax.persistence.*;

@Entity
public class Role {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "role_id")
//    private int id;

    @Id
    @Column(name = "role_name")
    private String role;

    @Column(name = "role_desc")
    private String desc;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User app_user;

    public Role() {
    }

    //    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
