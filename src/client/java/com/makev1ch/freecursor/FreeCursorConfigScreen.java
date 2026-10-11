package com.makev1ch.freecursor;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.SimplePositioningWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class FreeCursorConfigScreen extends Screen {
    private final Screen parent;
    private FreeCursorConfig config;
    private ButtonWidget simulateF1Button;
    private ButtonWidget disableBlurButton;
    private ButtonWidget hideGlowButton; // MacroTech patch (mt3, 2026-10-11)

    public FreeCursorConfigScreen(Screen parent) {
        super(Text.translatable("config.freecursor.title"));
        this.parent = parent;
        this.config = FreeCursorConfig.getInstance();
    }

    @Override
    protected void init() {
        super.init();
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().marginX(5).marginBottom(4).alignHorizontalCenter();
        GridWidget.Adder adder = gridWidget.createAdder(1);

        Text simulateF1Text = Text.translatable("config.freecursor.simulate_f1");
        Text simulateF1Status = config.isSimulateF1() ? Text.translatable("options.on") : Text.translatable("options.off");
        simulateF1Button = ButtonWidget.builder(
            Text.literal(simulateF1Text.getString() + ": " + simulateF1Status.getString()),
            button -> { config.setSimulateF1(!config.isSimulateF1()); updateButtonTexts(); }
        ).width(200).build();
        adder.add(simulateF1Button);

        Text disableBlurText = Text.translatable("config.freecursor.disable_blur");
        Text disableBlurStatus = config.isDisableBlur() ? Text.translatable("options.on") : Text.translatable("options.off");
        disableBlurButton = ButtonWidget.builder(
            Text.literal(disableBlurText.getString() + ": " + disableBlurStatus.getString()),
            button -> { config.setDisableBlur(!config.isDisableBlur()); updateButtonTexts(); }
        ).width(200).build();
        adder.add(disableBlurButton);

        // MacroTech patch (mt3, 2026-10-11): "Hide Glow" toggle
        Text hideGlowText = Text.translatable("config.freecursor.hide_glow");
        Text hideGlowStatus = config.isHideGlow() ? Text.translatable("options.on") : Text.translatable("options.off");
        hideGlowButton = ButtonWidget.builder(
            Text.literal(hideGlowText.getString() + ": " + hideGlowStatus.getString()),
            button -> { config.setHideGlow(!config.isHideGlow()); updateButtonTexts(); }
        ).width(200).build();
        adder.add(hideGlowButton);

        adder.add(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).width(200).build());
        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, 0, this.height / 6 + 10, this.width, this.height, 0.5f, 0.0f);
        gridWidget.forEachChild(this::addDrawableChild);
    }

    private void updateButtonTexts() {
        Text simulateF1Text = Text.translatable("config.freecursor.simulate_f1");
        Text simulateF1Status = config.isSimulateF1() ? Text.translatable("options.on") : Text.translatable("options.off");
        simulateF1Button.setMessage(Text.literal(simulateF1Text.getString() + ": " + simulateF1Status.getString()));

        Text disableBlurText = Text.translatable("config.freecursor.disable_blur");
        Text disableBlurStatus = config.isDisableBlur() ? Text.translatable("options.on") : Text.translatable("options.off");
        disableBlurButton.setMessage(Text.literal(disableBlurText.getString() + ": " + disableBlurStatus.getString()));

        Text hideGlowText = Text.translatable("config.freecursor.hide_glow");
        Text hideGlowStatus = config.isHideGlow() ? Text.translatable("options.on") : Text.translatable("options.off");
        hideGlowButton.setMessage(Text.literal(hideGlowText.getString() + ": " + hideGlowStatus.getString()));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        Text title = Text.translatable("config.freecursor.title");
        context.drawCenteredTextWithShadow(this.textRenderer, title, this.width / 2, this.height / 6 - 10, 0xFFFFFF);
        int tooltipY = this.height - 40;
        if (simulateF1Button.isHovered()) {
            Text tooltip = Text.translatable("config.freecursor.simulate_f1.tooltip");
            context.drawCenteredTextWithShadow(this.textRenderer, tooltip, this.width / 2, tooltipY, 0xFFFFFF);
        }
        if (disableBlurButton.isHovered()) {
            Text tooltip = Text.translatable("config.freecursor.disable_blur.tooltip");
            context.drawCenteredTextWithShadow(this.textRenderer, tooltip, this.width / 2, tooltipY, 0xFFFFFF);
        }
        if (hideGlowButton.isHovered()) {
            Text tooltip = Text.translatable("config.freecursor.hide_glow.tooltip");
            context.drawCenteredTextWithShadow(this.textRenderer, tooltip, this.width / 2, tooltipY, 0xFFFFFF);
        }
    }

    @Override
    public void close() { if (this.client != null) this.client.setScreen(this.parent); }
    @Override
    public boolean shouldPause() { return false; }
}
