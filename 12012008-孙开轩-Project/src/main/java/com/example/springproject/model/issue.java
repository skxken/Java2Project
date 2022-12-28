package com.example.springproject.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class issue {
    @Id
    @GeneratedValue
    private Long id;
    private String open_time;
    private String close_time;
    private int opening_days;
    private int repo;

    public issue(String open_time,String close_time,int opening_days,int repo)
    {
        this.open_time=open_time;
        this.close_time=close_time;
        this.opening_days=opening_days;
        this.repo=repo;
    }

    public issue() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOpening_days() {
        return opening_days;
    }

    public String getClose_time() {
        return close_time;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public void setOpening_days(int opening_days) {
        this.opening_days = opening_days;
    }
}
