package com.example.lastversionofproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class pvChatcontroller implements Initializable {

    static User contact;


    private Scene scene;

    private Stage stage;

    private Parent root;

    @FXML
    private Button backbutton,sendingpvmessage,blockinguser,friendshiprequest,unblockuser,mailboxbutton,showblocked,showrequest;

    @FXML
    private javafx.scene.control.TextField username_tf,pvmessage_tf;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private VBox vbox_main;

    public void backtopvandpublic(ActionEvent event) throws IOException {

        Client.savereq();

        root= FXMLLoader.load(Main.class.getResource("pv and public chat.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();



    }

    public void showblocked(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("mailbox.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();



    }
    public void block(ActionEvent event) throws Exception{

        String name=username_tf.getText();

        for(User jj:Client.userlist){
            if(jj.getUsername().equals(name)){

                Client.userOfProgram.addToBlocked(jj);

            }
        }
    }

    public void request(ActionEvent event) throws Exception{

        String name=username_tf.getText();

        for(User jjj:Client.userlist){

            if(name.equals(jjj.getUsername())){

                request rq=new request(Client.userOfProgram.getUsername(), jjj.getUsername());

                Client.reqlist.add(rq);

                jjj.addToWaitingList(Client.userOfProgram);


            }

        }
    }
    public void unblock(ActionEvent event) throws Exception{

        String name=username_tf.getText();

        for(User bb:Client.userOfProgram.blockedUser){

            if(bb.getUsername().equals(name)){

                Client.userOfProgram.rmBlockedUsers(bb);

            }

        }


    }

    public void sendingpvmessage(ActionEvent event)throws Exception{

        String reciever=username_tf.getText();

        for(User ui:Client.userlist){

            if(ui.getUsername().equals(reciever)){

                String messag=pvmessage_tf.getText();

                if(messag!=null){

                    messageForPrivateChat mfpc=new messageForPrivateChat(Client.userOfProgram.getUsername(),ui.getUsername(),messag);

                    Client.pvMessages.add(mfpc);

                    pvmessage_tf.clear();

                }

            }
        }

    }

    public void mailboxshow(ActionEvent event) throws IOException {

        Client.updatepvmessage();

        root= FXMLLoader.load(Main.class.getResource("mailbox.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public void showrequest(ActionEvent event) throws IOException {

        Client.restorereq();

        root= FXMLLoader.load(Main.class.getResource("request.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(User us:Client.userlist){

            HBox hBox=new HBox();

            hBox.setAlignment(Pos.CENTER);

            hBox.setPadding(new javafx.geometry.Insets(5,10,5,10));


            Text text=new Text(us.getUsername());
            TextFlow textflow=new TextFlow(text);


            textflow.setPadding(new Insets(5,10,5,10));
            hBox.getChildren().add(textflow);

            vbox_main.getChildren().add(hBox);

        }

    }
}
