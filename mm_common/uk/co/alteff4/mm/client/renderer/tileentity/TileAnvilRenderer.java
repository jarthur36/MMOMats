package uk.co.alteff4.mm.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
//import uk.co.alteff4.mm.client.model.ModelAnvil;
import uk.co.alteff4.mm.lib.Textures;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileAnvilRenderer extends TileEntitySpecialRenderer {

    //private ModelAnvil modelAnvil;
    
    public TileAnvilRenderer() {
        //modelAnvil = new ModelAnvil();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
            double z, float tick) {
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the
        // OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x, (float) y, (float) z + 1);

        // Scale our object to about half-size in all directions (the OBJ file
        // is a little large)
        GL11.glScalef(0.5f, 0.5f, 0.5f);

        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine
                .bindTexture(Textures.MODEL_ANVIL);

        // Render the object, using modelTutBox.renderAll();
        //modelAnvil.render();

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
}
