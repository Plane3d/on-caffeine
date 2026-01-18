package net.edrich.oncaffeine.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent COFFEE_MEDIUM = new FoodComponent.Builder().hunger(1).saturationModifier(2.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 1), 1.0f)
            .build();

}
