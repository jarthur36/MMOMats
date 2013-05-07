package uk.co.alteff4.mm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * 
 * MMO Materials
 * 
 * ModelHearth
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModelHearth extends ModelBase {
    // fields
    ModelRenderer Base;
    ModelRenderer Wall1;
    ModelRenderer Wall2;
    ModelRenderer Wall3;
    ModelRenderer Wall4;
    ModelRenderer Foot1;
    ModelRenderer Foot2;
    ModelRenderer Foot3;
    ModelRenderer Foot4;

    public ModelHearth() {
        textureWidth = 64;
        textureHeight = 32;

        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(-8F, 0F, -8F, 16, 2, 16);
        Base.setRotationPoint(0F, 21F, 0F);
        Base.setTextureSize(64, 32);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Wall1 = new ModelRenderer(this, 0, 0);
        Wall1.addBox(-8F, 0F, -8F, 16, 6, 2);
        Wall1.setRotationPoint(0F, 15F, 0F);
        Wall1.setTextureSize(64, 32);
        Wall1.mirror = true;
        setRotation(Wall1, 0F, 0F, 0F);
        Wall2 = new ModelRenderer(this, 0, 0);
        Wall2.addBox(-8F, 0F, 6F, 16, 6, 2);
        Wall2.setRotationPoint(0F, 15F, 0F);
        Wall2.setTextureSize(64, 32);
        Wall2.mirror = true;
        setRotation(Wall2, 0F, 0F, 0F);
        Wall3 = new ModelRenderer(this, 0, 0);
        Wall3.addBox(-8F, 0F, -6F, 2, 6, 12);
        Wall3.setRotationPoint(0F, 15F, 0F);
        Wall3.setTextureSize(64, 32);
        Wall3.mirror = true;
        setRotation(Wall3, 0F, 0F, 0F);
        Wall4 = new ModelRenderer(this, 0, 0);
        Wall4.addBox(6F, 0F, -6F, 2, 6, 12);
        Wall4.setRotationPoint(0F, 15F, 0F);
        Wall4.setTextureSize(64, 32);
        Wall4.mirror = true;
        setRotation(Wall4, 0F, 0F, 0F);
        Foot1 = new ModelRenderer(this, 0, 0);
        Foot1.addBox(0F, 0F, 0F, 2, 1, 2);
        Foot1.setRotationPoint(-8F, 23F, 6F);
        Foot1.setTextureSize(64, 32);
        Foot1.mirror = true;
        setRotation(Foot1, 0F, 0F, 0F);
        Foot2 = new ModelRenderer(this, 0, 0);
        Foot2.addBox(0F, 0F, 0F, 2, 1, 2);
        Foot2.setRotationPoint(-8F, 23F, -8F);
        Foot2.setTextureSize(64, 32);
        Foot2.mirror = true;
        setRotation(Foot2, 0F, 0F, 0F);
        Foot3 = new ModelRenderer(this, 0, 0);
        Foot3.addBox(0F, 0F, 0F, 2, 1, 2);
        Foot3.setRotationPoint(6F, 23F, -8F);
        Foot3.setTextureSize(64, 32);
        Foot3.mirror = true;
        setRotation(Foot3, 0F, 0F, 0F);
        Foot4 = new ModelRenderer(this, 0, 0);
        Foot4.addBox(0F, 0F, 0F, 2, 1, 2);
        Foot4.setRotationPoint(6F, 23F, 6F);
        Foot4.setTextureSize(64, 32);
        Foot4.mirror = true;
        setRotation(Foot4, 0F, 0F, 0F);
    }

    public void render() {
        Base.render(0.0625F);
        Wall1.render(0.0625F);
        Wall2.render(0.0625F);
        Wall3.render(0.0625F);
        Wall4.render(0.0625F);
        Foot1.render(0.0625F);
        Foot2.render(0.0625F);
        Foot3.render(0.0625F);
        Foot4.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
