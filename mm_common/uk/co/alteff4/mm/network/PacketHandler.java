package uk.co.alteff4.mm.network;

import uk.co.alteff4.mm.network.packet.PacketMM;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * PaleoCraft
 * 
 * PacketHandler
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player) {
        PacketMM packetMM = PacketTypeHandler.buildPacket(packet.data);
        // Execute the appropriate actions based on the PacketMM type
        packetMM.execute(manager, player);
    }

}
