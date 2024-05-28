package com.pogtech.pogtech.data;

public class Users {


    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private int isAdmin;

    public Users(int id, String username, String password, String email, String name, int isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int isAdmin() {
        return isAdmin;
    }

    public void setAdmin(int admin) { isAdmin = admin;}
}
