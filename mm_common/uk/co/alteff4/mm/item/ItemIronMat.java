package uk.co.alteff4.mm.item;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.lib.Strings;

public class ItemIronMat extends ItemMM {
    static final String[] IRON_MAT_NAMES = new String[] { "Iron Scraps" , "Iron Chunk" , "Iron Ingot" , "Refined Iron Ingot" , "Polished Iron Artifacts" , "Ancient Iron Core" };
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    
    public ItemIronMat(int id) {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.STONE_NAME);
        this.setCreativeTab(Reference.CREATIVE_TAB_MM);
        maxStackSize = 64;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);
        return IRON_MAT_NAMES[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int meta) {

        int j = MathHelper.clamp_int(meta, 0, 5);
        return icons[j];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        icons = new Icon[IRON_MAT_NAMES.length];

        for (int i = 0; i < IRON_MAT_NAMES.length; ++i) {
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + i);
        }
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
                return EnumChatFormatting.DARK_GRAY + super.getItemDisplayName(itemStack);
            case 1:
                return EnumChatFormatting.GRAY + super.getItemDisplayName(itemStack);
            case 2:
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
            case 3:
                return EnumChatFormatting.GREEN + super.getItemDisplayName(itemStack);
            case 4:
                return EnumChatFormatting.DARK_BLUE + super.getItemDisplayName(itemStack);
            case 5:
                return EnumChatFormatting.GOLD + super.getItemDisplayName(itemStack);
            default:
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
        }
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List list) {

        for (int meta = 0; meta < 6; ++meta) {
            list.add(new ItemStack(id, 1, meta));
            
        }
    }
}
