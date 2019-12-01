package pucrs.ep.poo.cartas.modelo;

public class TemporaryDbOfMethods {

    //It is just a temporary concept
    //These methods will be allocated into different classes later on

    public void attackAMinion(Minion a, Minion b) {
        b.setDefense(( b.getDefense() - a.getAttack() ));
        a.setDefense(( a.getDefense() - b.getAttack() ));
    }

    public void spellAMinion(Spell aSpell, Minion aMinion) {
        if(aSpell.getType().equals(Spell.SpellType.damage)) {
            aMinion.setDefense(( aMinion.getDefense() - aSpell.getValue() ));
        } else if(aSpell.getType().equals(Spell.SpellType.healing)) {
            aMinion.setDefense( aMinion.getDefense() + aSpell.getValue() );
        }

    }

    public void attackEnemyFace(Minion minion, boolean enemy) {
        if(enemy) {
            Game.getInstance().setLifePlayer1( Game.getInstance().getLifePlayer1() - minion.getAttack() );
        }
        else {
            Game.getInstance().setLifePlayer2( Game.getInstance().getLifePlayer2() - minion.getAttack() );
        }
    }
}
