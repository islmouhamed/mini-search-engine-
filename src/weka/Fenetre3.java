
package weka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Fenetre3 {
    
     public void afficherFenetre3(String ligne)
    {

       
        
   
        try {
            
            BufferedReader lecteurAvecBuffer = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\tpri\\corpus\\"+ligne));
                    
           Stage stage3=new Stage();
            stage3.setX(150);
            stage3.setY(20);
            
            stage3.setTitle(ligne);
            ScrollPane borderGenerale2=new ScrollPane();
            Scene scene2=new Scene(borderGenerale2,1000,650);
            stage3.setScene(scene2);
            stage3.show();
            VBox vb2=new VBox();
            Text v=new Text();
            
            vb2.setPadding(new Insets(10));
            StringBuffer chaine=new StringBuffer();
            String c;
            
            
            while((c=lecteurAvecBuffer.readLine())!=null)
            {   
                chaine.append(c);
                chaine.append("\n");
            } 
            
            v.setText(chaine.toString());
            
            vb2.getChildren().add(v);
                   
            lecteurAvecBuffer.close();
            borderGenerale2.setContent(vb2);
            
            }
        
        catch (IOException ex) {
            System.out.println("erreur de chargement");
            }
        }
    
}
