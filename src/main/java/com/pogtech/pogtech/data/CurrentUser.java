package com.pogtech.pogtech.data;

import java.util.ArrayList;
import java.util.List;

public class CurrentUser {
    private String name;
    private String username;
    private String email;
    private int isAdmin;

    private static List<CurrentUser> currentUserList = new ArrayList<>();

    public CurrentUser(String name, String username, String email, int isAdmin) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
        currentUserList.add(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public static List<CurrentUser> getCurrentUserList() {
        return currentUserList;
    }

    public static void removeFirstUser() {
        if (!currentUserList.isEmpty()) {
            currentUserList.remove(0);
        }
    }
}
