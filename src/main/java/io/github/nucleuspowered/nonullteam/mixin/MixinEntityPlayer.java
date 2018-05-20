package io.github.nucleuspowered.nonullteam.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {

    @Shadow @Nullable public abstract Scoreboard getWorldScoreboard();

    @Inject(method = "getTeam", at = @At("HEAD"))
    private void onGetTeamHead(CallbackInfoReturnable<Team> cir) {
        if (this.getWorldScoreboard() == null) {
            cir.setReturnValue(null);
        }
    }

}
