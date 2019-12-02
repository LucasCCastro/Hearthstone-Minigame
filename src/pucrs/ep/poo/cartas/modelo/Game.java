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
        this.lifePlayer1 = 50;
        this.lifePlayer2 = 50;
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
                //gameEvent = new GameEvent(GameEvent.Target.TABLE, GameEvent.Action.NEWROUND, "");
                GameEvent event = new GameEvent(GameEvent.Target.TABLE, GameEvent.Action.ADDINGTOTABLE, "");
                setChanged();
               // notifyObservers(gameEvent);
                notifyObservers(event);

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
                GameEvent anEvent = new GameEvent(GameEvent.Target.TABLE, GameEvent.Action.ADDINGTOTABLE, "");
                setChanged();
                notifyObservers(gameEvent);
                notifyObservers(anEvent);

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
            if (deckP1.getSelectedCard() != null) {
                if (manaPlayer1 >= deckP1.getSelectedCard().getValue()) {
                    addCardToTheTable(deckP1.getSelectedCard());
                    deckP1.removeSel();
                }
            }

        } else {
            if (deckP2.getSelectedCard() != null) {
                if (manaPlayer2 >= deckP2.getSelectedCard().getValue()) {
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

    public void attackMinions() {
        System.out.println("atacou minions");
        System.out.println("p1 " + tableP1.getNumberOfTableCards());
        System.out.println("p2 " + tableP2.getNumberOfTableCards());
        if(tableP1.getNumberOfTableCards() > 0 || tableP2.getNumberOfTableCards() > 0) {
            int attack = 0, defense = 0;
            ArrayList<Card> p1CardsToDie = new ArrayList<>();
            ArrayList<Card> p2CardsToDie = new ArrayList<>();
            Minion.MinionType mType = null;

            for (Card aCard : tableP1.getTableCards()) {
                if (aCard instanceof Minion) { //What to do if the current player card is a minion
                    attack = ((Minion) aCard).getAttack();
                    defense = ((Minion) aCard).getDefense();
                    if (((Minion) aCard).getType() != null) {
                        mType = ((Minion) aCard).getType();
                    }
                    if (mType == Minion.MinionType.charge) {
                        ((Minion) aCard).setAttack(((Minion) aCard).getAttack() + 2); //A minion receives +2 attack if it has CHARGE
                    }
                    if (mType == Minion.MinionType.taunt) {
                        ((Minion) aCard).setDefense(((Minion) aCard).getDefense() + 2); //A minion receives +2 defense if it has TAUNT
                    }

                    for (Card opponentCard : tableP2.getTableCards()) {
                        if (opponentCard instanceof Minion) { //What to do if the opponent player card is a minion
                            ((Minion) opponentCard).setDefense(((Minion) opponentCard).getDefense() - attack); //opponent minion  taking damage from current player minion
                            ((Minion) aCard).setDefense(((Minion) aCard).getDefense() - ((Minion) opponentCard).getAttack()); //current player minion taking damage from the opponent minion
                            if (((Minion) opponentCard).getDefense() <= 0) {
                                //tableP2.killCard(opponentCard);  //killing an opponent minion if it ends with 0 or less defense
                                p2CardsToDie.add(opponentCard);
                            }
                            if (((Minion) aCard).getDefense() <= 0) {
                                //tableP1.killCard(aCard); //killing current player minion if it ends with 0 or less defense
                                p1CardsToDie.add(aCard);
                                break;
                            }
                        } else if (opponentCard instanceof Spell && ((Spell) opponentCard).getType() == Spell.SpellType.damage) { //What to do if the opponent card is a damage spell
                            ((Minion) aCard).setDefense(((Minion) aCard).getDefense() - opponentCard.getValue());
                            if (((Minion) aCard).getDefense() <= 0) {
                                //tableP1.killCard(aCard); //killing current player minion if it ends with 0 or less defense
                                p1CardsToDie.add(aCard);
                                break;
                            }
                           //deckP2.killCard(opponentCard); //killing a spell after it gives the damage
                            p2CardsToDie.add(opponentCard);
                        } else if (opponentCard instanceof Spell && ((Spell) opponentCard).getType() == Spell.SpellType.healing) { //What to do if the opponent card is a healing spell
                            setLifePlayer2(getLifePlayer2() + aCard.getValue());
                            //deckP2.killCard(opponentCard); //killing a spell after it gives the healing
                            p2CardsToDie.add(opponentCard);
                        }

                    }
                } else if (aCard instanceof Spell) {
                    if (((Spell) aCard).getType() == Spell.SpellType.healing) {
                        setLifePlayer1(getLifePlayer1() + aCard.getValue()); //Healing the current player with the healing spell value
                    }
                    if (((Spell) aCard).getType() == Spell.SpellType.damage) {
                        for (Card opponentCard : tableP2.getTableCards()) {
                            if (opponentCard instanceof Minion) {
                                ((Minion) opponentCard).setDefense(((Minion) opponentCard).getDefense() - aCard.getValue()); //Giving the damage of the spell to the opponent minion
                                //tableP1.killCard(aCard); //Killing the damage spell after it's used
                                p1CardsToDie.add(aCard);
                                break;
                            }
                        }
                    }

                }

            }
            for(Card cardToDie : p1CardsToDie) {
                tableP1.killCard(cardToDie);
            }

            for(Card cardToDie : p2CardsToDie) {
                tableP2.killCard(cardToDie);
            }
            GameEvent gameEvent1 = new GameEvent(GameEvent.Target.TABLE, GameEvent.Action.REMOVINGFROMTABLE, "");
            setChanged();
            notifyObservers(gameEvent1);
        }
        System.out.println("depois p1 " + tableP2.getNumberOfTableCards());
        System.out.println("depois p2 " + tableP2.getNumberOfTableCards());
    }

    public void attackFace() {
        System.out.println("atacou face");
        ArrayList<Card> cardsToDieP1 = new ArrayList<>();
        ArrayList<Card> cardsToDieP2 = new ArrayList<>();
        if (player) {
            if (tableP2.getNumberOfTableCards() == 0) {

                for (Card aCard : tableP1.getTableCards()) {
                    if (aCard instanceof Minion) {
                        setLifePlayer2(getLifePlayer2() - ((Minion) aCard).getAttack()); //giving the minion attack damage to the face
                    } else if (aCard instanceof Spell && ((Spell) aCard).getType() == Spell.SpellType.damage) {
                        setLifePlayer2(getLifePlayer2() - aCard.getValue()); //giving the damage spell damage to the face
                        //tableP1.killCard(aCard); //killing a damage spell after it's used
                        cardsToDieP1.add(aCard);
                    } else if (aCard instanceof Spell && ((Spell) aCard).getType() == Spell.SpellType.healing) {
                        setLifePlayer1(getLifePlayer1() + aCard.getValue()); //giving the healing spell value to own player life
                        //tableP1.killCard(aCard); //killing the healing spell after it's used
                        cardsToDieP1.add(aCard);
                    }
                }
            }
            for(Card theCardToDie : cardsToDieP1) {
                tableP1.killCard(theCardToDie);
            }

        } else if (tableP1.getNumberOfTableCards() == 0) {
            System.out.println("verificou que inimigo n tem carta");
            for (Card aCard : tableP2.getTableCards()) {
                if (aCard instanceof Minion) {
                    System.out.println("verificou que se trata de um minion");
                    setLifePlayer1(getLifePlayer1() - ((Minion) aCard).getAttack()); //giving the minion attack damage to the face
                } else if (aCard instanceof Spell && ((Spell) aCard).getType() == Spell.SpellType.damage) {
                    setLifePlayer1(getLifePlayer1() - aCard.getValue()); //giving the damage spell damage to the face
                    //tableP2.killCard(aCard); //killing a damage spell after it's used
                    cardsToDieP2.add(aCard);
                } else if (aCard instanceof Spell && ((Spell) aCard).getType() == Spell.SpellType.healing) {
                    setLifePlayer2(getLifePlayer2() + aCard.getValue()); //giving the healing spell value to own player life
                    //tableP2.killCard(aCard); //killing the healing spell after it's used
                    cardsToDieP2.add(aCard);
                }
            }
        }
        for(Card theCardToDie : cardsToDieP2) {
            tableP2.killCard(theCardToDie);
        }
        System.out.println(getLifePlayer1());
        System.out.println(tableP2.getNumberOfTableCards());
    }


}
