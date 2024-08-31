package mod.gcm;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.render.RenderLayer;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        ScreenRegistry.register(Main.BETTER_FURNACE_HANDLER, BetterFurnaceScreen::new);
        ClientLifecycleEvents.CLIENT_STARTED.register(
                client -> {
                    BlockRenderLayerMap.INSTANCE.putBlock(Main.PEPPER_BLOCK, RenderLayer.getCutout());
                }
        );
    }
}
