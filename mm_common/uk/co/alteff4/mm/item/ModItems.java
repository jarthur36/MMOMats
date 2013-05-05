package uk.co.alteff4.mm.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItems {
    
    public static Item stoneMats;
    
    public static void init(){
        stoneMats = new ItemStoneMat(5001);
        
        
        for(int meta=0;meta<6;meta++){
            LanguageRegistry.addName(new ItemStack(ModItems.stoneMats,1,meta), ItemStoneMat.STONE_MAT_NAMES[meta]);
        }
        
    }

}
