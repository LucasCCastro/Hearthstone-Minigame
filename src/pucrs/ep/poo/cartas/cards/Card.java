package pucrs.ep.poo.cartas.cards;

import java.util.*;



public class Card extends Observable{
    private String id;
    private String imageId;
    private int cost;
    private boolean faceUp;
    
    public Card(String anId,String anImageId){
        this.id = anId;
        this.imageId = anImageId;
        this.faceUp = true;
    }
    
    public String getId(){
        return(id);
    }
    
    public String getImageId(){
        return(imageId);
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
        

