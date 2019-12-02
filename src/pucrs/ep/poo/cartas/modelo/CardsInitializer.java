package pucrs.ep.poo.cartas.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class CardsInitializer {

    private ArrayList<Card> allCards;
    private Random randomGenerator;

    public CardsInitializer() {
        randomGenerator = new Random();
        this.allCards = new ArrayList<>();
        Card c1 = new Minion("1","img1",3,5,2); this.allCards.add(c1);
        Card c2 = new Minion("2","img2",7,6,8); this.allCards.add(c2);
        Card c3 = new Minion("3","img3",7,5,9); this.allCards.add(c3);
        Card c4 = new Minion("4","img4",4,4,5); this.allCards.add(c4);
        Card c5 = new Minion("5","img5",5,7,4); this.allCards.add(c5);
        Card c6 = new Minion("6","img6",2,2,2); this.allCards.add(c6);
        Card c7 = new Minion("7","img7",4,4,4); this.allCards.add(c7);
        Card c8 = new Spell("8","img8",1, Spell.SpellType.damage,1); this.allCards.add(c8);
        Card c9 = new Spell("9","img9",7, Spell.SpellType.healing,5); this.allCards.add(c9);
        Card c10 = new Minion("10","img10",3,3,3); this.allCards.add(c10);
    }

//    public void initializeCards() {
//         cardsDeck = new ArrayList<>();
//        String nomeArq = "cards.csv" ;
//        String Type;
//        String IdCard;
//        String idImage;
//        int atack;
//        int cost;
//        int health;
//        String description;
//        int SpellPower = 0;
//
//
//        Path path1 = Paths.get(nomeArq);
//
//        try (BufferedReader reader = Files.newBufferedReader(path1, StandardCharsets.UTF_8)) {
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//
//                String[] items = line.split(";");
//
//                if (line.charAt(0) == 'M') {
//
//                    //Type = items[0];
//                    IdCard = items[1];
//                    idImage = items[2];
//                    cost = Integer.parseInt(items[3]);
//                    atack = Integer.parseInt(items[4]);
//                    health = Integer.parseInt(items[5]);
//
//                    Card minion = new Minion(IdCard, idImage, cost, atack, health);
//
//                    cardsDeck.add(minion);
//                }
//
//                else {
//                    // Type = items[0];
//                    IdCard = items[1];
//                    idImage = items[2];
//                    cost = Integer.parseInt(items[3]);
//                    atack = Integer.parseInt(items[4]);
//                    health = Integer.parseInt(items[5]);
//
//                    for (int i = 0; i < items[6].length(); i++) {
//                        if (items[6].charAt(i) == '$') {
//                            SpellPower = Character.getNumericValue(items[6].charAt(i + 1));
//                        }
//                    }
//                    if (items[6].contains("Deal")) {
//                        Card spell = new Spell(IdCard, idImage, cost, Spell.SpellType.damage, SpellPower);
//                        cardsDeck.add(spell);
//                    } else {
//                        Card spell = new Spell(IdCard, idImage, cost, Spell.SpellType.healing, SpellPower);
//                        cardsDeck.add(spell);
//                    }
//                }
//            }
//        } catch (IOException x) {
//            System.err.format("Erro de E/S: %s%n", x);
//        }
//    }

    public Card returnARandomCard() {
        int index = randomGenerator.nextInt(allCards.size());
        Card aCard = allCards.get(index);

        return aCard;
    }

    public String returnARandomCardImgId() {
        return returnARandomCard().getImageId();
    }


    public String getNome(){
        for (Card c:allCards) {
            System.out.println(c.getId());
        }
        return "cardsDeck.size()";
    }

}
