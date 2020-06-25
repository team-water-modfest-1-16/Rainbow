package hpru;

import hpru.entity.projectile.RainbowArrowEntity;
import hpru.item.RainbowItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RainbowMod implements ModInitializer{

    public static final RainbowItem RAINBOW_ITEM = new RainbowItem(
            new Item.Settings()
                    .group(ItemGroup.COMBAT)
                    .maxDamage(500)
    );

    public static final EntityType<RainbowArrowEntity> ARROW_ENTITY_TYPE =
            Registry.register(
                    Registry.ENTITY_TYPE,
                    new Identifier("rainbow", "rainbow_arrow"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<RainbowArrowEntity>) RainbowArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(.06f, .06f))
                            .spawnableFarFromPlayer()
                            .trackable(128, 1)
                            .build()
            );

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("rainbow", "rainbow"), RAINBOW_ITEM);
    }
}
