package pucrs.ep.poo.cartas.gui;

import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import pucrs.ep.poo.cartas.modelo.Card;
import pucrs.ep.poo.cartas.modelo.CardDeck;
import pucrs.ep.poo.cartas.modelo.CardObserver;
import pucrs.ep.poo.cartas.modelo.*;

public class DeckView extends HBox implements CardObserver,Observer{
    private int jogador;
    private CardDeck cDeck;
    private Card selectedCard;


    public DeckView(int nroJog){
        super(4);
        this.setAlignment(Pos.CENTER);
        
        jogador = nroJog;
        selectedCard = null;
        
        cDeck = null;
        if (jogador == 1){
            cDeck = Game.getInstance().getDeckP1();
        }else{
            cDeck = Game.getInstance().getDeckP2();
        }
        cDeck.addObserver(this);
        
        for(Card card:cDeck.getCards()){
            CardView cv = new CardView(card);
            cv.setCardObserver(this);
            this.getChildren().add(cv);
        }
    }
    
    @Override
    public void cardSelected(CardView cv){
        cDeck.setSelectedCard(cv.getCard());
        selectedCard = cv.getCard();
        Game.getInstance().play(cDeck);
    }
        
    private void removeSel(){
        List cards = getChildren();
        for(int i=0;i<cards.size();i++){
            CardView cv = (CardView)cards.get(i);
            if (cv.getCard() == selectedCard){
                getChildren().remove(cv);
                selectedCard = null;
            }
        }      
    }
    
    @Override
    public void update(Observable o,Object arg){
        if (arg == null){
            return;
        }
        GameEvent ge = (GameEvent)arg;
        if (ge.getTarget() != GameEvent.Target.DECK){
            return;
        }
        if (ge.getAction() == GameEvent.Action.REMOVESEL){
            removeSel();
        }
    }
}



