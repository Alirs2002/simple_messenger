package com.example.lastversionofproject;

import java.util.*;

import java.io.*;
import java.net.*;
import java.nio.channels.Channel;


public class Client {

    private chanel cha;

    private madeServer serverOfTheProgram;

    public void setServer(madeServer ser){
        serverOfTheProgram=ser;
    }

    public madeServer getServer(){
        return serverOfTheProgram;
    }

    public void setChanel(chanel cha){
        this.cha=cha;
    }

    public chanel getChanel(){
        return cha;
    }

    Scanner sc = new Scanner(System.in);

    public static User userOfProgram = null;
    public static User contactUser = null;

    final static int serverPortOfGroupChat = 1500;
    final static int serverPortOfPrivateChat = 3344;

    static ArrayList<User> userlist = new ArrayList<>();
    static ArrayList<User> newuserlist=new ArrayList<>();
    static ArrayList<User> savinguser=new ArrayList<>();

    static ArrayList<madeServer> serverList = new ArrayList<>();
    static ArrayList<madeServer> newserverlist= new ArrayList<>();
    static ArrayList<madeServer> savingserver=new ArrayList<>();

    static ArrayList<chanel> chanellist=new ArrayList<>();


    static ArrayList<messageForPrivateChat> pvMessages = new ArrayList<>();
    static ArrayList<messageForPrivateChat> newpvmessages=new ArrayList<>();
    static ArrayList<messageForPrivateChat> savingpvmessages=new ArrayList<>();

    static ArrayList<ChatMessage> chatmess = new ArrayList<>();
    static ArrayList<ChatMessage> newchatmess = new ArrayList<>();
    static ArrayList<ChatMessage> savingchatmess=new ArrayList<>();
    static ArrayList<request> reqlist=new ArrayList<>();

    public static final String fileofreq = "D:\\RD_Messanger\\reqlist.txt";
    public static final String filePaths = "D:\\RD_Messanger\\userList.txt";
    public static final String filePath = "D:\\RD_Messanger\\serverList.txt";
    public static final String fileOfPV = "D:\\RD_Messanger\\pvMessages.txt";
    public static final String fileOfMessages = "D:\\RD_Messanger\\chatMessages.txt";
    public static final String fileofchanel="D:\\RD_Messanger\\chanellist.txt";

    public static void restorereq() throws IOException {

        FileInputStream fis=new FileInputStream(fileofreq);

        try(ObjectInputStream ois=new ObjectInputStream(fis)){

            boolean tr=true;

            while (tr){

                request us=null;

                try{

                    us=(request) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(us!=null){
                    reqlist.add(us);
                }else{
                    tr=false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public static void savereq(){

        try {


            FileOutputStream fos=new FileOutputStream(fileofreq);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(request uuu:reqlist){

                    try {

                        oos.writeObject(uuu);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }



        } catch (Exception e) {

            e.printStackTrace();

        }



    }




    public static void restorechanel() throws IOException {

        FileInputStream fis=new FileInputStream(fileofchanel);

        try(ObjectInputStream ois=new ObjectInputStream(fis)){

            boolean tr=true;

            while (tr){

                chanel us=null;

                try{

                    us=(chanel) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(us!=null){
                    chanellist.add(us);
                }else{
                    tr=false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public static void savechanel(){

        try {


            FileOutputStream fos=new FileOutputStream(fileofchanel);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(chanel uuu:chanellist){

                    try {

                        oos.writeObject(uuu);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
            }



        } catch (Exception e) {

            e.printStackTrace();

        }



    }



    public static void restoreUser() throws IOException {

        FileInputStream fis=new FileInputStream(filePaths);

        try(ObjectInputStream ois=new ObjectInputStream(fis)){

            boolean tr=true;

            while (tr){

                User us=null;

                try{

                    us=(User) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(us!=null){
                    userlist.add(us);
                }else{
                    tr=false;
                }
            }




        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void updatemessages()throws FileNotFoundException{

        FileInputStream fis=new FileInputStream(fileOfMessages);

        try(ObjectInputStream ois=new ObjectInputStream(fis)){

            boolean tr=true;

            while (tr){

                ChatMessage cm=null;

                try{

                    cm=(ChatMessage) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(cm!=null){
                    newchatmess.add(cm);
                }else{
                    tr=false;
                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }


        for(ChatMessage ccmm:chatmess){

            if(!newchatmess.contains(ccmm)){

                newchatmess.add(ccmm);

            }

        }

        try {

            FileOutputStream fos=new FileOutputStream(fileOfMessages);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(ChatMessage mfpc:newchatmess){

                    try {

                        oos.writeObject(mfpc);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }



        } catch (Exception e) {

            e.printStackTrace();

        }

        FileInputStream fiiis=new FileInputStream(fileOfMessages);
        try(ObjectInputStream ois=new ObjectInputStream(fiiis)){

            boolean tr=true;

            while (tr){

                ChatMessage mfpc=null;

                try{

                    mfpc=(ChatMessage) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(mfpc!=null){
                    chatmess.add(mfpc);
                }else{
                    tr=false;
                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }



    public static void updatepvmessage() throws FileNotFoundException {

        FileInputStream fis=new FileInputStream(fileOfPV);

        try(ObjectInputStream ois=new ObjectInputStream(fis)){

            boolean tr=true;

            while (tr){

                messageForPrivateChat mfpc=null;

                try{

                    mfpc=(messageForPrivateChat) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(mfpc!=null){
                    newpvmessages.add(mfpc);
                }else{
                    tr=false;
                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }


        for(messageForPrivateChat ccmm:pvMessages){

            if(!newpvmessages.contains(ccmm)){

                newpvmessages.add(ccmm);

            }

        }

        try {


            FileOutputStream fos=new FileOutputStream(fileOfPV);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(messageForPrivateChat mfpc:newpvmessages){

                    try {

                        oos.writeObject(mfpc);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }



        } catch (Exception e) {

            e.printStackTrace();

        }

        FileInputStream fiis=new FileInputStream(fileOfPV);

        try(ObjectInputStream ois=new ObjectInputStream(fiis)){

            boolean tr=true;

            while (tr){

                messageForPrivateChat mfpc=null;

                try{

                    mfpc=(messageForPrivateChat) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(mfpc!=null){
                    pvMessages.add(mfpc);
                }else{
                    tr=false;
                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void restoreserverlist() throws FileNotFoundException {

        FileInputStream fis=new FileInputStream(filePath);

        try(ObjectInputStream ois=new ObjectInputStream(fis)){

            boolean tr=true;

            while (tr){

                madeServer ms=null;

                try{

                    ms=(madeServer) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(ms!=null){
                    serverList.add(ms);
                }else{
                    tr=false;
                }


            }




        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void restoreMessage() throws FileNotFoundException {

        FileInputStream fis=new FileInputStream(fileOfMessages);

        try(ObjectInputStream ois=new ObjectInputStream(fis)){

            boolean tr=true;

            while (tr){

                ChatMessage cm=null;

                try{

                    cm=(ChatMessage) ois.readObject();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(cm!=null){
                    chatmess.add(cm);
                }else{
                    tr=false;
                }


            }




        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void saveUser(){

        try {


            FileOutputStream fos=new FileOutputStream(filePaths);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(User uu:userlist){

                    try {

                        oos.writeObject(uu);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
            }



        } catch (Exception e) {

            e.printStackTrace();

        }



    }
    public static void saveserver(){

        try {


            FileOutputStream fos=new FileOutputStream(filePath);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(madeServer ms:serverList){

                    try {

                        oos.writeObject(ms);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
            }



        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public static void savemessage(){

        try {


            FileOutputStream fos=new FileOutputStream(fileOfMessages);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(ChatMessage cm:savingchatmess){

                    try {

                        oos.writeObject(cm);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
            }



        } catch (Exception e) {

            e.printStackTrace();

        }



    }
    public static void savepvmessage(){

        try {


            FileOutputStream fos=new FileOutputStream(fileOfPV);

            try {

                ObjectOutputStream oos=new ObjectOutputStream(fos);

                for(messageForPrivateChat mfpc:savingpvmessages){

                    try {

                        oos.writeObject(mfpc);


                    } catch (NotSerializableException e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }


                }

                oos.close();
                fos.close();


            } catch (Exception e) {
                //TODO: handle exception
            }



        } catch (Exception e) {

            e.printStackTrace();

        }


    }


}