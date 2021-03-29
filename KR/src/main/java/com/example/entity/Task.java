package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date create_date;
    private Date change_date;
    private String name;
    private String description;
    private Date complete_date;
    private Boolean completed;

    @ManyToOne
    private Client client;
    @ManyToMany(targetEntity= Category.class)
    private Set categories;
}
