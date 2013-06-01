package uk.co.alteff4.mm.core.proxy;

import java.util.HashMap;

import uk.co.alteff4.mm.client.gui.inventory.GuiForge;
import uk.co.alteff4.mm.client.gui.inventory.GuiHearth;
import uk.co.alteff4.mm.inventory.ContainerForge;
import uk.co.alteff4.mm.inventory.ContainerHearth;
import uk.co.alteff4.mm.lib.GuiIds;
import uk.co.alteff4.mm.tileentity.TileAnvil;
import uk.co.alteff4.mm.tileentity.TileChimney;
import uk.co.alteff4.mm.tileentity.TileCubusArcana;
import uk.co.alteff4.mm.tileentity.TileHearth;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * MMO Materials
 * 
 * CommonProxy
 * 
 * @author alteff4
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommonProxy implements IGuiHandler {

    public void registerKeyBindingHandler() {

    }

    public void registerRenderTickHandler() {

    }

    public void registerDrawBlockHighlightHandler() {

    }

    public void setKeyBinding(String name, int value) {

    }

    public void registerSoundHandler() {

    }

    public void initRenderingAndTextures() {

    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileAnvil.class, "tileMMAnvil");
        GameRegistry.registerTileEntity(TileHearth.class, "tileHearth");
        GameRegistry.registerTileEntity(TileChimney.class, "tileChimney");
        GameRegistry.registerTileEntity(TileCubusArcana.class, "tileCubusArcana");
    }

    public void sendRequestEventPacket(byte eventType, int originX,
            int originY, int originZ, byte sideHit, byte rangeX, byte rangeY,
            byte rangeZ, String data) {

    }

    public void handleTileEntityPacket(int x, int y, int z,
            ForgeDirection orientation, byte state, String customName,
            HashMap<String, Integer> addIntegers) {

    }

    public void handleTileWithItemPacket(int x, int y, int z,
            ForgeDirection orientation, byte state, String customName,
            int itemID, int metaData) {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        if (ID == GuiIds.HEARTH) {
            TileHearth tileHearth = (TileHearth) world.getBlockTileEntity(x, y,
                    z);
            if (tileHearth.isMultiblockPart()) {
                return new ContainerForge(player.inventory, tileHearth);
            } else {
                return new ContainerHearth(player.inventory, tileHearth);
            }

        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        if (ID == GuiIds.HEARTH) {
            TileHearth tileHearth = (TileHearth) world.getBlockTileEntity(x, y,
                    z);
            if (tileHearth.isMultiblockPart()) {
                return new GuiForge(player.inventory, tileHearth);
            } else {
                return new GuiHearth(player.inventory, tileHearth);
            }
        }
        return null;
    }

}
