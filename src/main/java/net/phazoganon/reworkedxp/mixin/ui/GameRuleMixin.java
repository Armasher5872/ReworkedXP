package net.phazoganon.reworkedxp.mixin.ui;

import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

//Used to set keepInventory to true
@Mixin(GameRules.class)
public abstract class GameRuleMixin {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules$BooleanValue;create(Z)Lnet/minecraft/world/level/GameRules$Type;"),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=keepInventory")))
    private static boolean changeDefaultValueForKeepInventory(boolean original) {
        return true;
    }
}