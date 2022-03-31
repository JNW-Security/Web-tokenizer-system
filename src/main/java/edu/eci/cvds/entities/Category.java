package edu.eci.cvds.entities;

import java.util.Date;

public class Category {
    private int id;
    private Date creationDate;
    private String name;
    private int age;
    private String credential;
    private String rh;
    private String vaccine;
    private String description;

    public Category() {
        super();
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(int id, Date creationDate, String name, int age, String credential, String rh, String vaccine, String description) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
        this.age = age;
        this.credential = credential;
        this.rh = rh;
        this.vaccine = vaccine;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }
}
