package uk.co.alteff4.mm.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.lib.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * MMO Materials
 * 
 * BlockStandard
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockStandard extends BlockMM {

    public BlockStandard(int id) {
        super(id, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(this, 1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                + ":brickFire");
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLiving entityLiving, ItemStack itemStack) {
    }
}
