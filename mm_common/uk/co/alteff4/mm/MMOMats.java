package uk.co.alteff4.mm;

import java.io.File;

import uk.co.alteff4.mm.block.ModBlocks;
import uk.co.alteff4.mm.configuration.ConfigurationHandler;
import uk.co.alteff4.mm.core.handlers.LocalizationHandler;
import uk.co.alteff4.mm.core.proxy.CommonProxy;
import uk.co.alteff4.mm.item.ModItems;
import uk.co.alteff4.mm.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * MMO Materials
 * 
 * MMOMats
 * 
 * @author alteff4
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES, certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false)
public class MMOMats {
    @Instance(Reference.MOD_ID)
    public static MMOMats instance;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        LocalizationHandler.loadLanguages();
        ConfigurationHandler.init(new File(event.getModConfigurationDirectory()
                .getAbsolutePath()
                + File.separator
                + Reference.CHANNEL_NAME
                + File.separator + Reference.MOD_ID + ".cfg"));

        ModItems.init();
        ModBlocks.init();
    }

    @Init
    public void load(FMLInitializationEvent event) {

        proxy.initRenderingAndTextures();
    }

    @PostInit
    public void modsLoaded(FMLPostInitializationEvent event) {

    }

}
