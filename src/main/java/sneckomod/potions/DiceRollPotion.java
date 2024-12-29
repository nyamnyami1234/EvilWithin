package sneckomod.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import sneckomod.SneckoMod;
import sneckomod.util.DiceRollPotionReward;

import java.util.ArrayList;

public class DiceRollPotion extends CustomPotion {
    public static final String POTION_ID = "sneckomod:DiceRollPotion";

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public DiceRollPotion() {
        super(NAME, POTION_ID, AbstractPotion.PotionRarity.RARE, AbstractPotion.PotionSize.SPHERE, AbstractPotion.PotionColor.FAIRY);
        this.labOutlineColor = SneckoMod.placeholderColor;
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom() != null) {
            AbstractDungeon.getCurrRoom().rewards.add(new DiceRollPotionReward());
            if (AbstractDungeon.player.hasRelic(SacredBark.ID)) {
                AbstractDungeon.getCurrRoom().rewards.add(new DiceRollPotionReward());
            }
        }
    }

    public static boolean cardListDuplicate(ArrayList<AbstractCard> cardsList, AbstractCard card) {
        for (AbstractCard alreadyHave : cardsList) {
            if (alreadyHave.cardID.equals(card.cardID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canUse() {
        return AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT;
    }

    @Override
    public CustomPotion makeCopy() {
        return new DiceRollPotion();
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
