package hpru.entity.projectile;

import hpru.RainbowMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class RainbowArrowEntity extends ArrowEntity {
    int r = 255;
    int g = 255;
    int b = 255;

    public RainbowArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public RainbowArrowEntity(World world){
        this(RainbowMod.ARROW_ENTITY_TYPE, world);
    }

    public RainbowArrowEntity(EntityType<? extends ArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if(world.isClient){
            world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getParticleX(.5), this.getRandomBodyY(), this.getParticleZ(.5), r,g,b);
        }
    }
}
