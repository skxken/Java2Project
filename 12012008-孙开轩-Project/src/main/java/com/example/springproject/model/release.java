package com.example.springproject.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class release {
    @Id
    @GeneratedValue
    private Long id;
    private String release_time;
    private int repo;
    public release(){

    }
    public release(String release_time,int repo)
    {
        this.release_time=release_time;
        this.repo=repo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getRepo() {
        return repo;
    }

    public void setRepo(int repo) {
        this.repo = repo;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }
}
