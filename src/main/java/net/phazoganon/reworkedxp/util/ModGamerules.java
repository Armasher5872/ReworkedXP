package net.phazoganon.reworkedxp.util;

import net.minecraft.world.level.GameRules;

public class ModGamerules {
    public static final GameRules.Key<GameRules.BooleanValue> KEEP_EXPERIENCE = GameRules
            .register("keepExperience", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
    public static void init() {
    }
}