package hpru.item;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.BowItem;
import net.minecraft.util.Identifier;

public class RainbowItem extends BowItem {
    public RainbowItem(Settings settings) {
        super(settings);

        FabricModelPredicateProviderRegistry.register(new Identifier("pull"), (stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem().getItem() != this ? 0.0F
                    : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        FabricModelPredicateProviderRegistry.register(new Identifier("pulling"), (stack, world, entity) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
    }
}
