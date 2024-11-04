package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import sneckomod.SneckoMod;

public class RoundaboutSwing extends AbstractSneckoCard {

    public static final String ID = SneckoMod.makeID("RoundaboutSwing");

    private static final int DAMAGE = 16;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public RoundaboutSwing() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        this.addToBot(new PutOnDeckAction(p, p, 1, false));
        applyToSelf(new DrawCardNextTurnPower(p, magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
