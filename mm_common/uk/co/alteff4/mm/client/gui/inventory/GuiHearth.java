package uk.co.alteff4.mm.client.gui.inventory;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
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

    private boolean showHeatTip;
    private TileHearth tileHearth;

    public GuiHearth(InventoryPlayer inventoryPlayer, TileHearth tileHearth) {
        super(new ContainerHearth(inventoryPlayer, tileHearth));
        this.tileHearth = tileHearth;
        this.xSize = 176;
        this.ySize = 166;
    }
    
    @Override
    public void handleMouseInput() {
        super.handleMouseInput();

        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height
                / this.mc.displayHeight - 1;
        int xStart = width / 2 - xSize / 2;
        int yStart = height / 2 - ySize / 2;

        int xPos = xStart + 41;
        int yPos = yStart + 16;
        int objWidth = 16;
        int objHeight = 50;

        if (x >= xPos && x <= (xPos + objWidth) && y >= yPos
                && y <= (yPos + objHeight)) {
            showHeatTip = true;
        } else {
            showHeatTip = false;
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void drawScreen(int x, int y, float par3) {
        super.drawScreen(x, y, par3);
        if (showHeatTip) {
            ArrayList text = new ArrayList();
            text.add(StatCollector.translateToLocal(Strings.TERM_HEAT)
                    + ":");
            text.add(tileHearth.getHeat() + "/1000");
            this.drawHoveringText(text, x, y, fontRenderer);
        }
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
                StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 24,
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
        
        if (tileHearth.getState() > 0) {
            int burnTime = tileHearth.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(xStart + 80,
                    yStart + 51 + 12 - burnTime, 176, 12 - burnTime, 14,
                    burnTime + 2);
        }
        
        int heat = (int) Math.floor(tileHearth.getHeat() / 10 / 2);
        this.drawTexturedModalRect(xStart + 41, yStart + 16 + 50 - heat,
                176, 64 - heat, 16, heat);
    }

}
