package net.edrich.oncaffeine;

import net.edrich.oncaffeine.block.ModBlocks;
import net.edrich.oncaffeine.screen.ClassicCoffeeScreen;
import net.edrich.oncaffeine.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;




public class OnCaffeineClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MUG_EMPTY, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COFFEE_CROP, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.COFFEE_SCREEN_HANDLER_SCREEN_HANDLER, ClassicCoffeeScreen::new);
    }
}
