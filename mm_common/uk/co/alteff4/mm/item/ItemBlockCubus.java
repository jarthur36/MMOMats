package uk.co.alteff4.mm.item;

import net.minecraft.item.ItemStack;
import uk.co.alteff4.mm.lib.Strings;

/**
 * 
 * MMO Materials
 * 
 * ItemBlockCubus
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemBlockCubus extends ItemBlockMM {
    private final static String[] subNames = { Strings.CUBUS_ARCANA_NAME };

    public ItemBlockCubus(int id) {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.CUBUS_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        if (is.getItemDamage() >= subNames.length)
            return "";
        return "block." + Strings.CUBUS_NAME + "."
                + subNames[is.getItemDamage()];
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }
}
