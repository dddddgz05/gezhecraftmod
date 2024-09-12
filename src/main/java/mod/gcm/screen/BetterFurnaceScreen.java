package mod.gcm.screen;

import mod.gcm.screen.handler.BetterFurnaceHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BetterFurnaceScreen extends HandledScreen<BetterFurnaceHandler> {
    public static final Identifier TEXTURE = Identifier.of("gcm", "textures/gui/better_furnace.png");

    public BetterFurnaceScreen(BetterFurnaceHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundHeight = 167;
        playerInventoryTitleY = backgroundHeight - 94;
    }

    @Override
    public void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
