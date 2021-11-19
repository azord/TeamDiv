package com.example.soccerrandomizer.db;

import javax.persistence.*;

@Entity
@Table(name="user_table")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int id;
    private String username;
    private String user_password;
    private String email;

    public Person() {
    }

    public Person(String username, String user_password, String email) {
        this.username = username;
        this.user_password = user_password;
        this.email = email;
    }

    public Person(int id, String username, String user_password, String email) {
        this.id = id;
        this.username = username;
        this.user_password = user_password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
