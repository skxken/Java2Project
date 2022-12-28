package com.example.springproject.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class developer {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String github_id;
    private int commit_times;
    private int repo;

    public developer(){

    }

    public developer(String username,String nickname,String email,String github_id,int commit_times,int repo)
    {
        this.username=username;
        this.nickname=nickname;
        this.email=email;
        this.commit_times=commit_times;
        this.github_id=github_id;
        this.repo=repo;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public int getCommit_times() {
        return commit_times;
    }

    public int getRepo() {
        return repo;
    }

    public String getGithub_id() {
        return github_id;
    }

    public void setCommit_times(int commit_times) {
        this.commit_times = commit_times;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRepo(int repo) {
        this.repo = repo;
    }

    public void setGithub_id(String github_id) {
        this.github_id = github_id;
    }
}
