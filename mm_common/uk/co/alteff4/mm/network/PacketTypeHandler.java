package uk.co.alteff4.mm.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import uk.co.alteff4.mm.lib.Reference;
import uk.co.alteff4.mm.network.packet.PacketMM;
import uk.co.alteff4.mm.network.packet.PacketTileUpdate;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * PaleoCraft
 *
 * PacketTypeHandler
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum PacketTypeHandler {
    TILE(PacketTileUpdate.class);

    private Class<? extends PacketMM> clazz;

    PacketTypeHandler(Class<? extends PacketMM> clazz) {
        this.clazz = clazz;
    }

    public static PacketMM buildPacket(byte[] data) {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        PacketMM packet = null;

        try {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }

    public static PacketMM buildPacket(PacketTypeHandler type) {

        PacketMM packet = null;

        try {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(PacketMM packetPC) {

        byte[] data = packetPC.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = Reference.CHANNEL_NAME;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetPC.isChunkDataPacket;

        return packet250;
    }
}