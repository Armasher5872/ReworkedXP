package net.phazoganon.reworkedxp.mixin.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.phazoganon.reworkedxp.util.ModGamerules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends PlayerMixin {
    protected ServerPlayerMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Shadow
    public abstract ServerLevel serverLevel();
    @Inject(method = "restoreFrom", at = @At(value = "TAIL"))
    private void restoreFrom(ServerPlayer pThat, boolean pKeepEverything, CallbackInfo ci) {
        if (this.serverLevel().getGameRules().getBoolean(ModGamerules.KEEP_EXPERIENCE)) {
            this.experienceLevel = pThat.experienceLevel;
            this.totalExperience = pThat.totalExperience;
            this.experienceProgress = pThat.experienceProgress;
        }
    }
    @Redirect(method = "restoreFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/level/ServerPlayer;experienceLevel:I", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void restoreFromExperienceLevel(ServerPlayer instance, int value) {}
    @Redirect(method = "restoreFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/level/ServerPlayer;totalExperience:I", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void restoreFromExperience(ServerPlayer instance, int value) {}
    @Redirect(method = "restoreFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/level/ServerPlayer;experienceProgress:F", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void restoreFromExperienceProgress(ServerPlayer instance, float value) {}
}