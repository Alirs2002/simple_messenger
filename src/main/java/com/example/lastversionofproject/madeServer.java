package com.example.lastversionofproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class madeServer implements Serializable {

    public static ArrayList<madeServer> serverlist = new ArrayList<madeServer>();
    public  ArrayList<chanel> chanellist = new ArrayList<>();
    public  ArrayList<User> userlist = new ArrayList<>();

    public static final String filePath = "D:\\RD_Messanger\\serverList.txt";

    private String name;

    private String Admin;

    public madeServer(String name, String Admin) {

        this.name = name;

        this.Admin = Admin;

    }

    public void rmUser(User u){

        Iterator it=userlist.iterator();

        while(it.hasNext()){
            if(it.next().equals(u)){
                it.remove();
            }
        }

    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return Admin;
    }

    public String getServerName() {
        return name;
    }

    public void addServer() {

        serverlist.add(this);

    }

    public void displayServers() {

        int i = 1;

        for (madeServer servers : serverlist) {
            System.out.println(i + ":" + servers.getServerName());

            i++;
        }
    }

    public void addChanel(chanel chanel) {

        chanellist.add(chanel);

        for (User uss : userlist) {

            chanel.addusers(uss);

        }

    }

    public void chanelsDisplay() {
        int i = 1;
        for (chanel show : chanellist) {
            System.out.println(i + ":" + show.getChanelName());
            i++;
        }
    }

    public String getName() {
        return name;
    }

    public void addUser(User user) {
        userlist.add(user);
    }

    int k = 1;

    public void displayUserList() {
        for (User listtoshow : userlist) {


            String str = k+(listtoshow.getUsername());

            System.out.println(str);

            k++;

        }
    }

    public User getuser(int in) {
        return userlist.get(in);
    }

    public void saveServer() {

        for (madeServer serverss : serverlist) {

            try {
                FileOutputStream fos = new FileOutputStream(filePath);

                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(serverss);

                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }



}
