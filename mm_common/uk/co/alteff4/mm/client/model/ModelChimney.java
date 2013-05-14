package uk.co.alteff4.mm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelChimney extends ModelBase {
    // fields
    ModelRenderer Wall;
    ModelRenderer Wall1;
    ModelRenderer Wall2;
    ModelRenderer Wall3;

    public ModelChimney() {
        textureWidth = 64;
        textureHeight = 64;

        Wall = new ModelRenderer(this, 0, 0);
        Wall.addBox(0F, 0F, 0F, 2, 16, 16);
        Wall.setRotationPoint(-8F, 8F, -8F);
        Wall.setTextureSize(64, 64);
        Wall.mirror = true;
        setRotation(Wall, 0F, 0F, 0F);
        Wall1 = new ModelRenderer(this, 0, 0);
        Wall1.addBox(0F, 0F, 0F, 2, 16, 16);
        Wall1.setRotationPoint(6F, 8F, -8F);
        Wall1.setTextureSize(64, 64);
        Wall1.mirror = true;
        setRotation(Wall1, 0F, 0F, 0F);
        Wall2 = new ModelRenderer(this, 36, 0);
        Wall2.addBox(0F, 0F, 0F, 12, 16, 2);
        Wall2.setRotationPoint(-6F, 8F, -8F);
        Wall2.setTextureSize(64, 64);
        Wall2.mirror = true;
        setRotation(Wall2, 0F, 0F, 0F);
        Wall3 = new ModelRenderer(this, 36, 0);
        Wall3.addBox(0F, 0F, 0F, 12, 16, 2);
        Wall3.setRotationPoint(-6F, 8F, 6F);
        Wall3.setTextureSize(64, 64);
        Wall3.mirror = true;
        setRotation(Wall3, 0F, 0F, 0F);
    }

    public void render() {
        Wall.render(0.0625F);
        Wall1.render(0.0625F);
        Wall2.render(0.0625F);
        Wall3.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
