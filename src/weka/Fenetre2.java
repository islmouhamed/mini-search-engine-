
package weka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.stemmers.LovinsStemmer;
import weka.core.tokenizers.WordTokenizer;

public class Fenetre2 {
    
     public void afficherFenetre2 (String s,Stage stage) throws IOException 
    {
      /**************************************WEKA******************************************/ 
        ArffLoader source1= new ArffLoader();
        source1.setSource(new File("C:\\Users\\user\\Desktop\\data2.arff"));
        Instances listInstances = source1.getDataSet();
        
        //creation d'une instance vide
        ArrayList L=new ArrayList();
        for(int i=0;i<listInstances.numAttributes();i++)
            {
            L.add(listInstances.attribute(i));
            }
        Instances Request=new Instances("request",L,1);
        
        Instance Inst1=new DenseInstance(Request.numAttributes());
        for(int i=0;i<Inst1.numAttributes();i++)
            {
            Inst1.setValue(i, 0);
            }
        Request.add(Inst1);
         
        WordTokenizer Tk=new WordTokenizer() ;
        Tk.setDelimiters(",;:(){}\\-_0123456789 '.?!$'\\\"<>\\r\\n\\t&/#");
        Tk.tokenize(s);
        
        LovinsStemmer LVS= new LovinsStemmer();
        
        while(Tk.hasMoreElements())
        {    
        String k=LVS.stem(Tk.nextElement());
        
            for(int i=1;i<listInstances.numAttributes();i++)
            {
                
            if(k.compareToIgnoreCase(listInstances.attribute(i).name())==0)
            {
                Inst1.setValue(i,1);
            }
             
            }
        }
        double []cos=new double[listInstances.numInstances()];
        double produit;
        double lR;
        double lD; 
        
        for(int i=0;i<listInstances.numInstances();i++)
            {
              produit=0;
              lR=0;
              lD=0;
              for(int j=1;j<listInstances.numAttributes();j++)
              {
                lR=lR+pow((Inst1.value(j)), 2);
                lD=lD+pow((listInstances.get(i).value(j)), 2);        
                produit=produit+((Inst1.value(j))*(listInstances.get(i).value(j)));
              }
              cos[i]=produit/(sqrt(lR)*sqrt(lD));
            }
        
        ListView lv1=new ListView();
        int cpt=0;
        String[] array=null;
        ArrayList chemin=new ArrayList();
        double numC=listInstances.get(0).value(listInstances.attribute(0));
        File f=new File ("C:\\Users\\user\\Desktop\\tpri\\corpus\\"+listInstances.get(0).stringValue(listInstances.attribute(0)));
        array=f.list(); 
            
        ////lv1.setStyle("-fx-selection-bar:rgb(242,114,39); -fx-border-color:rgb(147,137,83);");
        
        for(int i=0;i<listInstances.numInstances();i++)
            {
            if(numC != listInstances.get(i).value(listInstances.attribute(0) ) )
            {
            numC = listInstances.get(i).value(listInstances.attribute(0));    
            cpt=0; 
            f=new File ("C:\\Users\\user\\Desktop\\tpri\\corpus\\"+listInstances.get(i).stringValue(listInstances.attribute(0)));
            array=f.list(); 
            }     
            
            chemin.add(listInstances.get(i).stringValue(listInstances.attribute(0))+"\\"+array[cpt]);
            cpt++;
            }
            
          double max=cos[0];
          int indexMax=0;
          int valeurNotNull=0;
          for(int j=0;j<cos.length;j++)
            {
            if(cos[j]>0 ) 
               {
                valeurNotNull++;   
               }    
            }
            for(int k=0;k < valeurNotNull;k++)
            {
            max=cos[0];
            for(int i=0;i<cos.length;i++)
            {
            if(cos[i]>=max && cos[i]>0 ) 
              {
               max=cos[i];  
               indexMax=i;
                   
              }
            }
            System.out.println("Document:"+chemin.get(indexMax)+" Cosinus:"+cos[indexMax]);    
            cos[indexMax]=cos[indexMax]*(-1);
            lv1.getItems().add(chemin.get(indexMax));
            }
            
         
        /*************************************************************************/
           
        //l'objet scene c'est fenete globale blanc et pour cree cette fennetre il faut cree un layout 'ou conteneur' (BorderPane)
        BorderPane borderGenerale=new BorderPane();
        Scene scene=new Scene(borderGenerale,1000,650);
        stage.setScene(scene);
        
        //l'objet Hbox c'est un conteneur pour alligner les elements horisantalement
        HBox hb1=new HBox();
        HBox hbb1=new HBox(10);
        
        FileInputStream inputstream = null; 
         try {
             inputstream = new FileInputStream("C:\\Users\\user\\Desktop\\Capture1.png");
         }
         catch (FileNotFoundException ex) {
             System.out.println("erreur de chargement");
         }
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(65);
        imageView.setFitWidth(195);
        
        TextField tf= new TextField();
        tf.setMinWidth(400);
        tf.setMaxHeight(70);
        
        Button btn1=new Button("Recherche");
        btn1.setMaxHeight(35);
        
        hbb1.getChildren().add(tf);
        hbb1.getChildren().add(btn1);
        hbb1.setPadding(new Insets(20,10,10,70));
        hbb1.setAlignment(Pos.CENTER);
        
        //hb1.setPadding:l'espace entre Hbox et borderGenerale
        hb1.setPadding(new Insets(10));
        //hb1.setSpacing:l'espace entre les composent de Hbox
        hb1.setSpacing(10);
        hb1.getChildren().add(imageView);
        hb1.getChildren().add(hbb1);
        // on met hb1 on haut de borderGenerale;
        borderGenerale.setTop(hb1);
        
        //l'objet Vbox c'est un conteneur pour alligner les elements verticalement
        VBox vb1=new VBox();
        vb1.getChildren().add(lv1);
        vb1.setPadding(new Insets(10));
        // on met vb1 au centre de borderGenerale;
        borderGenerale.setCenter(vb1);
         
        btn1.setOnAction((ActionEvent AE) -> {
            try {
                afficherFenetre2(tf.getText(),stage);
            } catch (IOException ex) {
                Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });  
        
        lv1.setOnMouseClicked((MouseEvent ME) -> {
            Fenetre3 f3=new Fenetre3();
            f3.afficherFenetre3(lv1.getSelectionModel().getSelectedItem().toString());
        }); 
    }  

    
}
