package uk.co.alteff4.mm.item;

import uk.co.alteff4.mm.lib.ItemIds;
import net.minecraft.item.Item;

public class ModItems {

    public static Item stoneMats;
    public static Item ironMats;

    public static void init() {
        stoneMats = new ItemStoneMat(ItemIds.STONE_MAT);
        ironMats = new ItemIronMat(ItemIds.IRON_MAT);
    }

}
