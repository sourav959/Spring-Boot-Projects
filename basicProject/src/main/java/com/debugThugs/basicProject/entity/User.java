package com.debugThugs.basicProject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User() {
    }

}
