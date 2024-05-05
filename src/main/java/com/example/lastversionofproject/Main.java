package com.example.lastversionofproject;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


public class Main extends Application{

    public Main(){}
    @Override
    public void start(Stage stage) throws Exception {


            FXMLLoader fxmlloader= new FXMLLoader(Main.class.getResource("first.fxml"));

            Scene scene = new Scene((Parent) fxmlloader.load(),600.0D,400.0D);

            stage.setTitle("RD Messenger");

            stage.setScene(scene);

            stage.show();

    }

    public static void main(String[] args) throws IOException {

        Client.restoreserverlist();

        Client.restoreUser();

        Client.restorechanel();

        Client.restorereq();

        Client.updatemessages();

        Client.updatepvmessage();


        launch();
    }
}
