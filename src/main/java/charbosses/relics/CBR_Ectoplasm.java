package charbosses.relics;

import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Ectoplasm;
import downfall.downfallMod;
import slimebound.SlimeboundMod;

import java.util.ArrayList;

public class CBR_Ectoplasm extends AbstractCharbossRelic {
    public static final String ID = "Ectoplasm";
    private int numCards;

    public CBR_Ectoplasm() {
        super(new Ectoplasm());
    }

    @Override
    public String getUpdatedDescription() {
        if (AbstractCharBoss.boss != null) {
            return this.setDescription(AbstractCharBoss.boss.chosenClass);
        }
        return this.setDescription(null);
    }

    private String setDescription(final AbstractPlayer.PlayerClass c) {
        return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0] + CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID(ID)).DESCRIPTIONS[0] + this.numCards + CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID(ID)).DESCRIPTIONS[1];
    }

    @Override
    public void updateDescription(final AbstractPlayer.PlayerClass c) {
        this.description = this.setDescription(c);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void modifyCardsOnCollect(ArrayList<AbstractBossCard> groupToModify, int actIndex) {
        SlimeboundMod.logger.info("Ectoplasm received act index: " + actIndex);
        for (int i = actIndex; i < 3; i++) {
            if (this.owner.chosenArchetype.cardRemovalsPerAct[i] > 0)
            this.owner.chosenArchetype.cardRemovalsPerAct[i] -= 1;
            this.numCards +=1;
        }

        this.description = getUpdatedDescription();
        this.refreshDescription();

    }

    @Override
    public void onEquip() {
        final EnergyManager energy = AbstractCharBoss.boss.energy;
        ++energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        final EnergyManager energy = AbstractCharBoss.boss.energy;
        --energy.energyMaster;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CBR_Ectoplasm();
    }
}
