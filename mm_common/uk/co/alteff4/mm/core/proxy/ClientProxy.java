package uk.co.alteff4.mm.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import uk.co.alteff4.mm.client.renderer.item.ItemAnvilRenderer;
import uk.co.alteff4.mm.client.renderer.tileentity.TileAnvilRenderer;
import uk.co.alteff4.mm.lib.BlockIds;
import uk.co.alteff4.mm.tileentity.TileAnvil;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TileAnvil.class, new TileAnvilRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockIds.ANVIL, new ItemAnvilRenderer());
       
    }

    @Override
    public void registerTileEntities() {    
                
    }

    @Override
    public void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {

      
    }

    @Override
    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName) {

     
    }
    
    @Override
    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData) {
        
    }

   

}
