
package weka;

import java.awt.Color;
import static java.awt.Color.red;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.rgb;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class Fenetre1 extends Application{

    public static void main(String[] args) {
        // launch permet de lancer la methode start
        launch(args);
    }
    
    public void start(Stage stage) throws Exception {
        
               
        
        stage.setX(150);
        stage.setY(20);
        stage.setTitle("Speed Search");
        stage.show();
        
        BorderPane borderGenerale=new BorderPane();
        Scene scene=new Scene(borderGenerale,1000,650);
        stage.setScene(scene);
        
            
                
        FileInputStream inputstream = new FileInputStream("C:\\Users\\user\\Desktop\\capture1.png"); 
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(450);
        
        TextField tf= new TextField();
        tf.setMinHeight(35);
        tf.setMinWidth(400);
        
        
        Button btn1=new Button("Recherche");
        btn1.setMinHeight(35);       
        
        //btn1.setStyle("-fx-background-color: rgb(242,114,39);");
        //btn1.setTextFill(rgb(244,244,244));
        
        HBox hb1=new HBox(10);
        hb1.getChildren().add(tf);
        hb1.getChildren().add(btn1);
        hb1.setAlignment(Pos.CENTER);
        hb1.setPadding(new Insets(10,10,10,10));
        
        VBox vb1=new VBox(50);
        vb1.getChildren().add(imageView);
        vb1.getChildren().add(hb1);
        vb1.setAlignment(Pos.CENTER); 
        borderGenerale.setCenter(vb1);
        
        btn1.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent AE)
            {
               Fenetre2 f2=new Fenetre2(); 
                try {
                    f2.afficherFenetre2(tf.getText(),stage);
                } 
                catch (IOException ex) {
                    System.out.println("erreur");
                }
            }
        });  
        
        
    }        
    
    
    
}
