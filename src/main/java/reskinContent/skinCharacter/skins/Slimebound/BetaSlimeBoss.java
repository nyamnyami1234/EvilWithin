package reskinContent.skinCharacter.skins.Slimebound;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import reskinContent.reskinContent;
import reskinContent.skinCharacter.AbstractSkin;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

public class BetaSlimeBoss extends AbstractSkin {

    public BetaSlimeBoss() {
        this.NAME = CardCrawlGame.languagePack.getUIString("reskinContent:ReSkin").TEXT[1];
        this.DESCRIPTION = CardCrawlGame.languagePack.getCharacterString(SlimeboundCharacter.ID).TEXT[0];
        this.portraitStatic_IMG = ImageMaster.loadImage(SlimeboundMod.getResourcePath("charSelect/portrait.png"));

        this.SHOULDER1 = "slimeboundResources/SlimeboundImages/char/shoulder.png";
        this.SHOULDER2 = "slimeboundResources/SlimeboundImages/char/shoulderR.png";
        this.CORPSE = "slimeboundResources/SlimeboundImages/char/corpse.png";
        this.atlasURL = "reskinContent/img/Slimebound/BetaSlimeBoss/animation/BetaSlimeBoss.atlas";
        this.jsonURL = "reskinContent/img/Slimebound/BetaSlimeBoss/animation/BetaSlimeBoss.json";
        this.renderscale = 1.0f;
    }
}


