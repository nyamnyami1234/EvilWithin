package charbosses.bosses.Ironclad;

import charbosses.bosses.AbstractBossDeckArchetype;
import charbosses.bosses.AbstractCharBoss;
import charbosses.bosses.Defect.NewAge.ArchetypeAct2ClawNewAge;
import charbosses.bosses.Ironclad.NewAge.ArchetypeAct1StatusesNewAge;
import charbosses.bosses.Ironclad.NewAge.ArchetypeAct2MushroomsNewAge;
import charbosses.bosses.Ironclad.NewAge.ArchetypeAct3BlockNewAge;
import charbosses.cards.AbstractBossCard;
import charbosses.cards.anticards.ShieldSmash;
import charbosses.cards.red.EnBodySlam;
import charbosses.core.EnemyEnergyManager;
import charbosses.monsters.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
import downfall.downfallMod;
import downfall.monsters.NeowBoss;
import guardian.powers.ConstructPower;
import slimebound.SlimeboundMod;

public class CharBossIronclad extends AbstractCharBoss {
    public static final String ID = downfallMod.makeID("Ironclad");
    public static final String NAME = CardCrawlGame.languagePack.getCharacterString("Ironclad").NAMES[0];

    private Texture fgImg = ImageMaster.loadImage("downfallResources/images/fgShrooms.png");
    private Texture bgImg = ImageMaster.loadImage("downfallResources/images/bgShrooms.png");

    public CharBossIronclad() {
        super(NAME, ID, 80, -4.0f, -16.0f, 220.0f, 290.0f, null, 0.0f, -20.0f, PlayerClass.IRONCLAD);
        this.energyOrb = new EnergyOrbRed();
        this.energy = new EnemyEnergyManager(3);
        this.loadAnimation("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0f);
        final AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        this.stateData.setMix("Hit", "Idle", 0.1f);
        this.flipHorizontal = true;
        e.setTimeScale(0.6f);
        this.energyString = "[R]";
        type = EnemyType.BOSS;
    }

    @Override
    public void generateDeck() {
        //ArrayList<AbstractBossDeckArchetype> archetypes = new ArrayList<AbstractBossDeckArchetype>();
        AbstractBossDeckArchetype archetype;
        if (downfallMod.overrideBossDifficulty) {
            archetype = new ArchetypeAct1StatusesNewAge();
            downfallMod.overrideBossDifficulty = false;
            this.currentHealth -= 100;
        } else
            switch (AbstractDungeon.actNum) {
                case 1:
                    archetype = new ArchetypeAct1StatusesNewAge();
                    break;
                case 2:
                    archetype = new ArchetypeAct2MushroomsNewAge();
                    break;
                case 3:
                    archetype = new ArchetypeAct3BlockNewAge();
                    break;
                case 4: {

                    //SlimeboundMod.logger.info("Ironclad spawned at Archetype " + NeowBoss.Rezzes);
                    switch (NeowBoss.Rezzes) {
                        case 1:
                            archetype = new ArchetypeAct1StatusesNewAge();
                            break;
                        case 2:
                            archetype = new ArchetypeAct2MushroomsNewAge();
                            break;
                        case 3:
                            archetype = new ArchetypeAct3BlockNewAge();
                            break;
                        default:
                            archetype = new ArchetypeAct3BlockNewAge();
                            break;
                    }
                    break;
                }
                default:
                    archetype = new ArchetypeAct1StatusesNewAge();
                    break;
            }

        archetype.initialize();
        chosenArchetype = archetype;
//        if (AbstractDungeon.ascensionLevel >= 19) {
//            archetype.initializeBonusRelic();
//        }

        //archetypes.add(new ArchetypeIcStrike());
        //archetypes.add(new ArchetypeIcStrength());
        //archetypes.add(new ArchetypeIcRampage());
        //archetypes.add(new ArchetypeIcBlock());
        //this.chosenArchetype = archetypes.get(AbstractDungeon.monsterRng.random(archetypes.size() - 1));

        //this.chosenArchetype.simulateBuild(AbstractCharBoss.boss);
    }


    @Override
    public void loseBlock(int amount) {
        super.loseBlock(amount);
        for (AbstractCard c:hand.group){
            if (c instanceof EnBodySlam){
                c.applyPowers();
            }
        }
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
    }

    @Override
    public void onPlayAttackCardSound() {

        switch (MathUtils.random(2)) {
            case 0:
                CardCrawlGame.sound.play("VO_IRONCLAD_1A");
                break;
            case 1:
                CardCrawlGame.sound.play("VO_IRONCLAD_1B");
                break;
            case 2:
                CardCrawlGame.sound.play("VO_IRONCLAD_1C");
                break;
        }
    }

    @Override
    public void die() {
        super.die();

        switch (MathUtils.random(2)) {
            case 0:
                CardCrawlGame.sound.play("VO_IRONCLAD_2A");
                break;
            case 1:
                CardCrawlGame.sound.play("VO_IRONCLAD_2B");
                break;
            case 2:
                CardCrawlGame.sound.play("VO_IRONCLAD_2C");
                break;
        }

        downfallMod.saveBossFight(CharBossIronclad.ID);

        if (NeowBoss.neowboss != null){
            for (AbstractMonster m:AbstractDungeon.getCurrRoom().monsters.monsters){
                if (m instanceof Fortification || m instanceof MushroomPurple || m instanceof MushroomRed || m instanceof MushroomWhite){
                    AbstractDungeon.actionManager.addToBottom(new InstantKillAction(m));
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (chosenArchetype instanceof ArchetypeAct2MushroomsNewAge && NeowBoss.neowboss == null) {
            sb.setColor(Color.WHITE.cpy());
            sb.draw(this.bgImg, 0.0F, -10.0F, (float) Settings.WIDTH, 1080.0F * Settings.scale);
            sb.draw(this.fgImg, 0.0F, -20.0F * Settings.scale, (float) Settings.WIDTH, 1080.0F * Settings.scale);
        }
        super.render(sb);

    }
}


