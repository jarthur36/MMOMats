package uk.co.alteff4.mm.configuration;

import java.io.File;
import java.util.logging.Level;

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
            BlockIds.ANVIL = configuration.getBlock(Strings.ANVIL,
                    BlockIds.ANVIL_DEFAULT).getInt(BlockIds.ANVIL_DEFAULT);
            /* Block property configs */

            /* Item configs */
            ItemIds.IRON_MAT = configuration.getItem(Strings.IRON_MAT_NAME,
                    ItemIds.IRON_MAT_DEFAULT).getInt(
                    ItemIds.IRON_MAT_DEFAULT);
            ItemIds.STONE_MAT = configuration.getItem(Strings.STONE_MAT_NAME,
                    ItemIds.STONE_MAT_DEFAULT).getInt(
                    ItemIds.STONE_MAT_DEFAULT);
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
