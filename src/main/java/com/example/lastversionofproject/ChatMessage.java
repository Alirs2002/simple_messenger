package com.example.lastversionofproject;




import java.io.*;

public class ChatMessage implements Serializable {


    static final int WHOISIN = 0, MESSAGE = 1, LOGOUT = 2;
    private int type;
    private String message;
    private chanel ch;
    private String replied;

    // constructor
    ChatMessage(int type, String message,chanel ch) {
        this.type = type;
        this.message = message;
        this.ch=ch;
    }

    public void setReplied(String cm){
        this.replied=cm;
    }

    int getType() {
        return type;
    }

    String getMessage() {
        return message;
    }
    String getChanel(){
        return ch.getChanelName();
    }
}
