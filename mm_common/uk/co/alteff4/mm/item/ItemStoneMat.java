package uk.co.alteff4.mm.item;

import uk.co.alteff4.mm.lib.Strings;

public class ItemStoneMat extends ItemMat {
    public ItemStoneMat(int id) {
        super(id, "stone");
        this.setUnlocalizedName(Strings.STONE_MAT_NAME);
        maxStackSize = 64;
    }
}
