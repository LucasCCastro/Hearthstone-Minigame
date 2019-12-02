package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.gui.GameEvent;
import pucrs.ep.poo.cartas.gui.GameWindow;
import pucrs.ep.poo.cartas.modelo.CardDeck;

import java.util.*;

public class Game extends Observable {
    private static Game game = new Game();
    private int lifePlayer1, lifePlayer2;
    private int manaPlayer1, manaPlayer2, playMana;
    private CardDeck deckP1, deckP2, playDeck, opponentDeck;
    private Table tableP1, tableP2, playTable, opponentTable;
    private boolean player; //P1 = true//P2 = false//
    private CardsInitializer cardsInitializer;

    public static Game getInstance() {
        return (game);
    }

    private Game() {
        this.cardsInitializer = new CardsInitializer();
        this.lifePlayer1 = 30;
        this.lifePlayer2 = 30;
        this.manaPlayer1 = 0;
        this.manaPlayer2 = 0;
        this.playMana = 0;
        this.deckP1 = new CardDeck();
        this.deckP2 = new CardDeck();
        this.playDeck = new CardDeck();
        this.opponentDeck = new CardDeck();
        this.tableP1 = new Table();
        this.tableP2 = new Table();
        this.playTable = new Table();
        this.opponentTable = new Table();

        play(this.deckP1);
    }

    public void nextPlayer() {
        if (this.player) {
            this.player = false;
            play(this.deckP2);
        } else {
            this.player = true;
            play(this.deckP1);
        }
    }

    public int getLifePlayer1() {
        return this.lifePlayer1;
    }

    public void setLifePlayer1(int value) {
        this.lifePlayer1 = value;
    }

    public int getLifePlayer2() {
        return this.lifePlayer2;
    }

    public void setLifePlayer2(int value) {
        this.lifePlayer2 = value;
    }

    public int getManaPlayer1() {
        return this.manaPlayer1;
    }

    public void setManaPlayer1(int value) {
        this.manaPlayer1 = value;
    }

    public int getManaPlayer2() {
        return this.manaPlayer2;
    }

    public void setManaPlayer2(int value) {
        this.manaPlayer2 = value;
    }


    public boolean isPlayer() {
        return player;
    }

    public int getPlayMana() {
        return playMana;
    }

    public void setPlayMana(int playMana) {
        this.playMana = playMana;
    }

    public CardDeck getDeckP1() {
        return this.deckP1;
    }

    public CardDeck getDeckP2() {
        return this.deckP2;
    }

    public Table getTableP1() {
        return tableP1;
    }

    public Table getTableP2() {
        return tableP2;
    }

    public void setPlayTable(Table playTable) {
        this.playTable = playTable;
    }


    public void play(CardDeck deckAcionado) {
        GameEvent gameEvent = null;

        if (deckAcionado == this.deckP1) {
            if (!this.player) {
                gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "2");
                setChanged();
                notifyObservers(gameEvent);
            } else {

                this.deckP1.buyACard();
                if (this.playMana < 10) {
                    playMana++;
                }
                this.manaPlayer1 = playMana;
                gameEvent = new GameEvent(GameEvent.Target.TABLE, GameEvent.Action.NEWROUND, "");
                GameEvent event = new GameEvent(GameEvent.Target.TABLE,GameEvent.Action.ADDINGTOTABLE,"");
                setChanged();
                notifyObservers(gameEvent);
                notifyObservers(event);
                //
                //
                System.out.println("player1 turn");
                //All P1 possible actions will be here
                //
                //
                if (this.lifePlayer2 <= 0) {
                    //
                    //The actions in case P1 wins will be here
                    //
                }

            }
        } else if (deckAcionado == deckP2) {
            if (this.player) {
                gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "1");
                setChanged();
                notifyObservers(gameEvent);
            } else {
                this.deckP2.buyACard();
                this.manaPlayer2 = playMana;

                gameEvent = new GameEvent(GameEvent.Target.TABLE, GameEvent.Action.NEWROUND, "");
                GameEvent anEvent = new GameEvent(GameEvent.Target.TABLE,GameEvent.Action.ADDINGTOTABLE,"");
                setChanged();
                notifyObservers(gameEvent);
                notifyObservers(anEvent);
                //
                //
                System.out.println("player2 turn");
                //All P2 possible actions will be here
                //
                //
                if (this.lifePlayer1 <= 0) {
                    //
                    //The actions in case P2 wins will be here
                    //
                }

            }
        }
    }

    public void removeSelected() {
        GameEvent gameEvent = null;
        if (player) {
            if(deckP1.getSelectedCard()!=null) {
                if(manaPlayer1 >= deckP1.getSelectedCard().getValue()) {
                    addCardToTheTable(deckP1.getSelectedCard());
                    deckP1.removeSel();
                }
            }

        } else {
            if (deckP2.getSelectedCard() != null) {
                if(manaPlayer2 >= deckP2.getSelectedCard().getValue()) {
                    addCardToTheTable(deckP2.getSelectedCard());
                    deckP2.removeSel();
                }
            }
        }

        gameEvent = new GameEvent(GameEvent.Target.DECK, GameEvent.Action.REMOVINGFROMDECK, "");
        setChanged();
        notifyObservers(gameEvent);
    }

    public void addCardToTheTable(Card aCard) {
        if (player) {
            tableP1.addCardToTheTable(aCard);
        } else {
            tableP2.addCardToTheTable(aCard);
        }
    }




}
