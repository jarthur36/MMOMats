package uk.co.alteff4.mm.block;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.MMOMats;
import uk.co.alteff4.mm.lib.GuiIds;
import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.tileentity.TileHearth;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9) {
        boolean showGui = false;
        if (!player.isSneaking()) {
            if (!world.isRemote) {
                int xOff = 0;
                int zOff = 0;
                if (world.getBlockId(x, y + 1, z) == 0) {
                    for (int i = -1; i < 2; i++) {
                        boolean broken = false;
                        for (int j = -1; j < 2; j++) {
                            TileEntity te = world.getBlockTileEntity(x - i, y,
                                    z - j);
                            if (te != null && te instanceof TileHearth) {
                                xOff = i;
                                zOff = j;
                                broken = true;
                                if (((TileHearth) te).isMultiblockPart())
                                    showGui = true;
                                break;
                            }
                        }
                        if (broken)
                            break;
                    }
                }

                if (showGui)
                    player.openGui(MMOMats.instance, GuiIds.HEARTH, world, x
                            - xOff, y, z - zOff);
            }
        }
        return showGui;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int unk1, int unk2) {
        super.breakBlock(world, x, y, z, unk1, unk2);

        if (world.getBlockMetadata(x, y, z) == 0) {
            int xOff = 0;
            int zOff = 0;
            int yOff = 0;
            boolean isBroken = false;
            for (int off = 0; off < 3; off++) {
                for (int i = -1; i <= 1; i++) {
                    boolean broken = false;
                    for (int j = -1; j <= 1; j++) {
                        TileEntity te = world.getBlockTileEntity(x - i,
                                y - off, z - j);
                        if (te != null && te instanceof TileHearth) {
                            xOff = i;
                            zOff = j;
                            yOff = off;
                            broken = true;
                            if (((TileHearth) te).isMultiblockPart())
                                isBroken = true;
                            break;
                        }
                    }
                    if (broken)
                        break;
                }
            }

            if (isBroken) {
                ((TileHearth) world.getBlockTileEntity(x - xOff, y - yOff, z
                        - zOff)).invalidateMultiblock();
            }
        }
    }
}
