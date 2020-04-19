package com.shu.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String address;

    public User() {
    }

  }
