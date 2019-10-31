package pucrs.ep.poo.cartas.modelo;

import pucrs.ep.poo.cartas.cards.CardDeck;
import pucrs.ep.poo.cartas.gui.GameEvent;

import java.util.*;

public class Game extends Observable{
    private static Game game = new Game();
    private int lifePlayer1, lifePlayer2;
    private CardDeck deckP1, deckP2;
    private boolean player; //P1 = true//P2 = false//

    
    public static Game getInstance(){
        return(game);
    }
    
    private Game(){
        lifePlayer1 = 30;
        lifePlayer2 = 30;
        deckP1 = new CardDeck();
        deckP2 = new CardDeck();
        player = true;

    }
    
    private void nextPlayer(){
        if(player){
            this.player = false;
        }
        else {
            this.player = true;
        }
    }

    public int getLifePlayer1() {
        return lifePlayer1;
    }

    public int getLifePlayer2() {
        return lifePlayer2;
    }

    public CardDeck getDeckP1() {
        return deckP1;
    }

    public CardDeck getDeckP2() {
        return deckP2;
    }

    public void play(CardDeck deckAcionado){
        GameEvent gameEvent = null;

        if (deckAcionado == deckP1){
            if (!player){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"2");
                setChanged();
                notifyObservers(gameEvent);
            }else{
                //
                //
                //Tudo que o P1 tem pra fazer vai ficar aqui
                //
                //
                if(lifePlayer2 <= 0) {
                    //
                    //Aqui fica o que acontece caso o P2 ganhe a partida
                    //
                }
                nextPlayer();
            }
        }else if (deckAcionado == deckP2){
            if (player){
                gameEvent = new GameEvent(GameEvent.Target.GWIN,GameEvent.Action.INVPLAY,"1");
                setChanged();
                notifyObservers(gameEvent);
            }else{
                //
                //
                //Tudo que o P2 tem pra fazer vai ficar aqui
                //
                //
                if(lifePlayer1 <= 0) {
                    //
                    //Aqui fica o que acontece caso o P2 ganhe a partida
                    //
                }
                nextPlayer();
            }
        }          
    }

}
