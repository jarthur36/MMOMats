package uk.co.alteff4.mm.tileentity;

import cpw.mods.fml.common.network.PacketDispatcher;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * MMO Materials
 * 
 * TileHearth
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileHearth extends TileMM implements IInventory {

    /**
     * The ItemStacks that hold the items currently being used in the Calcinator
     */
    private ItemStack[] inventory;

    private final int INVENTORY_SIZE = 3;

    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int FUEL_INVENTORY_INDEX = 0;
    public static final int OUTPUT_INVENTORY_INDEX = 2;

    public TileHearth() {
        super();
        inventory = new ItemStack[INVENTORY_SIZE];
        setHeat(0);
        setCoalAmount(0);
        setIsMultiPart(false);
    }

    public boolean isMultiblockPart() {
        return getInt("validMultiBlock") == 1;
    }

    private void setIsMultiPart(boolean value) {
        if (value)
            setInt("validMultiBlock", 1);
        else
            setInt("validMultiBlock", 0);
    }

    public void validateMultiBlock() {
        int validBottomBlocks = 0;
        int validMidBlocks = 0;
        int validUpperBlocks = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (this.isValidForgePart(xCoord - i, yCoord, zCoord - j)
                        && !(i == 0 && j == 0))
                    validBottomBlocks++;
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (this.isValidForgePart(xCoord - i, yCoord + 1, zCoord - j))
                    validMidBlocks++;
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (this.isValidForgePart(xCoord - i, yCoord + 2, zCoord - j)
                        && !(i == 0 && j == 0))
                    validUpperBlocks++;
            }
        }
        ForgeDirection off = ForgeDirection.UP;
        if (validMidBlocks == 5) {
            // Checking South face of the structure
            if (!(worldObj.getBlockId(xCoord, yCoord + 1, zCoord + 1) == 0
                    && worldObj.getBlockId(xCoord - 1, yCoord + 1, zCoord + 1) == 0 && worldObj
                        .getBlockId(xCoord + 1, yCoord + 1, zCoord + 1) == 0)) {
                validMidBlocks = 4;
            } else {
                off = ForgeDirection.SOUTH;
            }

            // Checking North face of the structure
            if (!(worldObj.getBlockId(xCoord, yCoord + 1, zCoord - 1) == 0
                    && worldObj.getBlockId(xCoord - 1, yCoord + 1, zCoord - 1) == 0 && worldObj
                        .getBlockId(xCoord + 1, yCoord + 1, zCoord - 1) == 0)) {
                if (off == ForgeDirection.UP)
                    validMidBlocks = 4;
            } else {
                if (off == ForgeDirection.UP) {
                    off = ForgeDirection.NORTH;
                    validMidBlocks = 5;
                } else {
                    validMidBlocks = 4;
                }
            }

            // Checking West face of the structure
            int offX = ForgeDirection.WEST.offsetX;
            if (!(worldObj.getBlockId(xCoord + offX, yCoord + 1, zCoord) == 0
                    && worldObj.getBlockId(xCoord + offX, yCoord + 1,
                            zCoord - 1) == 0 && worldObj.getBlockId(xCoord
                    + offX, yCoord + 1, zCoord + 1) == 0)) {
                if (off == ForgeDirection.UP)
                    validMidBlocks = 4;
            } else {
                if (off == ForgeDirection.UP) {
                    off = ForgeDirection.WEST;
                    validMidBlocks = 5;
                } else {
                    validMidBlocks = 4;
                }
            }

            offX = ForgeDirection.EAST.offsetX;
            if (!(worldObj.getBlockId(xCoord + offX, yCoord + 1, zCoord) == 0
                    && worldObj.getBlockId(xCoord + offX, yCoord + 1,
                            zCoord - 1) == 0 && worldObj.getBlockId(xCoord
                    + offX, yCoord + 1, zCoord + 1) == 0)) {
                if (off == ForgeDirection.UP)
                    validMidBlocks = 4;
            } else {
                if (off == ForgeDirection.UP) {
                    off = ForgeDirection.EAST;
                    validMidBlocks = 5;
                } else {
                    validMidBlocks = 4;
                }
            }
        }
        if (validUpperBlocks == 5 && !off.equals(ForgeDirection.UP)) {
            int offX = off.offsetX;
            int offZ = off.offsetZ;
            if (!(worldObj.getBlockId(xCoord + offX, yCoord + 2, zCoord + offZ) == 0
                    && worldObj.getBlockId(xCoord + offX, yCoord + 2, zCoord
                            + offZ) == 0 && worldObj.getBlockId(xCoord + offX,
                    yCoord + 2, zCoord + offZ) == 0)) {
                validUpperBlocks = 4;
            }
        }
        if (validBottomBlocks == 8
                && validMidBlocks == 5
                && validUpperBlocks == 5
                && worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == 0
                && worldObj.getBlockTileEntity(xCoord, yCoord + 2, zCoord) instanceof TileChimney
                && worldObj.getBlockTileEntity(xCoord, yCoord + 3, zCoord) instanceof TileChimney) {
            setIsMultiPart(true);
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 32,
                    worldObj.provider.dimensionId, getDescriptionPacket());
        }
    }

    public void invalidateMultiblock() {
        setIsMultiPart(false);
        PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 32,
                worldObj.provider.dimensionId, getDescriptionPacket());
    }

    public int getHeat() {
        return getInt("heat");
    }

    public void setHeat(int heat) {
        setInt("heat", heat);
    }

    public int getCoalAmount() {
        return getInt("coalAmount");
    }

    public void setCoalAmount(int amount) {
        setInt("coalAmount", amount);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean hasToUpd = false;

        if (!worldObj.isRemote) {
            if (inventory[FUEL_INVENTORY_INDEX] != null) {
                if (getCoalAmount() != inventory[FUEL_INVENTORY_INDEX].stackSize) {
                    setCoalAmount(inventory[FUEL_INVENTORY_INDEX].stackSize);
                    hasToUpd = true;
                }
                if (getState() == 0) {
                    setState((byte) 1);
                    hasToUpd = true;
                }
            } else if (getState() == 1) {
                setCoalAmount(0);
                setState((byte) 0);
                hasToUpd = true;
            }
            if (hasToUpd) {
                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord,
                        32, this.worldObj.provider.dimensionId,
                        getDescriptionPacket());
            }
        }
    }

    @Override
    public void setState(byte state) {
        super.setState(state);
        if (worldObj.isRemote)
            worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {

        return inventory.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {

        return this.hasCustomName() ? this.getCustomName() : this
                .isMultiblockPart() ? Strings.CONTAINER_FORGE
                : Strings.CONTAINER_HEARTH;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if (inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
    }

    @Override
    public boolean isInvNameLocalized() {

        return this.hasCustomName();
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    public boolean isValidForgePart(int x, int y, int z) {
        return worldObj.getBlockId(x, y, z) == BlockIds.STANDARD
                && worldObj.getBlockMetadata(x, y, z) == 0;
    }

    public boolean isAirBlock(int x, int y, int z) {
        return worldObj.getBlockId(x, y, z) == 0;
    }

}
