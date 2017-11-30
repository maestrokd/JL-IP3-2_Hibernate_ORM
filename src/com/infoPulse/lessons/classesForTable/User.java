package com.infoPulse.lessons.classesForTable;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    // Fields
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private int user_id;

    @Id
    @Column(name = "user_login", length = 50)
    private String login;

    @Column(name = "user_password")
    private  String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "user")
    private List<Customer> customerList = new ArrayList<>();


    // Constructors
    public User() {}


    // Getter and Setter

//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }


}
