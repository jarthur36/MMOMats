package uk.co.alteff4.mm.block;

import cpw.mods.fml.common.registry.GameRegistry;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.block.Block;

public class ModBlocks {
    public static Block anvil;
    
    public static void init(){
        anvil = new BlockAnvil(BlockIds.ANVIL);
        
        GameRegistry.registerBlock(anvil, Strings.ANVIL);
    }

}
