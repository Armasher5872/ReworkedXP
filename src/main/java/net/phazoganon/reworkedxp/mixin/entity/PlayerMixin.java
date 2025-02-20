package net.phazoganon.reworkedxp.mixin.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.phazoganon.reworkedxp.util.ModGamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow
    public int experienceLevel;
    @Shadow public abstract boolean isSpectator();

    @Shadow public int totalExperience;

    @Shadow public float experienceProgress;

    protected PlayerMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Inject(method = "getXpNeededForNextLevel", at = @At(value = "RETURN"), cancellable = true)
    private void getXpNeededForNextLevel(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(36);
    }
    @Inject(method = "getBaseExperienceReward", at = @At(value = "RETURN"), cancellable = true)
    private void getBaseExperienceReward(ServerLevel p_376359_, CallbackInfoReturnable<Integer> cir) {
        if (!p_376359_.getGameRules().getBoolean(ModGamerules.KEEP_EXPERIENCE) && !this.isSpectator()) {
            cir.setReturnValue(this.experienceLevel*36);
        }
        else {
            cir.setReturnValue(0);
        }
    }

    protected int getBaseExperienceReward(ServerLevel p_376359_) {
        return !p_376359_.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && !this.isSpectator() ? Math.min(this.experienceLevel * 7, 100) : 0;
    }
}