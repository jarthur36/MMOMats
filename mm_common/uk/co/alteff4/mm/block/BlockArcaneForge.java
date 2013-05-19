/**
* 
* MMO Materials
* 
* BlockArcaneForge.java
* 
* @author Alteff4
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
* 
*/

package uk.co.alteff4.mm.block;

import java.util.Random;

import uk.co.alteff4.mm.client.particle.AcceleratedParticle;
import uk.co.alteff4.mm.tileentity.TileArcaneForge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockArcaneForge extends BlockMM {

    
    public BlockArcaneForge(int id) {
        super(id, Material.iron);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("arcaneForge");
        
           }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileArcaneForge();
    }
    
    @Override
    public int getRenderType()
    {
        return -1;
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 1;
    }

  


}
