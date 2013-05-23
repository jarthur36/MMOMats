package uk.co.alteff4.mm.tileentity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import uk.co.alteff4.mm.client.particle.AcceleratedParticle;
import uk.co.alteff4.mm.lib.Particles;

/**
 * 
 * MMO Materials
 * 
 * TileArcaneForge
 * 
 * @author AltEff4
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileArcaneForge extends TileMM {

    int count;

    public TileArcaneForge() {
        count = 0;
    }

    @Override
    public void updateEntity() {

        Random r = new Random();
        int i = this.xCoord;
        int j = this.yCoord;
        int k = this.zCoord;
        World world = this.worldObj;
        count = count + 1;
        if (count % 1 == 0) {

            for (int num = 0; num < Particles.INTENSITY; num++) {

                double theta = r.nextDouble() * Math.PI * 2;

                double x = Math.cos(theta) * 1.5D;
                double z = Math.sin(theta) * 1.5D;

                AcceleratedParticle p = new AcceleratedParticle(world, i + 0.5
                        + x, j, k + 0.5 + z, -x / 7.5D, 1.5 / 10D, -z / 7.5D);
                p.setRBGColorF((0.5F + (r.nextFloat() / 2)), 0.0F, 1.0F);
                p.setParticleTextureIndex(3);
                Minecraft.getMinecraft().effectRenderer.addEffect(p);
                AcceleratedParticle l = new AcceleratedParticle(world, i + 0.5
                        + x, j + 3, k + 0.5 + z, -x / 7.5D, -(1.75 / 10D),
                        -z / 7.5D);
                l.setRBGColorF((0.5F + (r.nextFloat() / 2)), 0.0F, 1.0F);
                l.setParticleTextureIndex(3);
                Minecraft.getMinecraft().effectRenderer.addEffect(l);

            }

        }

    }

}
