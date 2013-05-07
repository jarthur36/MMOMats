package uk.co.alteff4.mm.block;

import uk.co.alteff4.mm.lib.Strings;
import uk.co.alteff4.mm.tileentity.TileAnvil;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAnvil extends BlockMM {

    public BlockAnvil(int id) {
        super(id, Material.anvil);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName(Strings.ANVIL);
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
    public TileEntity createNewTileEntity(World world) {
        TileEntity tileAnvil = new TileAnvil();
        return tileAnvil;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

}
