package uk.co.alteff4.mm.configuration;

import java.io.File;
import java.util.logging.Level;

import uk.co.alteff4.mm.api.registry.MaterialRegistry;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.lib.ItemIds;
import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.lib.Strings;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

/**
 * 
 * MMO Materials
 * 
 * ConfigurationHandler
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ConfigurationHandler {
    public static Configuration configuration;

    public static void init(File configFile) {

        configuration = new Configuration(configFile);

        try {
            configuration.load();

            /* Block configs */
            BlockIds.FORGE = configuration.getBlock(Strings.FORGE_NORMAL_NAME,
                    BlockIds.FORGE_DEFAULT).getInt(BlockIds.FORGE_DEFAULT);
            BlockIds.STANDARD = configuration.getBlock(
                    Strings.STANDARD_BLOCKS_NAME, BlockIds.STANDARD_DEFAULT)
                    .getInt(BlockIds.STANDARD_DEFAULT);
            BlockIds.ARCANE_FORGE = configuration.getBlock(
                    Strings.FORGE_ARCANE_NAME, BlockIds.ARCANE_FORGE_DEFAULT)
                    .getInt(BlockIds.ARCANE_FORGE_DEFAULT);
            /* Block property configs */

            /* Item configs */
            MaterialRegistry.MATERIAL_ITEM_ID = configuration.getItem(
                    "itemMaterials", MaterialRegistry.MATERIAL_ITEM_ID_DEFAULT)
                    .getInt(MaterialRegistry.MATERIAL_ITEM_ID_DEFAULT);
            ItemIds.BELLOWS = configuration.getItem(Strings.BELLOWS_NAME,
                    ItemIds.BELLOWS_DEFAULT).getInt(ItemIds.BELLOWS_DEFAULT);
        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME
                    + " has had a problem loading its configuration");
        } finally {
            configuration.save();
        }
    }

    public static void set(String categoryName, String propertyName,
            String newValue) {

        configuration.load();
        if (configuration.getCategoryNames().contains(categoryName)) {
            if (configuration.getCategory(categoryName).containsKey(
                    propertyName)) {
                configuration.getCategory(categoryName).get(propertyName)
                        .set(newValue);
            }
        }
        configuration.save();
    }
}
