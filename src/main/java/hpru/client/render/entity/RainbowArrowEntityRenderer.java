package hpru.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

@Environment(EnvType.CLIENT)
public class RainbowArrowEntityRenderer extends ArrowEntityRenderer {

    public RainbowArrowEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }
}
