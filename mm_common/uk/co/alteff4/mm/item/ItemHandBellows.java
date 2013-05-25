package uk.co.alteff4.mm.item;

import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.item.Item;

/**
 * 
 * MMO Materials
 * 
 * ItemHandBellows
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemHandBellows extends ItemMM {

    public ItemHandBellows(int id) {
        super(id);
        this.setCreativeTab(Reference.CREATIVE_TAB_GENERAL);

        this.setUnlocalizedName(Reference
                .createUnlocalizedName(Strings.BELLOWS_NAME));
        this.setMaxDamage(30);
        this.setMaxStackSize(1);
    }
    
    /*@Override
    public boolean onItemUse(ItemStack stack,
            EntityPlayer player, World world, int x, int y,
            int z, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            if(world.getBlockTileEntity(x, y, z) instanceof TileHearth) {
                
            }
        }
        return false;
    }*/
}
