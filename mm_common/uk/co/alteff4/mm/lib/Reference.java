package uk.co.alteff4.mm.lib;

import uk.co.alteff4.mm.creativetab.CreativeTabGeneral;
import uk.co.alteff4.mm.creativetab.CreativeTabMaterials;
import net.minecraft.creativetab.CreativeTabs;

public class Reference {
    public static final String MOD_ID = "MMOMats";
    public static final String MOD_NAME = "MMO Materials";
    public static final String VERSION_NUMBER = "Alpha 0.1";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String DEPENDENCIES = "required-after:Forge@[7.7.1.650,)";
    public static final String FINGERPRINT = "@FINGERPRINT@";
    public static final int SECOND_IN_TICKS = 20;
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
    public static final String SERVER_PROXY_CLASS = "uk.co.alteff4.mm.core.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "uk.co.alteff4.mm.core.proxy.ClientProxy";
    public static final int VERSION_CHECK_ATTEMPTS = 3;

    public static CreativeTabs CREATIVE_TAB_MATERIALS = new CreativeTabMaterials(
            CreativeTabs.getNextID(), MOD_ID + "Materials");
    public static CreativeTabs CREATIVE_TAB_GENERAL = new CreativeTabGeneral(
            CreativeTabs.getNextID(), MOD_ID);
    
    public static String createUnlocalizedName(String name) {
        return MOD_ID.toLowerCase() + "." + name;
    }
}
