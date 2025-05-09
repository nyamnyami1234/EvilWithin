package downfall.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import downfall.actions.WaitForEscapeAction;
import downfall.downfallMod;
import gremlin.characters.GremlinCharacter;

public class HeartsMalice extends CustomRelic {

    public static final String ID = downfallMod.makeID("HeartsMalice");
    private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/HeartsMalice.png"));
    private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/HeartsMalice.png"));

    public HeartsMalice() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.FLAT);
        this.counter = 3;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        if (!(AbstractDungeon.getCurrRoom() instanceof ShopRoom)) {
            if (this.counter > 0) {
                --this.counter;
                if (this.counter == 0) {
                    this.setCounter(-2);
                    this.description = this.DESCRIPTIONS[1];
                    this.tips.clear();
                    this.tips.add(new PowerTip(this.name, this.description));
                    this.initializeTips();
                }

                this.flash();

                //AbstractDungeon.actionManager.actions.removeIf((act)->act instanceof DrawCardAction); Works, but may cause issues if somehow not all enemies flee.
                AbstractDungeon.actionManager.addToTop(new WaitForEscapeAction()); //This works better.
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToTop(new EscapeAction(m));
                }

                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

                if (AbstractDungeon.player instanceof GremlinCharacter) {
                    ((GremlinCharacter) AbstractDungeon.player).mobState.startOfBattle((GremlinCharacter) AbstractDungeon.player);
                }
            }
        }
    }

    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (setCounter <= 0) {
            this.usedUp();
        }

    }

    public AbstractRelic makeCopy() {
        return new HeartsMalice();
    }
}
