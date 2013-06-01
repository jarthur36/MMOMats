package uk.co.alteff4.mm.core.handlers;

import uk.co.alteff4.mm.core.util.LocalizationHelper;
import uk.co.alteff4.mm.lib.Localizations;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * MMO Materials
 * 
 * LocalizationHandler
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LocalizationHandler {
    public static void loadLanguages() {

        // For every file specified in the Localization library class, load them
        // into the Language Registry
        for (String localizationFile : Localizations.localeFiles) {
            LanguageRegistry.instance().loadLocalization(localizationFile,
                    LocalizationHelper.getLocaleFromFileName(localizationFile),
                    LocalizationHelper.isXMLLanguageFile(localizationFile));
        }
    }
}
