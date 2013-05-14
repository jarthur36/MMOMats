package uk.co.alteff4.mm.item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.api.registry.MaterialRegistry;
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
    private HashMap<String, Icon[]> icons;

    public ItemMat(int id) {
        super(id);
        this.setCreativeTab(Reference.CREATIVE_TAB_MM);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.MATERIAL_NAME);
        this.icons = new HashMap<String, Icon[]>();
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        String name = is.getTagCompound().getString("MaterialType");
        return this.getUnlocalizedName() + "." + name + "."
                + Strings.tierNames[is.getItemDamage()];
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int unk, CreativeTabs tabs, List list) {
        Iterator<String> keyIter = MaterialRegistry.getKeyIterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("MaterialType", key);
            for (int i = 0; i < Strings.tierNames.length; i++) {
                ItemStack is = new ItemStack(this, 1, i);
                is.setTagCompound(tag);
                list.add(is);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        Iterator<String> keyIter = MaterialRegistry.getKeyIterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            Icon[] tempIcons = new Icon[Strings.tierNames.length];
            for (int i = 0; i < Strings.tierNames.length; i++) {
                String rawName = Strings.tierNames[i];
                String upperCase = Character.toUpperCase(rawName.charAt(0))
                        + rawName.substring(1);
                tempIcons[i] = iconRegister.registerIcon("mm:" + key
                        + upperCase);
            }
            icons.put(key, tempIcons);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(ItemStack stack, int renderPass) {

        if (stack.hasTagCompound()) {
            String key = stack.getTagCompound().getString("MaterialType");
            if (MaterialRegistry.containsMaterial(key)) {
                return icons.get(key)[stack.getItemDamage()];
            }
        } else {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("MaterialType", "stone");
            stack.setTagCompound(tag);
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 1;
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
