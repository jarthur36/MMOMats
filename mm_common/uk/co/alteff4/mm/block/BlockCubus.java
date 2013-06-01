package uk.co.alteff4.mm.block;

import uk.co.alteff4.mm.tileentity.TileCubusArcana;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * MMO Materials
 * 
 * BlockCubus
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockCubus extends BlockMM {

    public BlockCubus(int id) {
        super(id, Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileCubusArcana();
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
    public int getRenderType() {
        return -1;
    }

}
