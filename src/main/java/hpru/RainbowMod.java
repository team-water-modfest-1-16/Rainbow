package hpru;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RainbowMod implements ModInitializer {

    public static final Item RAINBOW_ITEM = new Item(
            new Item.Settings()
                    .group(ItemGroup.COMBAT)
                    .maxDamage(500)
                    .maxCount(1)
    );

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("tutorial", "fabric_item"), RAINBOW_ITEM);
    }
}
