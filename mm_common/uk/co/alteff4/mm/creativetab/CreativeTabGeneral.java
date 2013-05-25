package uk.co.alteff4.mm.creativetab;

import uk.co.alteff4.mm.lib.BlockIds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * 
 * MMO Materials
 * 
 * CreativeTabGeneral
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CreativeTabGeneral extends CreativeTabs {

    public CreativeTabGeneral(int id, String name) {
        super(id, name);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(BlockIds.FORGE, 1, 1);
    }

}
