package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "user_id")
    private long user_id;
    @Column(name = "date_time")
    private LocalDateTime date_time;

    public Task(){

    }

    public Task(String name,String description,long user_id,LocalDateTime date_time){
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.date_time = date_time;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", description=" + description + ", user_id=" + user_id + ", date_time=" + date_time + "]";
    }

}
