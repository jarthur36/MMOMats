package uk.co.alteff4.mm.block;

import cpw.mods.fml.common.registry.GameRegistry;
import uk.co.alteff4.mm.lib.BlockIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
    public static Block anvil;

    public static void init(){
        anvil = new BlockAnvil(BlockIds.ANVIL,Material.iron);
        GameRegistry.registerBlock(anvil, "anvil");
    }
}
