package hpru.entity.projectile;

import hpru.RainbowMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;

public class RainbowArrowEntity extends ArrowEntity {

    public RainbowArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public RainbowArrowEntity(World world){
        this(RainbowMod.ARROW_ENTITY_TYPE, world);
    }

    public RainbowArrowEntity(EntityType<? extends ArrowEntity> entityType, World world) {
        super(entityType, world);
    }
}
