package com.example.lastversionofproject;


import java.io.*;
import java.util.*;
import java.net.*;

// Server class
public class privateChat {

    public static ArrayList<ClientHandler> userList = new ArrayList<>();

    public static ArrayList<messageForPrivateChat> privateMessages = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3344);

        while (true) {

            Socket soc = ss.accept();

            System.out.println("new client has connected ...");

            InputStream is = soc.getInputStream();

            OutputStream os = soc.getOutputStream();

            ClientHandler ch = new ClientHandler(soc, is, os);

            Thread t = new Thread(ch);

            userList.add(ch);

            t.start();

        }

    }

    public static void addToPrivateMessage(messageForPrivateChat mfpc) {
        privateMessages.add(mfpc);
    }

    public static void rmMessage(messageForPrivateChat mfpc) {
        privateMessages.remove(mfpc);
    }

}

class ClientHandler implements Runnable {

    Scanner sc = new Scanner(System.in);

    private String sender;

    private String reciever;

    private String message;

    final InputStream is;

    final OutputStream os;

    Socket s;

    public ClientHandler(Socket s, InputStream is, OutputStream os) {

        this.s = s;

        this.is = is;

        this.os = os;

    }

    @Override
    public void run() {

        messageForPrivateChat mfpc;

        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            while (true) {

                try {
                    mfpc = (messageForPrivateChat) ois.readObject();
                    privateChat.addToPrivateMessage(mfpc);

                } catch (IOException e) {

                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
