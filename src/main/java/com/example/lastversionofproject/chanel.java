package com.example.lastversionofproject;

import java.util.*;
import java.io.*;
import java.net.*;

public class chanel implements Serializable {

    public ArrayList<User> currentUserList = new ArrayList<User>();
    public ArrayList<Message> messagesOfChannel = new ArrayList<>();
    public ArrayList<ChatMessage> chatMList=new ArrayList<>();

    private String name;

    private String taggedMeesage;

    private String server;

    public void addusers(User user) {
        currentUserList.add(user);
    }

    public chanel(String name,String server) {

        this.name = name;

        this.server=server;

        for (User userlists : currentUserList) {

            currentUserList.add(userlists);

        }

    }

    public String getserver(){

        return server;

    }

    public void displaymessage(){

        for(ChatMessage cha:chatMList){
            System.out.println(cha.getMessage());
        }

    }
    public void setTaggedMessage(String message) {
        taggedMeesage = message;
    }

    public String getTagged(){
        return taggedMeesage;
    }

    public void removeUser(User user) {

        Iterator<User> it = currentUserList.iterator();

        while (it.hasNext()) {
            if (it == user) {
                it.remove();
            }
        }

    }

    public String getChanelName() {
        return name;
    }

    public void addMessage(Message message) {
        messagesOfChannel.add(message);
    }

    public void rmMessage(Message message) {

        Iterator it = messagesOfChannel.iterator();

        while (it.hasNext()) {
            if (it.next() == message) {
                it.remove();
            }
        }

    }

    public void restoreMessages() {

    }

    public void displayMessages() {

        try {

            for (Message m : messagesOfChannel) {
                System.out.println(m.getMessage()+"/n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addToCatM(ChatMessage cm){
        chatMList.add(cm);
    }

    public void rmCM(ChatMessage cm){
        chatMList.remove(cm);
    }

    public void fillUserlist(ArrayList<User> arr){

        for(User L:arr){

            currentUserList.add(L);


        }




    }

}

