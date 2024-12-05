package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.atb;

public class RepulsorCard extends AbstractCollectibleCard {
    public final static String ID = makeID(RepulsorCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 3, 1

    public RepulsorCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}