package uk.co.alteff4.mm.block;

import uk.co.alteff4.mm.lib.RenderIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.block.material.Material;

public class BlockAnvil extends BlockMM {

    protected BlockAnvil(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setUnlocalizedName(Strings.ANVIL_NAME);
        this.setHardness(5F);
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

        return RenderIds.anvilRenderId;
    }
    

}
