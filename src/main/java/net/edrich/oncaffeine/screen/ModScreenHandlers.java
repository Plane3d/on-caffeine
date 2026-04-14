package net.edrich.oncaffeine.screen;

import net.edrich.oncaffeine.OnCaffeine;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ClassicCoffeeScreenHandler> COFFEE_SCREEN_HANDLER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(OnCaffeine.MOD_ID, "classic_coffee"),
                    new ExtendedScreenHandlerType<>(ClassicCoffeeScreenHandler::new));


    public static void registerScreenHandler()
    {
        OnCaffeine.LOGGER.info("Registering Screen Handlers for " + OnCaffeine.MOD_ID);
    }


}
