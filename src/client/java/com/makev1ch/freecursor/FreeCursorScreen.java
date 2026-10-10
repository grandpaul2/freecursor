package com.makev1ch.freecursor;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class FreeCursorScreen extends Screen {
    private final boolean originalHideGui;
    private final int originalMenuBackgroundBlurriness;

    public FreeCursorScreen(boolean originalHideGui, int originalMenuBackgroundBlurriness) {
        super(Text.translatable("screen.freecursor.title"));
        this.originalHideGui = originalHideGui;
        this.originalMenuBackgroundBlurriness = originalMenuBackgroundBlurriness;
    }

    /**
     * MacroTech patch (mt2): chat still shows while the cursor is free.
     *
     * "Simulate F1" hides the whole HUD, chat included. With the HUD hidden this screen draws
     * the game's own chat display the way InGameHud does when nobody is typing, so new lines
     * appear and fade as in normal play and nothing else comes back. With the HUD showing, the
     * HUD draws chat itself.
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        if (this.client != null && this.client.options.hudHidden) {
            context.createNewRootLayer();
            this.client.inGameHud.getChatHud().render(context, this.client.textRenderer,
                this.client.inGameHud.getTicks(), mouseX, mouseY, false, false);
        }
    }

    /**
     * MacroTech patch: leave the world completely undimmed while the cursor is free.
     *
     * Screen#renderBackground paints the vanilla in-game darkening gradient across the whole
     * screen whenever a world is loaded. This screen has no widgets of its own, so there is
     * nothing that needs to stay legible against it -- the gradient is pure loss when the point
     * is to keep watching what is behind it. The mod's "Disable Blur" option does not cover
     * this: that only restores options.menuBackgroundBlurriness, a separate effect.
     */
    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // intentionally empty -- no darkening, no blur
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        if (click.button() == 0) {
            closeScreen();
            return true;
        }
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean keyPressed(KeyInput input) {
        if (input.key() == GLFW.GLFW_KEY_ESCAPE) {
            closeScreen();
            return true;
        }
        return super.keyPressed(input);
    }

    private void closeScreen() {
        if (this.client != null) {
            FreeCursorConfig config = FreeCursorConfig.getInstance();
            if (config.isSimulateF1()) this.client.options.hudHidden = originalHideGui;
            if (config.isDisableBlur()) this.client.options.getMenuBackgroundBlurriness().setValue(originalMenuBackgroundBlurriness);
            this.client.setScreen(null);
        }
    }
}
