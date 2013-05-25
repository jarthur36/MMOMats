package uk.co.alteff4.mm.tileentity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.lib.Strings;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
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

    private int tickCount;
    private boolean isFired;

    public TileHearth() {
        super();
        inventory = new ItemStack[INVENTORY_SIZE];
        setHeat(0);
        setCoalAmount(0);
        setBurnTime(0);
        setItemBurnTime(0);
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

    public int getBurnTime() {
        return getInt("burnTime");
    }

    public void setBurnTime(int burnTime) {
        setInt("burnTime", burnTime);
    }

    public int getItemBurnTime() {
        return getInt("itemBurnTime");
    }

    public void setItemBurnTime(int burnTime) {
        setInt("itemBurnTime", burnTime);
    }

    public int getCoalAmount() {
        return getInt("coalAmount");
    }

    public void setCoalAmount(int amount) {
        setInt("coalAmount", amount);
    }

    public void increaseHeat(int amount) {
        if (getHeat() == 1000)
            return;
        if (getCoalAmount() > 0) {
            setHeat(getHeat() + amount);
        }
        if (getHeat() > 1000)
            setHeat(1000);
        PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 32,
                worldObj.provider.dimensionId, getDescriptionPacket());
    }

    public void decreaseHeat(int amount) {
        if (getHeat() == 0)
            return;
        int oldHeat = getHeat();
        setHeat(getHeat() - amount);
        if (getHeat() < 0)
            setHeat(0);
        if (getHeat() < oldHeat)
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 32,
                    worldObj.provider.dimensionId, getDescriptionPacket());
    }

    public void fire() {
        isFired = true;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
        if (getItemBurnTime() == 0) {
            setItemBurnTime(200);
        }

        return getBurnTime() * par1 / getItemBurnTime();
    }

    private void decrBurnTime(int amount) {
        setBurnTime(getBurnTime() - amount);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean hasToUpd = false;
        tickCount++;

        if (getBurnTime() > 0)
            decrBurnTime(1);

        if (!worldObj.isRemote) {
            if (isFired && inventory[FUEL_INVENTORY_INDEX] != null
                    && getHeat() < 100)
                if (tickCount == 20)
                    increaseHeat(1);

            if (getHeat() >= 100 && isFired && getBurnTime() > 0) {
                if (getState() < 2)
                    hasToUpd = true;
                setState((byte) 2);
            }

            if (getBurnTime() == 0) {
                setBurnTime(TileEntityFurnace
                        .getItemBurnTime(inventory[FUEL_INVENTORY_INDEX]));
                setItemBurnTime(getBurnTime());
                if (getBurnTime() > 0) {
                    hasToUpd = true;
                    decrStackSize(FUEL_INVENTORY_INDEX, 1);
                }
            }

            if (inventory[FUEL_INVENTORY_INDEX] == null) {
                if (tickCount == 20)
                    decreaseHeat(2);
            }
            if (inventory[FUEL_INVENTORY_INDEX] != null) {
                if (getCoalAmount() != inventory[FUEL_INVENTORY_INDEX].stackSize) {
                    setCoalAmount(inventory[FUEL_INVENTORY_INDEX].stackSize);
                    hasToUpd = true;
                }
                if (getState() == 0) {
                    setState((byte) 1);
                    hasToUpd = true;
                }
            } else if (getState() > 0) {
                setCoalAmount(0);
                if (getBurnTime() == 0)
                    setState((byte) 0);
                isFired = false;
                hasToUpd = true;
            }
            if (hasToUpd) {
                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord,
                        32, this.worldObj.provider.dimensionId,
                        getDescriptionPacket());
            }
        }
        if (tickCount == 20) {
            tickCount = 0;
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
        isFired = nbtTagCompound.getBoolean("Fired");
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
        nbtTagCompound.setBoolean("Fired", isFired);
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
