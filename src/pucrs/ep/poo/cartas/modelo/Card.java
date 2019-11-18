package pucrs.ep.poo.cartas.modelo;

import java.util.*;

public class Card extends Observable{
    private String id;
    private String imageId;
    private int cost;
    //private boolean faceUp = true;

    public Card(String anId, String anImageId, int cost){
        this.cost = cost;
        this.id = anId;
        this.imageId = anImageId;
       // this.faceUp = true;
    }

    public Card(String anImageId){

        this.imageId = anImageId;
    }

    public Card(String anId, String anImageId) {
        this.id = anId;
        this.imageId = anImageId;
    }

    public String getId(){
        return(id);
    }
    
    public String getImageId(){
        return(imageId);
    }

    public int getCost() { return cost; }

//   public boolean isFacedUp(){
//        return(faceUp);
//   }
//
//   public void flip(){
//        if (faceUp == true){
//           faceUp = false;
//       }else{
//            faceUp = true;
//       }
//       setChanged();
//       notifyObservers();
//    }
}
        

