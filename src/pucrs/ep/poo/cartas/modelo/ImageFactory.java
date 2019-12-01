package pucrs.ep.poo.cartas.modelo;

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageFactory{
    private static ImageFactory imgf = new ImageFactory();
    private Map<String,Image> images;

    public static ImageFactory getInstance(){
        return(imgf);
    }

    private ImageFactory(){
        images = new HashMap<>();
    }

    private String id2File(String imgId){

        switch(imgId){
            case "img1" : return("file:./images/one.png");
            case "img2" : return("file:./images/two.png");
            case "img3" : return("file:./images/three.png");
            case "img4" : return("file:./images/four.png");
            case "img5" : return("file:./images/five.png");
            case "img6" : return("file:./images/six.png");
            case "img7" : return("file:./images/seven.png");
            case "img8" : return("file:./images/eight.png");
            case "img9" : return("file:./images/nine.png");
            case "img10" : return("file:./images/ten.png");
            case "imgBck" : return("file:./images/back.png");
            default: throw new IllegalArgumentException("Invalid image Id");
        }


    }

    public ImageView createImage(String imgId){
        Image img = images.get(imgId);
        if (img == null){
            img = new Image(id2File(imgId));
            images.put(imgId,img);
        }

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(128);
        imgView.setFitHeight(193.5);
       // imgView.setPreserveRatio(true);
        //Image img = new Image(id2File(imgId));
        return imgView;
    }
}

        
        
    

