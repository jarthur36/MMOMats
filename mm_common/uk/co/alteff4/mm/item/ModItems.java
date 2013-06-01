package uk.co.alteff4.mm.item;

import cpw.mods.fml.common.registry.GameRegistry;
import uk.co.alteff4.mm.api.material.MMOMaterial;
import uk.co.alteff4.mm.api.registry.MaterialRegistry;
import uk.co.alteff4.mm.lib.ItemIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.item.Item;

public class ModItems {

    public static Item itemMaterials;
    public static Item itemBellows;

    public static void init() {
        itemMaterials = new ItemMaterial(MaterialRegistry.MATERIAL_ITEM_ID);
        itemBellows = new ItemHandBellows(ItemIds.BELLOWS);
        
        GameRegistry.registerItem(itemMaterials, Strings.MATERIAL_NAME);
        GameRegistry.registerItem(itemBellows, Strings.BELLOWS_NAME);
        
        initMaterials();
    }
    
    private static void initMaterials() {
        MaterialRegistry.addMaterial(Strings.STONE_MAT_NAME, new MMOMaterial(
                Strings.STONE_MAT_NAME, Strings.STONE_MAT_NAME));
        MaterialRegistry.addMaterial(Strings.IRON_MAT_NAME, new MMOMaterial(
                Strings.IRON_MAT_NAME, Strings.IRON_MAT_NAME));
    }

}
