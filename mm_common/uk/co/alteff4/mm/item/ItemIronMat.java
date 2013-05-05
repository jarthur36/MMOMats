package uk.co.alteff4.mm.item;

import uk.co.alteff4.mm.lib.Strings;

public class ItemIronMat extends ItemMat {
    public ItemIronMat(int id) {
        super(id, "iron");
        this.setUnlocalizedName(Strings.IRON_MAT_NAME);
        maxStackSize = 64;
    }
}
