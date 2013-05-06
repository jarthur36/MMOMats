package uk.co.alteff4.mm.client.renderer.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import uk.co.alteff4.models.ModelAnvil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemAnvilRenderer implements IItemRenderer
{
    private ModelAnvil modelAnvil;
     
    public ItemAnvilRenderer()
    {
       modelAnvil = new ModelAnvil();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }
     
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }
     
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case ENTITY:{
                renderAnvil(0f, 0f, 0f, 0.5f);
                return;
            }
             
            case EQUIPPED:{
                renderAnvil(0f, 1f, 1f, 0.5f);
                return;
            }
                 
            case INVENTORY:{
                renderAnvil(0f, 0f, 0f, 0.5f);
                return;
            }
             
            default:return;
        }
    }
    private void renderAnvil(float x, float y, float z, float scale)
    {
        GL11.glPushMatrix();
     
        // Disable Lighting Calculations
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float)x, (float)y-0.75F, (float)z-1);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180f, 0f, 1f, 0f);
         
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/mm/textures/blocks/anvil.png");
         
        modelAnvil.render();
         
        // Re-enable Lighting Calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}