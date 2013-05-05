package uk.co.alteff4.mm.client.renderer.tileentity;

import uk.co.alteff4.mm.tileentity.TileAnvil;
import uk.co.alteff4.models.ModelAnvil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;


public class TileAnvilRenderer extends TileEntitySpecialRenderer {

    private ModelAnvil modelAnvil = new ModelAnvil();
    
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        modelAnvil.render((TileAnvil)tileEntity, x, y, z);
    }

}
