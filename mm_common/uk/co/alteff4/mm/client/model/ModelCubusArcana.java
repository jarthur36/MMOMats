package uk.co.alteff4.mm.client.model;

import uk.co.alteff4.mm.lib.Models;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 *
 * MMO Materials
 *
 * ModelCubusArcana
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ModelCubusArcana {
    private IModelCustom model;
    
    public ModelCubusArcana() {
        model = AdvancedModelLoader.loadModel(Models.CUBUS_ARCANA);
    }
    
    public void renderAll() {
        model.renderAll();
    }
    
    public void renderPart(String partName) {
        model.renderPart(partName);
    }
}
