package uk.co.alteff4.mm.client.model;

import uk.co.alteff4.mm.lib.Models;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelAnvil {

    private IModelCustom modelAnvil;

    public ModelAnvil() {
        modelAnvil = AdvancedModelLoader.loadModel(Models.ANVIL);
    }

    public void render() {
        modelAnvil.renderAll();
    }
}
