package uk.co.alteff4.mm.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import uk.co.alteff4.mm.inventory.ContainerHearth;
import uk.co.alteff4.mm.lib.Strings;
import uk.co.alteff4.mm.lib.Textures;
import uk.co.alteff4.mm.tileentity.TileHearth;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

/**
 * 
 * MMO Materials
 * 
 * GuiHearth
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class GuiHearth extends GuiContainer {

    private TileHearth tileHearth;

    public GuiHearth(InventoryPlayer inventoryPlayer, TileHearth tileHearth) {
        super(new ContainerHearth(inventoryPlayer, tileHearth));
        this.tileHearth = tileHearth;
        this.xSize = 176;
        this.ySize = 136;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        String containerName = tileHearth.isInvNameLocalized() ? tileHearth
                .getInvName() : StatCollector.translateToLocal(tileHearth
                .getInvName());
        fontRenderer.drawString(containerName,
                xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6,
                4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 8,
                ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2,
            int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Textures.GUI_HEARTH);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

}
