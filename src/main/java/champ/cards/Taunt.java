package champ.cards;

import champ.ChampMod;
import champ.stances.BerserkerStance;
import champ.stances.DefensiveStance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.actions.OctoChoiceAction;
import downfall.cards.OctoChoiceCard;
import downfall.util.OctopusCard;

import java.util.ArrayList;
import java.util.Collections;

public class Taunt extends AbstractChampCard {

    public final static String ID = makeID("Taunt");

    //stupid intellij stuff ATTACK, ENEMY, STARTER

    public Taunt() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        //tags.add(ChampMod.OPENER);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            applyToEnemy(m, autoWeak(m, 1));
            applyToEnemy(m, autoVuln(m, 1));
        }
        else {
           AbstractDungeon.getMonsters().monsters.stream().filter(m2 -> !m2.isDead && !m2.isDying).forEach(m2 -> {
               applyToEnemy(m2, autoWeak(m2, 1));
               applyToEnemy(m2, autoVuln(m2, 1));
           });
        }
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        target = CardTarget.ALL_ENEMY;
    }
}