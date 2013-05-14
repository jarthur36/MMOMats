package uk.co.alteff4.mm.block;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.MMOMats;
import uk.co.alteff4.mm.lib.GuiIds;
import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.tileentity.TileAnvil;
import uk.co.alteff4.mm.tileentity.TileHearth;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * 
 * MMO Materials
 * 
 * BlockAnvil
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockForge extends BlockMM {

    public BlockForge(int id) {
        super(id, Material.anvil);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(this, 1, 0));
        subItems.add(new ItemStack(this, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase()
                + ":brickFire");
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z,
            AxisAlignedBB bounds, List list, Entity entity) {
        if (world.getBlockMetadata(x, y, z) == 1) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
            super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
            float f = 0.125F;
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1F, 1.0F);
            super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1F, f);
            super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1F, 1.0F);
            super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1F, 1.0F);
            super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
            setBlockBounds(0, 0, 0, 1, 0.5625F, 1);
            return;
        }
        this.setBlockBounds(0, 0, 0, 1, 1, 1);
        super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9) {
        switch (world.getBlockMetadata(x, y, z)) {
            case 0:
                return false;
            case 1:
                player.openGui(MMOMats.instance, GuiIds.HEARTH, world, x, y, z);
                return true;
        }
        return false;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) == 1) {
            if (((TileHearth) world.getBlockTileEntity(x, y, z)).getState() == 1) {
                return 15;
            }
        }
        return 0;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return super.createNewTileEntity(world);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileAnvil();
            case 1:
                return new TileHearth();
        }
        return null;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

}
