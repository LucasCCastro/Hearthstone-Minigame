package pucrs.ep.poo.cartas.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import pucrs.ep.poo.cartas.modelo.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TableView extends HBox implements CardObserver, Observer {
    private boolean jogador;
    private Table cTable;
    private Card selectedCard;


    public TableView(boolean nroJog){
        super(4);
        this.setAlignment(Pos.CENTER);

        jogador = nroJog;
        selectedCard = null;

        cTable = null;
        if (jogador){
            cTable = Game.getInstance().getTableP1();
        }else{
            cTable = Game.getInstance().getTableP2();
        }
        cTable.addObserver(this);

        for(Card card:cTable.getTableCards()){
            CardView cv = new CardView(card);
            cv.setCardObserver(this);
            this.getChildren().add(cv);
        }
    }

    @Override
    public void cardSelected(CardView cv){
        cTable.setSelectedCard(cv.getCard());
        selectedCard = cv.getCard();
        Game.getInstance().setPlayTable(cTable);
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
    public void update(Observable o, Object arg){
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
