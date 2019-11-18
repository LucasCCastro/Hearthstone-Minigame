package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;
import pucrs.ep.poo.cartas.gui.GameWindow;
import pucrs.ep.poo.cartas.modelo.CardDeck;

import java.util.*;

public class Game extends Observable{
    private static Game game = new Game();
    private int lifePlayer1, lifePlayer2;
    private int manaPlayer1, manaPlayer2;
    private CardDeck deckP1, deckP2, playDeck, opponentDeck;
    private table tableP1, tableP2, playTable, opponentTable;
    private boolean player; //P1 = true//P2 = false//
    private CardsInitializer cardsInitializer = new CardsInitializer();

    public static Game getInstance(){
        return(game);
    }

    private Game(){
        this.cardsInitializer.initializeCards();
        this.lifePlayer1 = 30;
        this.lifePlayer2 = 30;
        this.manaPlayer1 = 1;
        this.manaPlayer2 = 1;
        this.deckP1 = new CardDeck();
        this.deckP2 = new CardDeck();
        this.playDeck = new CardDeck();
        this.opponentDeck = new CardDeck();
        this.tableP1 = new table();
        this.tableP2 = new table();
        this.playTable = new table();
        this.opponentTable = new table();
        this.player = true;
    }

    private void nextPlayer(){
        if(this.player){
            this.player = false;
        }
        else {
            this.player = true;
        }
    }

    public int getLifePlayer1() {
        return this.lifePlayer1;
    }
    public void setLifePlayer1(int value) { this.lifePlayer1 = value; }

    public int getLifePlayer2() {
        return this.lifePlayer2;
    }
    public void setLifePlayer2(int value) { this.lifePlayer2 = value; }

    public int getManaPlayer1() { return this.manaPlayer1; }
    public void setManaPlayer1(int value) { this.manaPlayer1 = manaPlayer1; }

    public int getManaPlayer2() { return this.manaPlayer2; }
    public void setManaPlayer2(int value) { this.manaPlayer2 = manaPlayer2; }

    public CardDeck getDeckP1() {
        return this.deckP1;
    }

    public CardDeck getDeckP2() {
        return this.deckP2;
    }

    public void play(CardDeck deckAcionado){
        GameEvent gameEvent = null;

        if (deckAcionado == this.deckP1){
            if (!this.player){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"2");
                setChanged();
                notifyObservers(gameEvent);
            }else{
                this.deckP1.add(this.cardsInitializer.returnARandomCard());
                this.playDeck = deckP1;
                this.playTable = tableP1;
                this.opponentTable = tableP2;
                this.opponentDeck.createOpponentHand(deckP2.getNumberOfCards());
                if(this.manaPlayer1 < 10) {this.manaPlayer1++;}
                //
                //
                //All P1 possible actions will be here
                //
                //
                if(this.lifePlayer2 <= 0) {
                    //
                    //The actions in case P1 wins will be here
                    //
                }
                nextPlayer();
            }
        }else if (deckAcionado == deckP2){
            if (this.player){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"1");
                setChanged();
                notifyObservers(gameEvent);
            }else{
                this.deckP2.add(this.cardsInitializer.returnARandomCard());
                this.playDeck = deckP2;
                this.playTable = tableP2;
                this.opponentTable = tableP1;
                this.opponentDeck.createOpponentHand(deckP1.getNumberOfCards());
                if(this.manaPlayer2 < 10){this.manaPlayer2++;}
                //
                //
                //All P2 possible actions will be here
                //
                //
                if(this.lifePlayer1 <= 0) {
                    //
                    //The actions in case P2 wins will be here
                    //
                }
                nextPlayer();
            }
        }
    }

}
