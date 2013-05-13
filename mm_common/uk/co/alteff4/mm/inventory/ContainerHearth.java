package uk.co.alteff4.mm.inventory;

import uk.co.alteff4.mm.tileentity.TileHearth;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * 
 * MMO Materials
 * 
 * ContainerHearth
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ContainerHearth extends Container {

    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerHearth(InventoryPlayer inventoryPlayer,
            TileHearth tileHearth) {

        this.addSlotToContainer(new Slot(tileHearth,
                TileHearth.INPUT_INVENTORY_INDEX, 56, 17));

        this.addSlotToContainer(new Slot(tileHearth,
                TileHearth.OUTPUT_INVENTORY_INDEX, 116, 35) {
            @Override
            public boolean isItemValid(ItemStack itemstack) {
                return false;
            }
        });

        this.addSlotToContainer(new Slot(tileHearth,
                TileHearth.FUEL_INVENTORY_INDEX, 56, 53) {
            @Override
            public boolean isItemValid(ItemStack itemstack) {
                return TileEntityFurnace.isItemFuel(itemstack);
            }
        });

        for (int i = 0; i < PLAYER_INVENTORY_ROWS; ++i) {
            for (int j = 0; j < PLAYER_INVENTORY_COLUMNS; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer,
                        j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < PLAYER_INVENTORY_COLUMNS; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18,
                    142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer,
            int slotIndex) {

        return null;
    }
}
