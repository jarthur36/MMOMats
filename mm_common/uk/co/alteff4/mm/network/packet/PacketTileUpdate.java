package uk.co.alteff4.mm.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import uk.co.alteff4.mm.MMOMats;
import uk.co.alteff4.mm.network.PacketTypeHandler;

import net.minecraft.network.INetworkManager;
import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.common.network.Player;

/**
 * PaleoCraft
 * 
 * PacketTileUpdate
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketTileUpdate extends PacketMM {
    public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName;
    public HashMap<String, Integer> addIntegers;

    public PacketTileUpdate() {
        super(PacketTypeHandler.TILE, true);
    }

    public PacketTileUpdate(int x, int y, int z, ForgeDirection orientation,
            byte state, String customName, HashMap<String, Integer> addIntegers) {
        super(PacketTypeHandler.TILE, true);
        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = (byte) orientation.ordinal();
        this.state = state;
        this.customName = customName;
        this.addIntegers = addIntegers;
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeByte(orientation);
        data.writeByte(state);
        data.writeUTF(customName);
        data.writeInt(addIntegers.size());
        Iterator<Integer> tempVal = addIntegers.values().iterator();
        String[] tempKey = addIntegers.keySet().toArray(new String[addIntegers.size()]);
        for (int i = 0; tempVal.hasNext(); i++) {
            int val = tempVal.next();
            String key = tempKey[i];
            data.writeUTF(key);
            data.writeInt(val);
        }
    }

    @Override
    public void readData(DataInputStream data) throws IOException {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();
        orientation = data.readByte();
        state = data.readByte();
        customName = data.readUTF();
        addIntegers = new HashMap<String, Integer>();
        int addCount = data.readInt();
        for (int i = 0; i < addCount; i++) {
            String key = data.readUTF();
            int val = data.readInt();
            addIntegers.put(key, val);
        }
    }

    @Override
    public void execute(INetworkManager manager, Player player) {
        MMOMats.proxy.handleTileEntityPacket(x, y, z,
                ForgeDirection.getOrientation(orientation), state, customName, addIntegers);
    }
}
