package uk.co.alteff4.mm.item;

import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * 
 * MMO Materials
 * 
 * ItemBlockForge
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemBlockForge extends ItemBlock {

    private final static String[] subNames = { Strings.ANVIL_NAME,
            Strings.HEARTH_NAME, Strings.BELLOWS_NAME, Strings.CHIMNEY_NAME };

    public ItemBlockForge(int id) {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.FORGE_NORMAL_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        if (is.getItemDamage() >= subNames.length)
            return "";
        return "block." + Strings.FORGE_NORMAL_NAME + "."
                + subNames[is.getItemDamage()];
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }
}
