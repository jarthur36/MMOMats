package uk.co.alteff4.mm.core.helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import uk.co.alteff4.mm.lib.Reference;

/**
 * 
 * MMO Materials
 * 
 * LogHelper
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LogHelper {
    private static Logger mmoMatsLogger = Logger.getLogger(Reference.MOD_ID);

    public static void init() {
        mmoMatsLogger.setParent(FMLLog.getLogger());
    }
    
    public static void log(Level level, String message) {
        mmoMatsLogger.log(level, message);
    }
}
