package uk.co.alteff4.mm.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import uk.co.alteff4.mm.client.model.ModelCubusArcana;
import uk.co.alteff4.mm.lib.Textures;
import uk.co.alteff4.mm.tileentity.TileCubusArcana;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * MMO Materials
 * 
 * TileEntityCubusRenderer
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileEntityCubusRenderer extends TileEntitySpecialRenderer {
    private ModelCubusArcana modelCubusArcana;

    public TileEntityCubusRenderer() {
        modelCubusArcana = new ModelCubusArcana();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
            double z, float f) {
        if (tileEntity instanceof TileCubusArcana) {
            renderCubusArcana((TileCubusArcana) tileEntity, (int) x,
                    (int) y, (int) z);
        }
    }

    private void renderCubusArcana(TileCubusArcana te, int x, int y, int z) {
        GL11.glPushMatrix();

        GL11.glTranslatef(x, y, z);
        FMLClientHandler.instance().getClient().renderEngine
                .bindTexture(Textures.MODEL_ANVIL);

        modelCubusArcana.renderPart("CubusOuter2");

        GL11.glPopMatrix();
    }
}
