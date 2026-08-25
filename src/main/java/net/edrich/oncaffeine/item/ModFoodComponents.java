package net.edrich.oncaffeine.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent COFFEE_MEDIUM = new FoodComponent.Builder().hunger(1).saturationModifier(4.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3600, 2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 3600, 2), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent GREEN_MEDIUM = new FoodComponent.Builder().hunger(1).saturationModifier(2.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3600, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 3600, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 240, 1), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BLACK_MEDIUM = new FoodComponent.Builder().hunger(1).saturationModifier(2.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3600, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 3600, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 240, 0), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent HERBAL_MEDIUM = new FoodComponent.Builder().hunger(1).saturationModifier(2.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0f)
            .alwaysEdible().build();


}
