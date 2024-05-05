package com.example.lastversionofproject;

import java.util.*;
import java.io.*;
import java.net.*;
import java.time.LocalDate;

public class Message implements Serializable {

    private String message;

    private String Sender;

    private LocalDate date;


    public Message(String message, String sender) {

        this.message = message;

        this.Sender = sender;

        LocalDate now = LocalDate.now();

        this.date = now;

    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return Sender;
    }
}
