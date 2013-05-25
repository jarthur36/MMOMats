package uk.co.alteff4.mm.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.lib.Strings;

/**
 * 
 * MMO Materials
 * 
 * ItemBlockStandard
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemBlockStandard extends ItemBlock {
    private final static String[] subNames = { Strings.FIRE_RESISTANT_NAME };

    public ItemBlockStandard(int id) {
        super(id);
        this.setUnlocalizedName(Strings.STANDARD_BLOCKS_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        if (is.getItemDamage() >= subNames.length)
            return "";
        return "block."
                + Reference.createUnlocalizedName(Strings.STANDARD_BLOCKS_NAME)
                + "." + subNames[is.getItemDamage()];
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }
}
