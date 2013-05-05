package uk.co.alteff4.mm.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.lib.Strings;

/**
 * 
 * MMO Materials
 * 
 * ItemMat
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemMat extends ItemMM {
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    private String texturePrefix;

    public ItemMat(int id, String texturePrefix) {
        super(id);
        this.setCreativeTab(Reference.CREATIVE_TAB_MM);
        this.setHasSubtypes(true);
        this.texturePrefix = texturePrefix;
        this.icons = new Icon[Strings.tierNames.length];
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return this.getUnlocalizedName() + "."
                + Strings.tierNames[is.getItemDamage()];
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int unk, CreativeTabs tabs, List list) {
        for (int i = 0; i < Strings.tierNames.length; i++)
            list.add(new ItemStack(this, 1, i));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        for (int i = 0; i < Strings.tierNames.length; i++) {
            String rawName = Strings.tierNames[i];
            String upperCase = Character.toUpperCase(rawName.charAt(0))
                    + rawName.substring(1);
            icons[i] = iconRegister.registerIcon("mm:" + texturePrefix
                    + upperCase);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int dmg) {
        return icons[dmg];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        int meta = MathHelper.clamp_int(stack.getItemDamage(), 0, 5);

        if (meta == 5)
            return true;
        else
            return false;
    }

    @Override
    public String getItemDisplayName(ItemStack itemStack) {

        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 6);

        switch (meta) {
            case 0:
                return EnumChatFormatting.DARK_GRAY
                        + super.getItemDisplayName(itemStack);
            case 1:
                return EnumChatFormatting.GRAY
                        + super.getItemDisplayName(itemStack);
            case 2:
                return EnumChatFormatting.WHITE
                        + super.getItemDisplayName(itemStack);
            case 3:
                return EnumChatFormatting.GREEN
                        + super.getItemDisplayName(itemStack);
            case 4:
                return EnumChatFormatting.DARK_BLUE
                        + super.getItemDisplayName(itemStack);
            case 5:
                return EnumChatFormatting.GOLD
                        + super.getItemDisplayName(itemStack);
            default:
                return EnumChatFormatting.WHITE
                        + super.getItemDisplayName(itemStack);
        }
    }
}
