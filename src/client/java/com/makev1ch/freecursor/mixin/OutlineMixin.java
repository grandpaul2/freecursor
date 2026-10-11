package com.makev1ch.freecursor.mixin;

import com.makev1ch.freecursor.FreeCursorConfig;
import com.makev1ch.freecursor.FreeCursorScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * MacroTech patch (mt3, 2026-10-11): no glow outlines while the cursor is free ("Hide Glow").
 *
 * The cursor is freed to keep watching the world while the mouse is elsewhere, and glowing
 * outlines (the glowing effect, the spectator highlight key, glow added by other mods) draw
 * through walls and distract from it. MinecraftClient#hasOutline is the one place the client
 * decides whether an entity gets an outline, so answering "no" there while FreeCursorScreen is
 * open hides them all, and they are back the moment the screen closes.
 */
@Mixin(MinecraftClient.class)
public class OutlineMixin {
    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    private void freecursor_hideGlow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.currentScreen instanceof FreeCursorScreen && FreeCursorConfig.getInstance().isHideGlow()) {
            cir.setReturnValue(false);
        }
    }
}
