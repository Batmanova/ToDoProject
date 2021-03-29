package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
    private Date birthday;
    private Date create_date;
    private Date change_date;

    @OneToMany(targetEntity= Task.class )
    private List tasks;


}
