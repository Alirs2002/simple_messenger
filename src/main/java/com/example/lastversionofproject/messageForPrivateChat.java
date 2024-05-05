package com.example.lastversionofproject;

import java.util.*;
import java.io.*;
import java.net.*;
import java.time.LocalDate;

public class messageForPrivateChat implements Serializable{

    String Sender;

    String Reciever;

    String MessageText;

    public messageForPrivateChat(String sender, String reciever, String message) {

        this.Sender = sender;

        this.Reciever = reciever;

        this.MessageText = message;

    }

    public String getSender() {
        return Sender;
    }

    public String getReciever() {
        return Reciever;
    }

    public String getMessage() {
        return MessageText;
    }
}
