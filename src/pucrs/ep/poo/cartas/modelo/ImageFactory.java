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
            case "img11" : return("file:./images/eleven.png");
            case "img12" : return("file:./images/twelve.png");
            case "img13" : return("file:./images/thirteen.png");
            case "img14" : return("file:./images/fourteen.png");
            case "img15" : return("file:./images/fifteen.png");
            case "img16" : return("file:./images/sixteen.png");
            case "img17" : return("file:./images/seventeen.png");
            case "img18" : return("file:./images/eighteen.png");
            case "img19" : return("file:./images/nineteen.png");
            case "img20" : return("file:./images/twenty.png");
            case "img21" : return("file:./images/twentyone.png");
            case "img22" : return("file:./images/twentytwo.png");
            case "img23" : return("file:./images/twentythree.png");
            case "img24" : return("file:./images/twentyfour.png");
            case "img25" : return("file:./images/twentyfive.png");
            case "img26" : return("file:./images/twentysix.png");
            case "img27" : return("file:./images/twentyseven.png");
            case "img28" : return("file:./images/twentyeight.png");
            case "img29" : return("file:./images/twentynine.png");
            case "img30" : return("file:./images/thirty.png");
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

        
        
    

