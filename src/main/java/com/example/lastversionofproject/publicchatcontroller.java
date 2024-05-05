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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class publicchatcontroller implements Initializable {

    String newname;

    public static chanel cha;

    public ArrayList<chanel> chanlist=new ArrayList<>();


    @FXML
    private Button createbutton,backtopvandpublic,nextbutton;

    @FXML
    private javafx.scene.control.TextField newchannelfield,chanelfield;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private VBox vbox_main;


    private Scene scene;

    private Stage stage;

    private Parent root;

    public static chanel getChannel(){
        return cha;
    }
    public void setChannel(chanel ch){
        this.cha=ch;
    }


    public void gotopvandpublic(ActionEvent event) throws IOException {

        root= FXMLLoader.load(Main.class.getResource("pv and public chat.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        scene=new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
    public boolean createchannel(){

        boolean hamed=false;

        newname=newchannelfield.getText();

        if(newname!=null) {


            chanel ch = new chanel(newname,serverlogincontroller.getServer().getServerName());

            cha=ch;

            serverlogincontroller.getServer().addChanel(cha);

            cha.fillUserlist(Client.userlist);

            Client.chanellist.add(cha);

            hamed=true;

        }

        return hamed;

    }

    public void gotopublicchat(ActionEvent event) throws IOException {

        if(createchannel()) {

            Client.updatemessages();

            root = FXMLLoader.load(Main.class.getResource("Chatroom.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
        }


    }

    public boolean searchChannel(String nameofchannel){

        boolean kd=false;

        for(chanel ch:Client.chanellist){

            if(nameofchannel.equals(ch.getChanelName())){

                cha=ch;
                kd=true;
            }

        }

        return kd;

    }

    public void channelenter(ActionEvent event) throws IOException {

        if(searchChannel(chanelfield.getText())){

            Client.updatemessages();

            root = FXMLLoader.load(Main.class.getResource("Chatroom.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(root);

            stage.setScene(scene);

            stage.show();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(chanel ch:Client.chanellist){

            if(ch.getserver().equals(serverlogincontroller.getServer().getServerName())) {

                HBox hBox = new HBox();

                hBox.setAlignment(Pos.CENTER);

                hBox.setPadding(new Insets(5, 10, 5, 10));


                Text text = new Text(ch.getChanelName());
                TextFlow textflow = new TextFlow(text);


                textflow.setPadding(new Insets(5, 10, 5, 10));
                hBox.getChildren().add(textflow);

                vbox_main.getChildren().add(hBox);

            }
        }

    }

}
