package uk.co.alteff4.mm.core.proxy;

import java.util.HashMap;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import uk.co.alteff4.mm.client.renderer.item.ItemForgeRenderer;
import uk.co.alteff4.mm.client.renderer.tileentity.TileEntityForgeRenderer;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.tileentity.TileAnvil;
import uk.co.alteff4.mm.tileentity.TileChimney;
import uk.co.alteff4.mm.tileentity.TileHearth;
import uk.co.alteff4.mm.tileentity.TileMM;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;

/**
 * MMO Materials
 * 
 * ClientProxy
 * 
 * @author alteff4
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeyBindingHandler() {

    }

    @Override
    public void registerRenderTickHandler() {

    }

    @Override
    public void registerDrawBlockHighlightHandler() {

    }

    @Override
    public void setKeyBinding(String name, int value) {

    }

    @Override
    public void registerSoundHandler() {

    }

    @Override
    public void initRenderingAndTextures() {
        TileEntityForgeRenderer forgeRenderer = new TileEntityForgeRenderer();

        ClientRegistry.bindTileEntitySpecialRenderer(TileChimney.class,
                forgeRenderer);
        ClientRegistry.bindTileEntitySpecialRenderer(TileAnvil.class,
                forgeRenderer);
        ClientRegistry.bindTileEntitySpecialRenderer(TileHearth.class,
                forgeRenderer);
        MinecraftForgeClient.registerItemRenderer(BlockIds.FORGE,
                new ItemForgeRenderer());
    }

    @Override
    public void sendRequestEventPacket(byte eventType, int originX,
            int originY, int originZ, byte sideHit, byte rangeX, byte rangeY,
            byte rangeZ, String data) {

    }

    @Override
    public void handleTileEntityPacket(int x, int y, int z,
            ForgeDirection orientation, byte state, String customName,
            HashMap<String, Integer> addIntegers) {

        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld
                .getBlockTileEntity(x, y, z);

        if (tileEntity != null) {
            if (tileEntity instanceof TileMM) {
                ((TileMM) tileEntity).setOrientation(orientation);
                ((TileMM) tileEntity).setState(state);
                ((TileMM) tileEntity).setCustomName(customName);
                ((TileMM) tileEntity).setAddIntegers(addIntegers);
            }
        }
    }

    @Override
    public void handleTileWithItemPacket(int x, int y, int z,
            ForgeDirection orientation, byte state, String customName,
            int itemID, int metaData) {

    }

}
