package uk.co.alteff4.mm.creativetab;

import uk.co.alteff4.mm.api.registry.MaterialRegistry;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * 
 * MMO Materials
 * 
 * CreativeTabMaterials
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CreativeTabMaterials extends CreativeTabs {

    public CreativeTabMaterials(int id, String name) {
        super(id, name);
    }

    @Override
    public ItemStack getIconItemStack() {
        return MaterialRegistry.createStack(Strings.STONE_MAT_NAME, 1, 4);
    }

}
