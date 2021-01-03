package downfall.powers;

import charbosses.bosses.AbstractCharBoss;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import downfall.actions.NeowRezAction;
import downfall.downfallMod;
import downfall.monsters.NeowBoss;
import slimebound.SlimeboundMod;
import theHexaghost.util.TextureLoader;

public class NeowInvulnerablePower extends AbstractPower {
    public static final String POWER_ID = downfallMod.makeID("NeowInvulnerable");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(downfallMod.assetPath("images/powers/NeowRez32.png"));

    private boolean firstTurn;

    public NeowInvulnerablePower(final AbstractCreature owner, int stack) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.amount = stack;
        this.name = NAME;

        this.updateDescription();

        firstTurn = true;
        this.canGoNegative = false;
    }

    @Override
    public int onLoseHp(int damageAmount) {
        if (NeowBoss.neowboss.offscreen){
            return 0;
        }

        return super.onLoseHp(damageAmount);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
        if (!NeowBoss.neowboss.offscreen) {
            this.owner.heal(amount, true);
        }
    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}