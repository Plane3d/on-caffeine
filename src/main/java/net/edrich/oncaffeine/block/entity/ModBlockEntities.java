package net.edrich.oncaffeine.block.entity;

import net.edrich.oncaffeine.OnCaffeine;
import net.edrich.oncaffeine.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<ClassicCoffeeMachineBlockEntity> CLASSIC_COFFEE_MACHINE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(OnCaffeine.MOD_ID, "classic_coffee_be"),
                    FabricBlockEntityTypeBuilder.create(ClassicCoffeeMachineBlockEntity::new,
                            ModBlocks.CLASSIC_COFFEE_MACHINE).build());

}
