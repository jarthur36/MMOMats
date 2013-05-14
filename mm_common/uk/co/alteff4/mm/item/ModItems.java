package uk.co.alteff4.mm.item;

import uk.co.alteff4.mm.lib.ItemIds;
import net.minecraft.item.Item;

public class ModItems {

    public static Item itemMaterials;

    public static void init() {
        itemMaterials = new ItemMat(ItemIds.MATERIALS);
    }

}
