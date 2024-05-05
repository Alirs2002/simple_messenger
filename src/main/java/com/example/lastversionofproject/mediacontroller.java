package com.example.lastversionofproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class mediacontroller implements Initializable {

    private Scene scene;

    private Stage stage;

    private Parent root;

    @FXML
    private Button playbutton,pausebutton,resetbutton;

    @FXML
    private MediaView mv;

    private File file;

    private Media media;

    private MediaPlayer mediaplayer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(githubcontroller.get_index()==1) {

            file = new File("C:\\Users\\Partorayan\\Desktop\\videos of project\\1.mp4");
        }else if(githubcontroller.get_index()==2){

            file = new File("C:\\Users\\Partorayan\\Desktop\\videos of project\\2.mp4");

        }
        else if(githubcontroller.get_index()==3){

            file = new File("C:\\Users\\Partorayan\\Desktop\\videos of project\\3.mp4");

        }
        else if(githubcontroller.get_index()==4){

            file = new File("C:\\Users\\Partorayan\\Desktop\\videos of project\\4.mp4");

        }
        media=new Media(file.toURI().toString());

        mediaplayer=new MediaPlayer(media);

        mv.setMediaPlayer(mediaplayer);

    }

    public void play(ActionEvent event){

        mediaplayer.play();

    }
    public void pause(ActionEvent event){

        mediaplayer.pause();

    }
    public void reset(ActionEvent event){

        if(mediaplayer.getStatus()!=MediaPlayer.Status.READY) {

            mediaplayer.seek(Duration.seconds(0.0));
        }

    }
    public void goback(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("github.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();



    }


}
