package com.example.springproject.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class open_issue {
    @Id
    @GeneratedValue
    private Long id;
    private int total_num;
    private int repo;
    public open_issue(){

    }
    public open_issue(int total_num,int repo)
    {
        this.total_num=total_num;
        this.repo=repo;
    }

}
