package pucrs.ep.poo.cartas.modelo;

import java.util.*;



public class Card extends Observable{
    private String id;
    private String imageId;
    private int value;
    private boolean faceUp;

    public Card(String anId,String anImageId,int val){
        id = anId;
        imageId = anImageId;
        value = val;
        faceUp = true;
    }
    public Card(String imageId){
        this.imageId = imageId;
    }

    public String getId(){
        return(id);
    }

    public String getImageId(){
        return(imageId);
    }

    public int getValue(){
        return(value);
    }

    public boolean isFacedUp(){
        return(faceUp);
    }

    public void flip(){
        if (faceUp == true){
            faceUp = false;
        }else{
            faceUp = true;
        }
        setChanged();
        notifyObservers();
    }
}
        

