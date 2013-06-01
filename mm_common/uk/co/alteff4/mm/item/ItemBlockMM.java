package uk.co.alteff4.mm.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import uk.co.alteff4.mm.lib.Reference;

/**
 *
 * MMO Materials
 *
 * ItemBlockMM
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemBlockMM extends ItemBlock {
    
    public ItemBlockMM(int id) {
        super(id);
    }

    @Override
    public String getLocalizedName(ItemStack stack) {
        return StatCollector.translateToLocal("block."
                + Reference.createUnlocalizedName(this.getUnlocalizedName(stack)
                        .substring(this.getUnlocalizedName(stack).indexOf(".") + 1)));
    }
}
