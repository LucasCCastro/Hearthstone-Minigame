package pucrs.ep.poo.cartas.gui;

import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import pucrs.ep.poo.cartas.modelo.Card;
import pucrs.ep.poo.cartas.modelo.CardDeck;
import pucrs.ep.poo.cartas.modelo.CardObserver;
import pucrs.ep.poo.cartas.modelo.*;

public class DeckView extends HBox implements CardObserver, Observer {
    private boolean jogador;
    private CardDeck cDeck;
    private Card selectedCard;


    public DeckView(boolean nroJog) {
        super(4);
        this.setAlignment(Pos.CENTER);
        jogador = nroJog;
        selectedCard = null;

        cDeck = null;
        if (jogador) {
            cDeck = Game.getInstance().getDeckP1();
        } else {
            cDeck = Game.getInstance().getDeckP2();
        }
        cDeck.addObserver(this);

        for (Card card : cDeck.getCards()) {
            CardView cv = new CardView(card);
            cv.setCardObserver(this);
            this.getChildren().add(cv);
        }
    }

    @Override
    public void cardSelected(CardView cv) {
        cDeck.setSelectedCard(cv.getCard());
        selectedCard = cv.getCard();
    }

    private void removeSel() {
        boolean player = Game.getInstance().isPlayer();

        if (player) {
            if (Game.getInstance().getManaPlayer1() >= selectedCard.getValue()) {
                List cards = getChildren();
                for (int i = 0; i < cards.size(); i++) {
                    CardView cv = (CardView) cards.get(i);
                    if (cv.getCard() == selectedCard) {
                        getChildren().remove(cv);
                        Game.getInstance().setManaPlayer1(Game.getInstance().getManaPlayer1() - selectedCard.getValue());
                        selectedCard = null;
                    }
                }
            }
            } else if (!player) {
                if (Game.getInstance().getManaPlayer2() >= selectedCard.getValue()) {
                    List cards = getChildren();
                    for (int i = 0; i < cards.size(); i++) {
                        CardView cv = (CardView) cards.get(i);
                        if (cv.getCard() == selectedCard) {
                            getChildren().remove(cv);
                            Game.getInstance().setManaPlayer2(Game.getInstance().getManaPlayer2() - selectedCard.getValue());
                            selectedCard = null;
                        }
                    }
                }
            }



    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    private void addingToDeck() {
        List cards = getChildren();
        Card aCard = cDeck.getTheBoughtCard();

        if (aCard != null) {
            CardView cv = new CardView(aCard);
            cv.setCardObserver(this);
            cards.add(cv);
        }
    }

//    private void removingFromDeck() {
//        List cards = getChildren();
//
//            CardView cv = new CardView();
//            cv.setCardObserver(this);
//            cards.add(cv);
//
//    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            return;
        }
        GameEvent ge = (GameEvent) arg;
        if (ge.getTarget() != GameEvent.Target.DECK) {
            return;
        }
        if (ge.getAction() == GameEvent.Action.REMOVESEL) {
            removeSel();
        }
        if (ge.getAction() == GameEvent.Action.ADDINGTOTABLE) {

        }

        if (ge.getAction() == GameEvent.Action.ADDINGTODECK) {
            addingToDeck();
        }

    }
}



