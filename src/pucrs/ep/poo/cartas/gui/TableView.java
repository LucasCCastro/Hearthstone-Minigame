package pucrs.ep.poo.cartas.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import pucrs.ep.poo.cartas.modelo.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TableView extends HBox implements CardObserver, Observer {
    private boolean player;
    private Table cTable;
    private CardDeck cDeck;
    private Card selectedCard;


    public TableView(boolean whichPlayer) {
        super(4);
        this.setAlignment(Pos.CENTER);

        player = whichPlayer;
        selectedCard = null;

        cDeck = null;
        cTable = null;
        if (player) {
            cDeck = Game.getInstance().getDeckP1();
            cTable = Game.getInstance().getTableP1();
        } else {
            cDeck = Game.getInstance().getDeckP2();
            cTable = Game.getInstance().getTableP2();
        }
        cDeck.addObserver(this);
        cTable.addObserver(this);

        for (Card card : cTable.getTableCards()) {
            CardView cv = new CardView(card);
            cv.setCardObserver(this);
            this.getChildren().add(cv);
        }
    }

    @Override
    public void cardSelected(CardView cv) {
        cDeck.setSelectedCard(cv.getCard());
        selectedCard = cv.getCard();
       // Game.getInstance().setPlayTable(cTable);
    }

    private void addSel() {
        List cards = cTable.getTableCards();
        selectedCard = cTable.getSelectedCard();
        CardView cv = new CardView(selectedCard);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) == selectedCard) {
                getChildren().add(cv);
                selectedCard = null;
            }
        }
    }

    private void removeSel() {
        List cards = getChildren();
        for (int i = 0; i < cards.size(); i++) {
            CardView cv = (CardView) cards.get(i);
            if (cv.getCard() == selectedCard) {
                getChildren().remove(cv);
                selectedCard = null;
            }
        }
    }

    private void addingToTable() {
        List cards = cTable.getTableCards();
        selectedCard = cTable.getSelectedCard();
        CardView cv = new CardView(selectedCard);
        getChildren().add(cv);
//        for (int i = 0; i < cards.size(); i++) {
//            if (cards.get(i) == selectedCard) {
//            }
//        }
        selectedCard = null;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            return;
        }
        GameEvent ge = (GameEvent) arg;
        if (ge.getTarget() != GameEvent.Target.TABLE) {
            return;
        }
        if (ge.getAction() == GameEvent.Action.REMOVESEL) {
            removeSel();
        }

        if (ge.getAction() == GameEvent.Action.ADDINGTOTABLE) {
            addSel();
        }
    }
}
