package uk.co.alteff4.mm.api.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

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
    private static Logger mmoMatsLogger = Logger.getLogger("MMOMats");

    public static void init() {
        mmoMatsLogger.setParent(FMLLog.getLogger());
    }
    
    public static void log(Level level, String message) {
        mmoMatsLogger.log(level, message);
    }
}
