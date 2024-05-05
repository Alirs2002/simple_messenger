package com.example.lastversionofproject;

import java.util.*;
import java.io.*;
import java.net.*;

public class User implements Serializable {

    ArrayList<User> blockedUser = new ArrayList<>();
    ArrayList<User> friend = new ArrayList<>();
    ArrayList<User> waitingList = new ArrayList<>();


    private String username;

    private String password;

    private String email;

    private String situation;

    private boolean isAdmin;

    public User(String username, String password, String email) {

        this.username = username;

        this.password = password;

        this.email = email;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeSituation(String situation) {
        this.situation = situation;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdminOfServer() {
        return isAdmin;
    }

    public void addToBlocked(User user) {
        blockedUser.add(user);
    }

    public void rmBlockedUsers(User user) {
        blockedUser.remove(user);
    }

    public void addToFriends(User user) {
        friend.add(user);
    }

    public void removeFromUserList(User user) {
        friend.remove(user);
    }

    public void addToWaitingList(User user) {
        waitingList.add(user);
    }

    public void rejectingRequest(User user) {

        Iterator it = waitingList.iterator();

        while (it.hasNext()) {

            if (it.next() == user) {
                it.remove();
            }

        }

    }

    public void printWaiting(){

        for(User u:waitingList){

            System.out.println(u.getUsername());

        }



    }

    public User userfind(String name){

        User u=null;

        for(User userr:waitingList){


            if(userr.getUsername().equals(name)){
                u=userr;
            }


        }
        return u;

    }
}
