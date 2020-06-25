package hpru.client.render.entity;

import hpru.entity.projectile.RainbowArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RainbowArrowEntityRenderer extends ProjectileEntityRenderer<RainbowArrowEntity> {
    public static final Identifier TEXTURE = new Identifier("textures/entity/projectiles/arrow.png");

    public RainbowArrowEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Override
    public Identifier getTexture(RainbowArrowEntity entity) {
        return TEXTURE;
    }

}
