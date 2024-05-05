package com.example.lastversionofproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import java.io.IOException;
import java.net.URISyntaxException;

public class resourcescontroller {

    @FXML
    private Button backbutton,w3button,awesbutton,geeksbutton,programmizbutton,ppezbutton;


    private Scene scene;

    private Stage stage;

    private Parent root;


    public void goback(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("tutorial.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();



    }
    public void gow3(ActionEvent event){

        try {

            URI uri= new URI("https://www.w3schools.com/java/default.asp");

            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");

        } catch (Exception e) {

            e.printStackTrace();
        }


    }
    public void goawes(ActionEvent event){

        try {

            URI uri= new URI("https://www.androidauthority.com/java-tutorial-for-beginners-write-a-simple-app-with-no-previous-experience-1121975/");

            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");

        } catch (Exception e) {

            e.printStackTrace();
        }


    }
    public void gogeeks(ActionEvent event) throws URISyntaxException, IOException {

        try{
            URI uri= new URI("https://www.geeksforgeeks.org/java/?ref=shm");

            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void goprogrammiz(ActionEvent event){

        try{
            URI uri= new URI("https://www.programiz.com/java-programming");

            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");

        } catch (Exception e) {

            e.printStackTrace();
        }


    }
    public void godeveloppez(ActionEvent event){

        try{
            URI uri= new URI("https://java.developpez.com/");

            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


}
