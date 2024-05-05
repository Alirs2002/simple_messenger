package com.example.lastversionofproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class musiccontroller implements Initializable {

    private Scene scene;

    private Stage stage;

    private Parent root;

    @FXML
    private Pane pane;

    @FXML
    private Button backbutton,playbutton,pausebutton,nextbutton,previousbutton,resetbutton;

    @FXML
    private Slider volumeslider;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private Label label;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private int songnumber;

    private Timer timer;

    private TimerTask task;

    private boolean running;

    private Media media;
    private MediaPlayer mediaplayer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        songs=new ArrayList<File>();

        directory=new File("C:\\Users\\Partorayan\\Desktop\\music");

        files=directory.listFiles();

        if(files!=null){

            for(File file:files){

                songs.add(file);


            }

        }

        media=new Media(songs.get(songnumber).toURI().toString());

        mediaplayer=new MediaPlayer(media);

        label.setText(songs.get(songnumber).getName());

        volumeslider.valueProperty().addListener(new ChangeListener<Number>(){


            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaplayer.setVolume(volumeslider.getValue()*0.01);
            }
        });

    }

    public void goback(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("tutorial.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();




    }
    public void playmedia(){

        begintimer();

        mediaplayer.setVolume(volumeslider.getValue()*0.01);

        mediaplayer.play();

    }

    public void pausemedia(){

        canceltimer();

        mediaplayer.pause();

    }
    public void resetmedia(ActionEvent event){

        progressbar.setProgress(0);

        mediaplayer.seek(Duration.seconds(0.0));


    }
    public void nextmedia(ActionEvent event){

        if(songnumber<songs.size()){

            songnumber++;
            mediaplayer.stop();

            if(running){
                canceltimer();
            }


            media=new Media(songs.get(songnumber).toURI().toString());

            mediaplayer=new MediaPlayer(media);

            label.setText(songs.get(songnumber).getName());

            playmedia();
        }else{
            songnumber=0;
            mediaplayer.stop();

            if(running){
                canceltimer();
            }


            media=new Media(songs.get(songnumber).toURI().toString());

            mediaplayer=new MediaPlayer(media);

            label.setText(songs.get(songnumber).getName());

            playmedia();

        }

    }
    public void previousmedia(ActionEvent event){


        if(songnumber>0){

            songnumber--;
            mediaplayer.stop();

            if(running){
                canceltimer();
            }


            media=new Media(songs.get(songnumber).toURI().toString());

            mediaplayer=new MediaPlayer(media);

            label.setText(songs.get(songnumber).getName());

            playmedia();
        }else{
            songnumber= (songs.size()-1);
            mediaplayer.stop();

            if(running){
                canceltimer();
            }


            media=new Media(songs.get(songnumber).toURI().toString());

            mediaplayer=new MediaPlayer(media);

            label.setText(songs.get(songnumber).getName());

            playmedia();

        }


    }
    public void begintimer(){

        timer=new Timer();

        task=new TimerTask() {
            @Override
            public void run() {

                running=true;

                double current=mediaplayer.getCurrentTime().toSeconds();

                double end=media.getDuration().toSeconds();

                System.out.println(current/end);
                progressbar.setProgress(current/end);

                if(current/end==1){

                    canceltimer();

                }

            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }
    public void canceltimer(){

        running=false;
        timer.cancel();

    }


}
