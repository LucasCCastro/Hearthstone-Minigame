package pucrs.ep.poo.cartas.modelo;

import java.util.ArrayList;
import java.util.Random;

public class CardsInitializer {
    private ArrayList<Card> mountain = new ArrayList<>();

    public void initializeCards() {
        Card c1 = new Minion("1", "img1",5, 5, 4, Minion.MinionType.charge);
        mountain.add(c1);
        Card c2 = new Minion("2", "img2",1, 2, 1);
        mountain.add(c2);
        Card c3 = new Minion("3", "img3",3, 3, 4);
        mountain.add(c3);
        Card c4 = new Minion("4", "img4",4, 6, 6, Minion.MinionType.taunt);
        mountain.add(c4);

        Card c5 = new Spell("5", "img4",4, Spell.SpellType.damage, 3);
        mountain.add(c5);
        Card c6 = new Spell("6", "img4",6, Spell.SpellType.healing, 5);
        mountain.add(c6);
    }

    public Card returnARandomCard() {
        Random r = new Random();
        Card aCard = null;

        int n = r.nextInt(6) + 1;
        for (Card c : mountain) {

            if (Integer.parseInt(c.getId()) == n) {
                aCard = c;
                this.mountain.remove(c);
            }

        }
        return aCard;
    }
}
