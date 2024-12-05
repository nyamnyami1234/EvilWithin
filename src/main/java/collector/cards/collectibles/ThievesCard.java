package collector.cards.collectibles;

import collector.powers.collectioncards.ThievesCardPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.applyToSelf;

public class ThievesCard extends AbstractCollectibleCard {
    public final static String ID = makeID(ThievesCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 1, 1

    public ThievesCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ThievesCardPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}