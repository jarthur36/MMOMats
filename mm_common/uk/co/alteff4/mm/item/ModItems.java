package uk.co.alteff4.mm.item;

import cpw.mods.fml.common.registry.GameRegistry;
import uk.co.alteff4.mm.lib.ItemIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.item.Item;

public class ModItems {

    public static Item stoneMats;
    public static Item ironMats;

    public static void init() {
        stoneMats = new ItemStoneMat(ItemIds.STONE_MAT);
        ironMats = new ItemIronMat(ItemIds.IRON_MAT);

        GameRegistry.registerItem(stoneMats, Strings.STONE_MAT_NAME);
        GameRegistry.registerItem(ironMats, Strings.IRON_MAT_NAME);
    }

}
