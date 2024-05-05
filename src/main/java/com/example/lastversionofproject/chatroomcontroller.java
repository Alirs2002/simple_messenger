package com.example.lastversionofproject;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class chatroomcontroller implements Initializable {

        String notif="****";

        static ArrayList<String> cmlist=new ArrayList<>();

        chanel cha=publicchatcontroller.getChannel();

        private ObjectInputStream sInput;		// to read from the socket
        private ObjectOutputStream sOutput;		// to write on the socket
        private Socket socket;					// socket object

        private String server, username;	// server and username
        private int port;

        public boolean start() {
            // try to connect to the server
            try {
                socket = new Socket(server, port);
            }
            // exception handler if it failed
            catch(Exception ec) {
                display("Error connectiong to server:" + ec);
                return false;
            }

            String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
            display(msg);

            /* Creating both Data Stream */
            try
            {
                sInput  = new ObjectInputStream(socket.getInputStream());
                sOutput = new ObjectOutputStream(socket.getOutputStream());
            }
            catch (IOException eIO) {
                display("Exception creating new Input/output Streams: " + eIO);
                return false;
            }

            // creates the Thread to listen from the server
            new ListenFromServer(cha).start();
            // Send our username to the server this is the only message that we
            // will send as a String. All other messages will be ChatMessage objects
            try
            {
                sOutput.writeObject(username);
            }
            catch (IOException eIO) {
                display("Exception doing login : " + eIO);
                disconnect();
                return false;
            }
            // success we inform the caller that it worked
            return true;
        }

        /*
         * To send a message to the console
         */
        private void display(String msg) {

            System.out.println(msg);

        }

        /*
         * To send a message to the server
         */
        void sendMessage(ChatMessage msg) {
            try {
                sOutput.writeObject(msg);
            }
            catch(IOException e) {
                display("Exception writing to server: " + e);
            }
        }

        /*
         * When something goes wrong
         * Close the Input/Output streams and disconnect
         */
        private void disconnect() {
            try {
                if(sInput != null) sInput.close();
            }
            catch(Exception e) {}
            try {
                if(sOutput != null) sOutput.close();
            }
            catch(Exception e) {}
            try{
                if(socket != null) socket.close();
            }
            catch(Exception e) {}

        }


        private Scene scene;

        private Stage stage;

        private Parent root;


        @FXML
        private Button removebutton,sendbutton,taggingbutton,showtaggedbutton,backbutton,updatebutton;

        @FXML
        private TextField message_tf,index_tf;

        @FXML
        private ScrollBar sb_main;

        @FXML
        private VBox vbox_main;




        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {



            for(ChatMessage cm:Client.chatmess){

                if(cm.getChanel().equals(cha.getChanelName())){

                    HBox hBox=new HBox();

                    hBox.setAlignment(Pos.CENTER);

                    hBox.setPadding(new Insets(5,10,5,10));


                    Text text=new Text(cm.getMessage());
                    TextFlow textflow=new TextFlow(text);


                    textflow.setPadding(new Insets(5,10,5,10));
                    hBox.getChildren().add(textflow);

                    vbox_main.getChildren().add(hBox);

                }

            }


            updatebutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for(String cmm:cmlist) {


                        HBox hBox = new HBox();

                        hBox.setAlignment(Pos.CENTER);

                        hBox.setPadding(new Insets(5, 10, 5, 10));


                        Text text = new Text(cmm);
                        TextFlow textflow = new TextFlow(text);


                        textflow.setPadding(new Insets(5, 10, 5, 10));
                        hBox.getChildren().add(textflow);

                        vbox_main.getChildren().add(hBox);


                    }
                    cmlist.removeAll(cmlist);
                }
            });


            vbox_main.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    sb_main.setValue((Double) t1);
                }
            });


            int portNumber = 1500;
            String serverAddress = "localhost";
            String userName = "Anonymous";

            chatroomcontroller client = new chatroomcontroller();

            client.server=serverAddress;

            client.port=portNumber;

            client.username=Client.userOfProgram.getUsername();

            // try to connect to the server and return if not connected
            if(!client.start())
                return;


            sendbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {


                    System.out.print("> ");
                    // read message from user
                    String msg = message_tf.getText();

                    if(!msg.isEmpty()){

                        for(User pp:Client.userlist){

                            if(msg.contains(pp.getUsername())){

                                String not= Client.userOfProgram.getUsername()+"has mentioned you in "+msg+"message";

                                messageForPrivateChat sms=new messageForPrivateChat(Client.userOfProgram.getUsername(), pp.getUsername(), not);

                                Client.pvMessages.add(sms);

                            }

                        }


                        // logout if message is LOGOUT

                        // regular text message

                        client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg,cha));

                        ChatMessage cmm=new ChatMessage(ChatMessage.MESSAGE, msg, cha);


                     //   Client.chatmess.add(cmm);

                    //    cha.chatMList.add(cmm);

                     //   HBox hBox = new HBox();

                  //      hBox.setAlignment(Pos.CENTER);

                     //   hBox.setPadding(new Insets(5, 10, 5, 10));


                    //    Text text = new Text(msg);
                    //    TextFlow textflow = new TextFlow(text);


                    //    textflow.setPadding(new Insets(5, 10, 5, 10));
                     //   hBox.getChildren().add(textflow);

                     //   vbox_main.getChildren().add(hBox);

                        message_tf.clear();



                    }
                }
            });

        }




    public void remove(ActionEvent event){

            int inde=Integer.parseInt(index_tf.getText());

            cha.chatMList.remove(inde);

            ChatMessage cm=cha.chatMList.get(inde);

            Client.chatmess.remove(cm);

        }
        public void tagging(ActionEvent event){

            int inde=Integer.parseInt(index_tf.getText());

            ChatMessage cm=cha.chatMList.get(inde);

            cha.setTaggedMessage(cm.getMessage());

        }
        public void showtagged(ActionEvent event){

            System.out.println(cha.getTagged());

        }

        public void goback(ActionEvent event) throws IOException {

            root= FXMLLoader.load(Main.class.getResource("public chat.fxml"));

            stage=(Stage)((Node)event.getSource()).getScene().getWindow();

            scene=new Scene(root);

            stage.setScene(scene);

            stage.show();

        }



        class ListenFromServer extends Thread {

            private chanel cha;


            public ListenFromServer (chanel c){

                this.cha=c;
            }

            public void run() {
                while(true) {
                    try {

                        String msg=(String) sInput.readObject();

                        String[] zaza=msg.split(":");

                        chanel chan=null;

                        for(chanel ch:serverlogincontroller.getServer().chanellist){

                            if(zaza[0].equals(ch.getChanelName())){

                                chan=ch;

                            }


                        }

                        ChatMessage cmc=new ChatMessage(ChatMessage.MESSAGE, zaza[1]+":"+zaza[2], chan);

                        Client.chatmess.add(cmc);

                        cmlist.add(msg);

                        cha.chatMList.add(cmc);

                        if(zaza[0].equals(cha.getChanelName())){
                            System.out.println(zaza[1]+":"+zaza[2]);


                        }

                        // read the message form the input datastream

                    }
                    catch(IOException e) {
                        display(notif + "Server has closed the connection: " + e + notif);
                        break;
                    }
                    catch(ClassNotFoundException e2) {
                    }
                }
            }
        }
    }





