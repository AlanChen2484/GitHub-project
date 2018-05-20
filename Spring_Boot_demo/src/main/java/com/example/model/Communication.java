package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Communication {
    @Id
    @GeneratedValue
    private Integer itemid;

    @Column(nullable = false, length = 50)
    private String comname;
    @Column(nullable = false, length = 200)
    private String comcontent;
}
