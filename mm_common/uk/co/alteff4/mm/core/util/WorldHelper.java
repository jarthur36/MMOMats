package uk.co.alteff4.mm.core.util;

import uk.co.alteff4.mm.lib.BlockIds;
import net.minecraft.world.World;

/**
 * 
 * MMO Materials
 * 
 * WorldHelper
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WorldHelper {
    public static boolean isValidForgePart(World world, int x, int y, int z) {
        return world.getBlockId(x, y, z) == BlockIds.STANDARD
                && world.getBlockMetadata(x, y, z) == 0;
    }

    public static boolean isAirBlock(World world, int x, int y, int z) {
        return world.getBlockId(x, y, z) == 0;
    }
}
