package collector.cards;

import basemod.helpers.CardModifierManager;
import champ.ChampMod;
import collector.CollectorCollection;
import collector.CollectorMod;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.cardmods.CollectedCardMod;
import collector.effects.MiniUpgradeShine;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import guardian.GuardianMod;
import guardian.cards.AbstractGuardianCard;
import hermit.util.Wiz;
import sneckomod.SneckoMod;

import java.util.ArrayList;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.atb;

public class DarkApotheosis extends AbstractCollectorCard {
    public final static String ID = makeID(DarkApotheosis.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , , 

    public DarkApotheosis() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> toCheck = new ArrayList<>(CollectorCollection.combatCollection.group);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : toCheck) {
                    q.upgrade();
                }
            }
        });

        collectCollectedFromGroup(p.hand);
        collectCollectedFromGroup(p.drawPile);
        collectCollectedFromGroup(p.discardPile);
        collectCollectedFromGroup(p.exhaustPile);

        if (toCheck.stream().anyMatch(q -> CollectorCollection.combatCollection.group.contains(q))) {
            atb(new VFXAction(new MiniUpgradeShine(CollectorMod.combatCollectionPileButton.getRenderX(), CollectorMod.combatCollectionPileButton.getRenderY())));
        }
    }

    private void collectCollectedFromGroup(CardGroup group) {
        for (AbstractCard c : group.group) {
            if ((CardModifierManager.hasModifier(c, ActuallyCollectedCardMod.ID))) {
                c.upgrade();
            }
        }
    }


    public void upp() {
        upgradeBaseCost(0);
    }
}