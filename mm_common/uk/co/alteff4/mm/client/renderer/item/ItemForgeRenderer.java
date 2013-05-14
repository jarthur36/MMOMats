package uk.co.alteff4.mm.client.renderer.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import uk.co.alteff4.mm.client.model.ModelAnvil;
import uk.co.alteff4.mm.client.model.ModelChimney;
import uk.co.alteff4.mm.client.model.ModelHearth;
import uk.co.alteff4.mm.lib.Textures;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemForgeRenderer implements IItemRenderer {
    @SuppressWarnings("unused")
    private ModelAnvil modelAnvil;
    private ModelHearth modelHearth;
    private ModelChimney modelChimney;

    public ItemForgeRenderer() {
        // modelAnvil = new ModelAnvil();
        modelHearth = new ModelHearth();
        modelChimney = new ModelChimney();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY: {
                if (item.getItemDamage() == 0)
                    renderAnvil(0f, 0f, 0f, 0.5f);
                else if (item.getItemDamage() == 1)
                    renderHearth(0f, 1f, 0f, 1f);
                else if (item.getItemDamage() == 3)
                    renderChimney(0f, 1F, 0F, 1F);
                return;
            }

            case EQUIPPED: {
                if (item.getItemDamage() == 0)
                    renderAnvil(0f, 1f, 1f, 0.5f);
                else if (item.getItemDamage() == 1)
                    renderHearth(0f, 2F, 0F, 1F);
                else if (item.getItemDamage() == 3)
                    renderChimney(0f, 2F, 0F, 1F);
                return;
            }

            case INVENTORY: {
                if (item.getItemDamage() == 0) {
                    renderAnvil(0f, 0f, 0f, 0.5f);
                    return;
                } else if (item.getItemDamage() == 1)
                    renderHearth(0f, 1.25f, 0f, 1F);
                else if (item.getItemDamage() == 3)
                    renderChimney(0f, 1.0F, 0F, 1F);
                return;
            }

            default:
                return;
        }
    }

    private void renderChimney(float x, float y, float z, float scale) {
        GL11.glPushMatrix();

        // Disable Lighting Calculations
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glScalef(scale, scale, scale);
        FMLClientHandler.instance().getClient().renderEngine
                .bindTexture(Textures.MODEL_CHIMNEY);
        GL11.glRotatef(180, 1, 0, 0);

        modelChimney.render();

        GL11.glPopMatrix();
    }

    private void renderHearth(float x, float y, float z, float scale) {
        GL11.glPushMatrix();

        // Disable Lighting Calculations
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glScalef(scale, scale, scale);
        FMLClientHandler.instance().getClient().renderEngine
                .bindTexture(Textures.MODEL_ANVIL);
        GL11.glRotatef(180, 1, 0, 0);

        modelHearth.render();

        GL11.glPopMatrix();
    }

    private void renderAnvil(float x, float y, float z, float scale) {
        GL11.glPushMatrix();

        // Disable Lighting Calculations
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float) x, (float) y - 0.75F, (float) z - 1);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180f, 0f, 1f, 0f);

        FMLClientHandler.instance().getClient().renderEngine
                .bindTexture(Textures.MODEL_ANVIL);

        // modelAnvil.render();

        // Re-enable Lighting Calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}