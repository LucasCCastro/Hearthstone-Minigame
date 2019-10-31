package pucrs.ep.poo.cartas.cards;

public class Minion extends Card {
    private int attack;
    private int defense;
    private MinionType type;

    public Minion(String anId, String anImageId, int anAttack, int aDefense, MinionType aType) {
        super(anId, anImageId);
        this.attack = anAttack;
        this.defense = aDefense;
        this.type = aType;
    }

    public Minion(String anId, String anImageId, int anAttack, int aDefense) {
        super(anId, anImageId);
        this.attack = anAttack;
        this.defense = aDefense;
        this.type = null;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public MinionType getType() {
        return type;
    }

    public void setType(MinionType type) {
        this.type = type;
    }


    public enum MinionType {
        taunt,
        charge
    }
}
