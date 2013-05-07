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
                TileHearth.FUEL_INVENTORY_INDEX, 80, 21) {
            @Override
            public boolean isItemValid(ItemStack itemstack) {
                return TileEntityFurnace.isItemFuel(itemstack);
            }
        });

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer,
                        inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                        8 + inventoryColumnIndex * 18,
                        54 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer,
                    actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 112));
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
