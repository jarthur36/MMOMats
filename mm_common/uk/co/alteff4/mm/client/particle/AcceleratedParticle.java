package uk.co.alteff4.mm.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class AcceleratedParticle extends EntityFX {
    private double accX, accY, accZ;

    public AcceleratedParticle(World world, double x, double y, double z,
            double ax, double ay, double az) {
        super(world, x, y, z);
        accX = ax;
        accY = ay;
        accZ = az;
        this.particleMaxAge = 26;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = posX;
        this.prevPosY = posY;
        this.prevPosZ = posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }

        this.posX += (accX / 2) * ((double) (particleAge * particleAge) / 400D)
                + motionX * particleAge;
        this.posY += (accY / 2) * ((double) (particleAge * particleAge) / 400D)
                + motionY * particleAge;
        this.posZ += (accZ / 2) * ((double) (particleAge * particleAge) / 400D)
                + motionZ * particleAge;
    }
}
