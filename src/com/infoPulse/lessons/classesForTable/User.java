package com.infoPulse.lessons.classesForTable;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "user_login")
    private String user_login;

    @Column(name = "user_password")
    private  String user_password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "user")
    private List<Customer> customerList = new ArrayList<>();


    // Constructors
    public User() {}


    // Getter and Setter
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_login='" + user_login + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
