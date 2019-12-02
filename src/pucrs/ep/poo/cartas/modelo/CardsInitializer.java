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
        Card c11 = new Minion("11","img11",5,5,6); this.allCards.add(c11);
        Card c12 = new Spell("12","img12",4,Spell.SpellType.damage,6); this.allCards.add(c12);
        Card c13 = new Minion("13","img13",5,5,5); this.allCards.add(c13);
        Card c14 = new Minion("14","img14",4,5,2); this.allCards.add(c14);
        Card c15 = new Minion("15","img15",2,2,2); this.allCards.add(c15);
        Card c16 = new Minion("16","img16",3,3,4); this.allCards.add(c16);
        Card c17 = new Minion("17","img17",8,7,7); this.allCards.add(c17);
        Card c18 = new Minion("18","img18",10,10,10); this.allCards.add(c18);
        Card c19 = new Minion("19","img19",6,6,6); this.allCards.add(c19);
        Card c20 = new Minion("20","img20",1,1,1); this.allCards.add(c20);
        Card c21 = new Minion("21","img21",1,2,1); this.allCards.add(c21);
        Card c22 = new Minion("22","img22",10,10,10); this.allCards.add(c22);
        Card c23 = new Spell("23","img23",1,Spell.SpellType.damage,1); this.allCards.add(c23);
        Card c24 = new Minion("24","img24",3,3,3); this.allCards.add(c24);
        Card c25 = new Minion("25","img25",2,2,3); this.allCards.add(c25);
        Card c26 = new Minion("26","img26",3,3,3); this.allCards.add(c26);
        Card c27 = new Minion("27","img27",4,4,4); this.allCards.add(c27);
        Card c28 = new Minion("28","img28",1,1,3); this.allCards.add(c28);
        Card c29 = new Minion("29","img29",2,3,3); this.allCards.add(c29);
        Card c30 = new Minion("30","img30",2,2,2); this.allCards.add(c30);
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
        allCards.remove(aCard);
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
