package hpru;

import hpru.item.RainbowItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RainbowMod implements ModInitializer {

    public static final RainbowItem RAINBOW_ITEM = new RainbowItem(
            new Item.Settings()
                    .group(ItemGroup.COMBAT)
                    .maxDamage(500)
    );

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("rainbow", "rainbow"), RAINBOW_ITEM);
    }
}
