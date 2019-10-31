package pucrs.ep.poo.cartas.cards;

public class Spell extends Card{
    private SpellType type;
    private int value;

    public Spell(String anId, String anImageId, SpellType aType, int aValue) {
        super(anId, anImageId);
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
