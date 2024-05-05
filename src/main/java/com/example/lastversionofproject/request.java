package com.example.lastversionofproject;

public class request {

    private String sender;

    private String reciever;

    public request(String sender,String reciever){

        this.sender=sender;

        this.reciever=reciever;

    }

    public String getReciever(){
        return reciever;
    }
    public String getsender(){
        return sender;
    }


}
