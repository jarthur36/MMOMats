package uk.co.alteff4.mm.block;


import uk.co.alteff4.mm.tileentity.TileAnvil;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAnvil extends BlockMM {

    public BlockAnvil(int par1, Material par2Material) {
        super(par1, par2Material);
        // TODO Auto-generated constructor stub
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
    public boolean hasTileEntity() {

        return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        TileEntity tileAnvil = new TileAnvil();
        return tileAnvil;
        
    }

    @Override
    public int getRenderType() {

        return -1;
    }

}
