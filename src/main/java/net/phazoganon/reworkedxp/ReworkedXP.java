package net.phazoganon.reworkedxp;

import net.neoforged.fml.common.Mod;
import net.phazoganon.reworkedxp.util.ModGamerules;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ReworkedXP.MODID)
public class ReworkedXP {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "reworkedxp";
    public ReworkedXP() {
        init();
    }
    public static void init() {
        ModGamerules.init();
    }
}