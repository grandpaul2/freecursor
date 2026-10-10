# FreeCursor

> **This is MacroTech's fork** of [Makev1ch/freecursor](https://github.com/Makev1ch/freecursor),
> on the branch `1.21.11-mt`. It is the same mod, GPL-2.0 like the original, with these changes:
>
> - **mt1 (2026-08-29):** the world isn't darkened while the cursor is free
>   (`FreeCursorScreen.renderBackground`).
> - **mt2 (2026-10-10):** chat still shows while the cursor is free with the HUD hidden
>   (`FreeCursorScreen.render`); built for Minecraft 1.21.11 (Loom 1.15, Gradle 9.2.1).
>
> Jars are named `freecursor-1.2.10-mt<n>+mc1.21.11.jar`. Build with JDK 21: `./gradlew build`.
> Everything below is the original README.
**FreeCursor** is a Minecraft mod that allows you to temporarily free your mouse cursor during gameplay by pressing a key.

## Features

   - **Customizable Key**: F6 by default, but can be changed in game settings
   - **Simple Cursor Release**: Press the key to free your cursor
   - **Easy Return**: Click LMB or press ESC to return to the game
   - **Localization**: Support for Russian and English languages
   - **Client-side Only**: No server installation required

## Usage

1. Press **F6** (or your configured key) → cursor unlocks.

3. To return to the game:
   - Click **Left Mouse Button** anywhere
   - Or press **ESC**

![demonstration of the mod's work](https://cdn.modrinth.com/data/vcD9vl16/images/bfb76ecf129468e38dd135ff766d98d0507d2706.gif)

## Configuration

### Key Rebinds
>Settings > Controls > FreeCursor category

### Mod Settings in two ways:
   - Press N (default) in-game for built-in config, or
   - Use ModMenu (if installed) to configure.

### Available Settings:
1.   **Simulate F1** - Hide HUD elements when cursor is freed (just like pressing F1)
2.   **Disable Blur** - Remove background blur when cursor is freed
