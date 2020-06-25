package hpru.item;

import hpru.entity.projectile.RainbowArrowEntity;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)user;
            boolean shouldNotConsumeArrow = playerEntity.abilities.creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack arrowStack = playerEntity.getArrowType(stack);
            if (!arrowStack.isEmpty() || shouldNotConsumeArrow) {
                if (arrowStack.isEmpty()) {
                    arrowStack = new ItemStack(Items.ARROW);
                }

                float pullProgress = getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks);
                if (pullProgress >= 0.1) {
                    boolean isNormalInfinityOrCreativeArrow = shouldNotConsumeArrow && arrowStack.getItem() == Items.ARROW;
                    if (!world.isClient) {
                        for(int i = 0; i < 6; i++){
                            double arrowLineLength = 2;
                            double offset = (arrowLineLength / 6d * i) - (arrowLineLength/2); // an even spread of 6 arrows across a 2 block length centered on the origin
                            PersistentProjectileEntity persistentProjectileEntity = new RainbowArrowEntity(world, playerEntity);
                            persistentProjectileEntity.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, pullProgress * 3.0F, 1.0F);
                            Vec3d pos = persistentProjectileEntity.getPos();
                            persistentProjectileEntity.teleport(pos.x, pos.y + offset, pos.z);
                            if (pullProgress == 1.0F) {
                                persistentProjectileEntity.setCritical(true);
                            }

                            int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                            if (powerLevel > 0) {
                                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)powerLevel * 0.5D + 0.5D);
                            }

                            int punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                            if (punchLevel > 0) {
                                persistentProjectileEntity.setPunch(punchLevel);
                            }

                            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                                persistentProjectileEntity.setOnFireFor(100);
                            }

                            stack.damage(1, playerEntity, (
                                    (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand())
                            ));
                            if (isNormalInfinityOrCreativeArrow || playerEntity.abilities.creativeMode && (arrowStack.getItem() == Items.SPECTRAL_ARROW || arrowStack.getItem() == Items.TIPPED_ARROW)) {
                                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                            }

                            world.spawnEntity(persistentProjectileEntity);
                        }

                    }

                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.4F + 1.2F) + pullProgress * 0.5F);
                    if (!isNormalInfinityOrCreativeArrow && !playerEntity.abilities.creativeMode) {
                        arrowStack.decrement(1);
                        if (arrowStack.isEmpty()) {
                            playerEntity.inventory.removeOne(arrowStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }
}
