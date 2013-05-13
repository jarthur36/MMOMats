package uk.co.alteff4.mm.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import uk.co.alteff4.mm.client.model.ModelHearth;
//import uk.co.alteff4.mm.client.model.ModelAnvil;
import uk.co.alteff4.mm.lib.Textures;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import uk.co.alteff4.mm.tileentity.TileAnvil;
import uk.co.alteff4.mm.tileentity.TileHearth;

public class TileEntityForgeRenderer extends TileEntitySpecialRenderer {

    // private ModelAnvil modelAnvil;
    private ModelHearth modelHearth;

    public TileEntityForgeRenderer() {
        // modelAnvil = new ModelAnvil();
        modelHearth = new ModelHearth();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
            double z, float tick) {
        if (tileEntity instanceof TileAnvil) {
            renderAnvil(tileEntity, x, y, z);
        } else if (tileEntity instanceof TileHearth) {
            renderHearth((TileHearth) tileEntity, x, y, z);
        }
    }

    private void renderHearth(TileHearth te, double x, double y, double z) {
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because
        // the
        // OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180, 1, 0, 0);

        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine
                .bindTexture(Textures.MODEL_ANVIL);

        if (te.getState() == 1) {
            modelHearth.render(te.getCoalAmount());
        } else {
            modelHearth.render();
        }

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }

    private void renderAnvil(TileEntity te, double x, double y, double z) {
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because
        // the
        // OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x, (float) y, (float) z + 1);

        // Scale our object to about half-size in all directions (the OBJ
        // file
        // is a little large)
        GL11.glScalef(0.5f, 0.5f, 0.5f);

        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine
                .bindTexture(Textures.MODEL_ANVIL);

        // Render the object, using modelTutBox.renderAll();
        // modelAnvil.render();

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
}
