package com.example.lastversionofproject;



import java.io.*;
import java.net.*;
import java.util.*;


public class publicChat {

    private static int uniqueId;
    private ArrayList<ClientThread> al;
    private int port;
    private boolean keepGoing;
    // notification
    private String notif = " *** ";
    private ChatMessage cm;



    static ArrayList<ChatMessage> cmd=new ArrayList<>();


    public publicChat(int port) {
        // the port
        this.port = port;
        // an ArrayList to keep the list of the Client
        al = new ArrayList<ClientThread>();
    }

    public void start() {
        keepGoing = true;
        //create socket server and wait for connection requests
        try
        {
            // the socket used by the server
            ServerSocket serverSocket = new ServerSocket(port);

            // infinite loop to wait for connections ( till server is active )
            while(keepGoing)
            {
                display("Server waiting for Clients on port " + port + ".");

                // accept connection if requested from client
                Socket socket = serverSocket.accept();
                // break if server stoped
                if(!keepGoing)
                    break;
                // if client is connected, create its thread
                ClientThread t = new ClientThread(socket);
                //add this client to arraylist
                al.add(t);

                t.start();
            }
            // try to stop the server
            try {
                serverSocket.close();
                for(int i = 0; i < al.size(); ++i) {
                    ClientThread tc = al.get(i);
                    try {
                        // close all data streams and socket
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    }
                    catch(IOException ioE) {
                    }
                }
            }
            catch(Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        }
        catch (IOException e) {
            String msg = " Exception on new ServerSocket: " + e + "\n";
            display(msg);
        }
    }

    // to stop the server
    protected void stop() {
        keepGoing = false;
        try {
            new Socket("localhost", port);
        }
        catch(Exception e) {
        }
    }

    // Display an event to the console
    private void display(String msg) {
        System.out.println(msg);
    }



    private synchronized boolean broadcast(String message) {

        String[] w = message.split(" ");




        String messageLf = message + "\n"; //add time if needed
        // display message
        System.out.print(messageLf);

        // we loop in reverse order in case we would have to remove a Client
        // because it has disconnected
        for(int i = al.size(); --i >= 0;) {
            ClientThread ct = al.get(i);
            // try to write to the Client if it fails remove it from the list
            if(!ct.writeMsg(messageLf)) {
                al.remove(i);
                display("Disconnected Client " + ct.username + " removed from list.");
            }
        }

        return true;
    }

    synchronized void remove(int id) {

        String disconnectedClient = "";
        for(int i = 0; i < al.size(); ++i) {
            ClientThread ct = al.get(i);
            // if found remove it
            if(ct.id == id) {
                disconnectedClient = ct.getUsername();
                al.remove(i);
                break;
            }
        }
        broadcast(notif + disconnectedClient + " has left the chat room." + notif);
    }


    public static void main(String[] args) {

        try {

            FileInputStream fis=new FileInputStream("D:\\RD_Messanger\\chatMessages.txt");

            try (ObjectInputStream ois=new ObjectInputStream(fis)) {

                boolean tr=true;

                while(tr){

                    ChatMessage mes=null;

                    try {
                        mes=(ChatMessage) ois.readObject();

                    } catch (Exception e) {
                        //TODO: handle exception

                        e.printStackTrace();
                    }

                    if(mes!=null){

                        cmd.add(mes);

                    }else{
                        tr=false;

                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
                //TODO: handle exception
            }

        } catch (Exception e) {
            //TODO: handle exception

            e.printStackTrace();
        }







        // start server on port 1500 unless a PortNumber is specified
        int portNumber = 1500;

        // create a server object and start it
        publicChat server = new publicChat(portNumber);
        server.start();
    }

    // One instance of this thread will run for each client
    class ClientThread extends Thread {
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        int id;
        String username;
        ChatMessage cm;

        // Constructor
        ClientThread(Socket socket) {
            // a unique id
            id = ++uniqueId;
            this.socket = socket;
            //Creating both Data Stream
            System.out.println("Thread trying to create Object Input/Output Streams");
            try
            {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput  = new ObjectInputStream(socket.getInputStream());
                // read the username
                username = (String) sInput.readObject();
                broadcast(notif + username + " has joined the chat room." + notif);
            }
            catch (IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            }
            catch (ClassNotFoundException e) {
            }
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // infinite loop to read and forward message
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while(keepGoing) {
                // read a String (which is an object)
                try {
                    cm = (ChatMessage) sInput.readObject();
                    cmd.add(cm);
                }
                catch (IOException e) {
                    display(username + " Exception reading Streams: " + e);
                    break;
                }
                catch(ClassNotFoundException e2) {
                    break;
                }
                // get the message from the ChatMessage object received
                String message = cm.getMessage();

                // different actions based on type message
                switch(cm.getType()) {

                    case ChatMessage.MESSAGE:
                        boolean confirmation =  broadcast(cm.getChanel()+":"+username+":"+message);
                        if(confirmation==false){
                            String msg = notif + "Sorry. No such user exists." + notif;
                            writeMsg(msg);
                        }
                        break;
                    case ChatMessage.LOGOUT:
                        display(username + " disconnected with a LOGOUT message.");
                        keepGoing = false;
                        break;
                    case ChatMessage.WHOISIN:
                        writeMsg("List of the users connected " + "\n");
                        // send list of active clients
                        for(int i = 0; i < al.size(); ++i) {
                            ClientThread ct = al.get(i);
                            writeMsg((i+1) + ") " + ct.username );
                        }
                        break;
                }
            }
            // if out of the loop then disconnected and remove from client list
            remove(id);
            close();
        }

        // close everything
        private void close() {
            try {
                if(sOutput != null) sOutput.close();
            }
            catch(Exception e) {}
            try {
                if(sInput != null) sInput.close();
            }
            catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            }
            catch (Exception e) {}
        }

        private boolean writeChatMessage(ChatMessage cm){

            if(!socket.isConnected()){

                close();

                return false;

            }

            try {

                sOutput.writeObject(cm);

            } catch (Exception e) {
                //TODO: handle exception

                display(notif + "Error sending message to " + username + notif);
                display(e.toString());
            }


            return true;
        }

        // write a String to the Client output stream
        private boolean writeMsg(String msg) {
            // if Client is still connected send the message to it
            if(!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
            }
            // if an error occurs, do not abort just inform the user
            catch(IOException e) {
                display(notif + "Error sending message to " + username + notif);
                display(e.toString());
            }
            return true;
        }
    }

    public static void save() throws FileNotFoundException{

        FileOutputStream fos=new FileOutputStream(Client.fileOfMessages);


        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {


            for(ChatMessage cx:cmd){

                if(cx!=null){


                    oos.writeObject(cx);

                }

            }

        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}