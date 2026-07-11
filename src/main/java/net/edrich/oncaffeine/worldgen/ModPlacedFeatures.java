package net.edrich.oncaffeine.worldgen;

import net.edrich.oncaffeine.OnCaffeine;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> WILD_COFFEE_PLACED_KEY = registerKey("wild_coffee_placed");
    public static final RegistryKey<PlacedFeature> WILD_TEA_PLACED_KEY = registerKey("wild_tea_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> coffeeRegistryEntry = configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WILD_COFFEE_KEY);
        RegistryEntry<ConfiguredFeature<?, ?>> teaRegistryEntry = configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WILD_TEA_KEY);

        register(context, WILD_COFFEE_PLACED_KEY, coffeeRegistryEntry, ModPlacedFeatures.modifiers((int)(Math.random() * 3) + 5));
        register(context, WILD_TEA_PLACED_KEY, teaRegistryEntry, ModPlacedFeatures.modifiers((int)(Math.random() * 3) + 5));

    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(OnCaffeine.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static List<PlacementModifier> modifiers(int count) {
        return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }
}
