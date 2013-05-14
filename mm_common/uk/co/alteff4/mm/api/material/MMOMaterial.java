package uk.co.alteff4.mm.api.material;

/**
 * 
 * MMO Materials
 * 
 * MMOMaterial
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class MMOMaterial {
    private String identifier;
    private String textureFile;

    /**
     * 
     * Creates a new MMOMaterial object.
     * 
     * @param identifier
     *            used to identify the material inside crafting recipes etc.
     * @param textureFile
     *            the prefix of the file used for the texture of the material
     */
    public MMOMaterial(String identifier, String textureFile) {
        this.identifier = identifier;
        this.textureFile = textureFile;
    }

    /**
     * 
     * Returns the identifier of the material. It's used for the localization
     * registering.
     * 
     * @return the internal ID of the material
     */
    public String getID() {
        return identifier;
    }

    /**
     * 
     * Returns the file used for texturing the material. The icon is registered
     * using textureFile + "Tier[1-5]" + ".png".
     * 
     * @return the prefix of the file used for the texture of the material
     */
    public String getTextureFile() {
        return textureFile;
    }
}
