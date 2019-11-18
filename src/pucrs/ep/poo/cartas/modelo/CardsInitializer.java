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

    private ArrayList<Card> cardsDeck;

    public void initializeCards() {
         cardsDeck = new ArrayList<>();
        String nomeArq = "cards.csv" ;
        String Type;
        String IdCard;
        String idImage;
        int atack;
        int cost;
        int health;
        String description;
        int SpellPower = 0;


        Path path1 = Paths.get(nomeArq);

        try (BufferedReader reader = Files.newBufferedReader(path1, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null) {

                String[] items = line.split(";");

                if (line.charAt(0) == 'M') {

                    //Type = items[0];
                    IdCard = items[1];
                    idImage = items[2];
                    cost = Integer.parseInt(items[3]);
                    atack = Integer.parseInt(items[4]);
                    health = Integer.parseInt(items[5]);

                    Card minion = new Minion(IdCard, idImage, cost, atack, health);

                    cardsDeck.add(minion);
                }

                else {
                    // Type = items[0];
                    IdCard = items[1];
                    idImage = items[2];
                    cost = Integer.parseInt(items[3]);
                    atack = Integer.parseInt(items[4]);
                    health = Integer.parseInt(items[5]);

                    for (int i = 0; i < items[6].length(); i++) {
                        if (items[6].charAt(i) == '$') {
                            SpellPower = Character.getNumericValue(items[6].charAt(i + 1));
                        }
                    }
                    if (items[6].contains("Deal")) {
                        Card spell = new Spell(IdCard, idImage, cost, Spell.SpellType.damage, SpellPower);
                        cardsDeck.add(spell);
                    } else {
                        Card spell = new Spell(IdCard, idImage, cost, Spell.SpellType.healing, SpellPower);
                        cardsDeck.add(spell);
                    }
                }
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    public Card returnARandomCard() {
        Random r = new Random();

        return new Card("1","3",9);
    }

    public String getNome(){
        for (Card c:cardsDeck) {
            System.out.println(c.getId());
        }
        return "cardsDeck.size()";
    }

}
