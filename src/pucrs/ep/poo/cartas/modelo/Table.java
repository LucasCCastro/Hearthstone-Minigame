package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.cards.Card;
import pucrs.ep.poo.cartas.gui.GameEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Table extends Observable{

        public static final int MAXIMUMSIZE = 7;
        private List<Card> tableCards;
       // private Card selected;

        public Table(){

            tableCards = new ArrayList<>();
           // selected = null;
        }

        public List<Card> getTableCards(){
            return(tableCards);
        }

        public int getNumberOfTableCards(){
            return(tableCards.size());
        }

//        public void setSelectedCard(Card card){
//            selected = card;
//        }

//        public Card getSelectedCard(){
//            return(selected);
//        }

}
