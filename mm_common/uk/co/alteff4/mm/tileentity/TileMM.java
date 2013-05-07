package uk.co.alteff4.mm.tileentity;

import java.util.HashMap;
import java.util.Iterator;

import uk.co.alteff4.mm.lib.Strings;
import uk.co.alteff4.mm.network.packet.PacketTileUpdate;
import uk.co.alteff4.mm.network.PacketTypeHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 *
 * MMO Materials
 *
 * TileMM
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileMM extends TileEntity {
    private ForgeDirection orientation;
    private byte state;
    private String customName;
    private HashMap<String, Integer> addIntegers;

    public TileMM() {
        orientation = ForgeDirection.SOUTH;
        state = 0;
        customName = "";
        addIntegers = new HashMap<String, Integer>();
    }

    public ForgeDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation) {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    public short getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public boolean hasCustomName() {
        return customName != null && customName.length() > 0;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey(Strings.NBT_TE_DIRECTION)) {
            this.setOrientation(ForgeDirection.getOrientation(nbtTagCompound
                    .getByte(Strings.NBT_TE_DIRECTION)));
        }

        if (nbtTagCompound.hasKey(Strings.NBT_TE_STATE)) {
            state = nbtTagCompound.getByte(Strings.NBT_TE_STATE);
        }

        if (nbtTagCompound.hasKey(Strings.NBT_TE_NAME)) {
            customName = nbtTagCompound.getString(Strings.NBT_TE_NAME);
        }
        
        NBTTagCompound addition = nbtTagCompound.getCompoundTag("Addition");
        addIntegers = new HashMap<String, Integer>();
        int addCount = addition.getTags().size();
        Object[] tagsRaw = addition.getTags().toArray();
        NBTBase[] tags = new NBTBase[addCount];
        for(int i = 0; i < addCount; i++) {
            tags[i] = (NBTBase) tagsRaw[i];
        }
        for (int i = 0; i < addCount; i++) {
            String key = tags[i].getName();
            addIntegers.put(key, addition.getInteger(key));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Strings.NBT_TE_DIRECTION,
                (byte) orientation.ordinal());
        nbtTagCompound.setByte(Strings.NBT_TE_STATE, state);

        if (this.hasCustomName()) {
            nbtTagCompound.setString(Strings.NBT_TE_NAME, customName);
        }
        
        NBTTagCompound addition = new NBTTagCompound();
        
        Iterator<Integer> tempVal = addIntegers.values().iterator();
        String[] tempKey = addIntegers.keySet().toArray(new String[addIntegers.size()]);
        for (int i = 0; tempVal.hasNext(); i++) {
            int val = tempVal.next();
            String key = tempKey[i];
            addition.setInteger(key, val);
        }
        nbtTagCompound.setCompoundTag("Addition", addition);
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketTypeHandler.populatePacket(new PacketTileUpdate(xCoord,
                yCoord, zCoord, orientation, state, customName, addIntegers));
    }

    /**
     * @return the addIntegers
     */
    public HashMap<String, Integer> getAddIntegers() {
        return addIntegers;
    }

    /**
     * @param addIntegers the addIntegers to set
     */
    public void setAddIntegers(HashMap<String, Integer> addIntegers) {
        this.addIntegers = addIntegers;
    }
    
    public int getInt(String key) {
        if(addIntegers.containsKey(key))
            return addIntegers.get(key);
        return 0;
    }

    public void setInt(String key, int value) {
        addIntegers.put(key, value);
    }
}
