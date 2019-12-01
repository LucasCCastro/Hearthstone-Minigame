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

        return("file:./images/teste.png");

    }

    public ImageView createImage(String imgId){
        Image img = images.get(imgId);
        if (img == null){
            img = new Image(id2File(imgId));
            images.put(imgId,img);
        }

        //Image img = new Image(id2File(imgId));
        return(new ImageView(img));
    }
}

        
        
    

