package com.example.springproject.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class commit {
    @Id
    @GeneratedValue
    private Long id;
    private String time;
    private int commit_hour;
    private int repo;

    public commit(){

    }

    public commit(String time,int commit_hour,int repo)
    {
        this.time=time;
        this.commit_hour=commit_hour;
        this.repo=repo;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getCommit_hour() {
        return commit_hour;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCommit_hour(int commit_hour) {
        this.commit_hour = commit_hour;
    }
}
