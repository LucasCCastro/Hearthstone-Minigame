package pucrs.ep.poo.cartas.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Spell extends Card {
    private SpellType type;
    private int value;

    public Spell(String anId, String anImageId, int cost, SpellType aType, int aValue) {
        super(anId, anImageId, cost);
        this.type = aType;
        this.value = aValue;
    }

    public SpellType getType() {
        return type;
    }

    public void setType(SpellType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public enum SpellType {
        damage,
        healing
    }

}