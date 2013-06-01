package uk.co.alteff4.mm.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemMM extends Item {

    public ItemMM(int id) {
        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                + ":"
                + this.getUnlocalizedName().substring(
                        this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    public String getLocalizedName(ItemStack stack) {
        return StatCollector.translateToLocal("item."
                + Reference
                        .createUnlocalizedName(this.getUnlocalizedName(stack)
                                .substring(
                                        this.getUnlocalizedName(stack).indexOf(
                                                ".") + 1)));
    }
}
