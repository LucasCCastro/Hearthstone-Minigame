package pucrs.ep.poo.cartas.modelo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Table extends Observable{

    public static final int MAXIMUMSIZE = 11;
    private List<Card> tableCards;
    private Card selected;


    public Table() {
        CardsInitializer initializer = new CardsInitializer();
        tableCards = new ArrayList<>();
        selected = null;

        for (int i = 0; i < MAXIMUMSIZE; i++) {
            tableCards.add(initializer.returnARandomCard());
        }

    }


    public List<Card> getTableCards(){
        return(tableCards);
    }

    public int getNumberOfTableCards(){
        return(tableCards.size());
    }

    public boolean addCardToTheTable(Card aCard) {
        if(aCard==null) return false;

        if(tableCards.size() < MAXIMUMSIZE) {
            tableCards.add(aCard);
            return true;
        }
        return false;
    }

    public boolean removeCardFromTable(Card aCard) {
        if(aCard != null) {
            tableCards.remove(aCard);
            return true;
        }
        return false;
    }


        public void setSelectedCard(Card card){
            selected = card;
        }

        public Card getSelectedCard(){
            return(selected);
        }




}
