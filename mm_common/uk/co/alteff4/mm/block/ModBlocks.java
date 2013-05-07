package uk.co.alteff4.mm.block;

import cpw.mods.fml.common.registry.GameRegistry;
import uk.co.alteff4.mm.item.ItemBlockForge;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.block.Block;

public class ModBlocks {
    public static Block forgeBlocks;

    public static void init() {
        forgeBlocks = new BlockForge(BlockIds.FORGE);

        GameRegistry.registerBlock(forgeBlocks, ItemBlockForge.class,
                Strings.FORGE_NORMAL_NAME);
    }

}
