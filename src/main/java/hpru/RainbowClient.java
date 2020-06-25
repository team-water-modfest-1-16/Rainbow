package hpru;

import hpru.client.render.entity.RainbowArrowEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

import static hpru.RainbowMod.ARROW_ENTITY_TYPE;

@Environment(EnvType.CLIENT)
public class RainbowClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ARROW_ENTITY_TYPE,
                (EntityRenderDispatcher manager, EntityRendererRegistry.Context context) -> new RainbowArrowEntityRenderer(manager));
    }
}
