package uk.co.alteff4.mm.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import uk.co.alteff4.mm.item.ItemBlockCubus;
import uk.co.alteff4.mm.item.ItemBlockForge;
import uk.co.alteff4.mm.item.ItemBlockStandard;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.block.Block;

public class ModBlocks {
    public static Block standardBlocks;
    public static Block forgeBlocks;
    public static Block arcaneForgeBlocks;
    public static Block cubus;

    public static void init() {
        standardBlocks = new BlockStandard(BlockIds.STANDARD);
        forgeBlocks = new BlockForge(BlockIds.FORGE);
        arcaneForgeBlocks = new BlockArcaneForge(BlockIds.ARCANE_FORGE);
        cubus = new BlockCubus(BlockIds.CUBUS);

        GameRegistry.registerBlock(standardBlocks, ItemBlockStandard.class,
                Strings.STANDARD_BLOCKS_NAME);
        GameRegistry.registerBlock(forgeBlocks, ItemBlockForge.class,
                Strings.FORGE_NORMAL_NAME);
        GameRegistry.registerBlock(arcaneForgeBlocks, "arcaneForge");
        GameRegistry.registerBlock(cubus, ItemBlockCubus.class,
                Strings.CUBUS_NAME);

        LanguageRegistry.addName(arcaneForgeBlocks, "Arcane Forge");
    }

}
