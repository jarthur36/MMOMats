package uk.co.alteff4.models;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import uk.co.alteff4.mm.tileentity.TileAnvil;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;


public class ModelAnvil extends ModelBase {

    private IModelCustom modelAnvil;
    
    public ModelAnvil()
    {
        modelAnvil = AdvancedModelLoader.loadModel("/mods/mm/models/modelAnvil.obj");
    }
    
    public void render()
    {
        modelAnvil.renderAll();
    }
    
    public void render(TileAnvil anvil, double x, double y, double z)
    {
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();
     
        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float)x, (float)y, (float)z+1);
     
        // Scale our object to about half-size in all directions (the OBJ file is a little large)
        GL11.glScalef(0.5f, 0.5f, 0.5f);
     
        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/mm/textures/blocks/anvil.png");
     
        // Render the object, using modelTutBox.renderAll();
        this.render();
     
        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
}
