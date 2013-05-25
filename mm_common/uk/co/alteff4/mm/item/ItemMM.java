package uk.co.alteff4.mm.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemMM extends Item {

    public ItemMM(int id) {
        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        String mmomatsName = this.getUnlocalizedName().substring(
                this.getUnlocalizedName().indexOf(".") + 1);

        itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                + ":" + mmomatsName.substring(mmomatsName.indexOf(".") + 1));
    }
}
