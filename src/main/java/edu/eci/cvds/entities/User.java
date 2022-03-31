package edu.eci.cvds.entities;

public class User {
    private String username;
    private String fullname;
    private boolean status;
    private String mail;
    private String userpassword;
    private int usertype;

    public User() {
        super();
    }

    public User(String username, String fullname, boolean status, String mail, String userpassword, int usertype) {
        this.username = username;
        this.fullname = fullname;
        this.status = status;
        this.mail = mail;
        this.userpassword = userpassword;
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

}
