# FreeCursor (MacroTech fork)

This is MacroTech's fork of [FreeCursor](https://github.com/Makev1ch/freecursor) by Makev1ch.
MacroTech is a small technical vanilla Minecraft group. We run this fork because we wanted the
world to stay undimmed and chat to stay readable while the cursor is free. It is GPL-2.0, like
the original, and it is built for MacroTech's Minecraft version, 1.21.11.

FreeCursor is a client-side Fabric mod. Press a key (F6 by default) and the mouse cursor is
released while the game keeps running; click or press ESC to take control back.

## What the fork changes

Each patch is marked `MacroTech patch` in the code. mt1 and mt2 are in `FreeCursorScreen.java`,
mt3 in `mixin/OutlineMixin.java` and the settings.

- **mt1 (2026-08-29):** no darkening and no blur while the cursor is free. The upstream
  "Disable Blur" setting is therefore redundant on this screen.
- **mt2 (2026-10-10):** chat stays visible while the cursor is free with the HUD hidden
  ("Simulate F1"). Built for Minecraft 1.21.11 only: upstream's 1.2.10 accepted 1.21.9 to
  1.21.11, and this build refuses 1.21.9 and 1.21.10.
- **mt3 (2026-10-11):** a third setting, "Hide Glow" (off by default). When on, no entity has
  a glowing outline while the cursor is free: the glowing effect, the spectator highlight key
  and glow added by other mods. The outlines are back when you return to the game.

Version `1.2.10-mt3+mc1.21.11` is upstream 1.2.10 plus these patches. See `CHANGELOG.md`.

## Requirements

- Minecraft 1.21.11 (Java 21)
- Fabric Loader 0.16.0 or newer
- Fabric API
- Mod Menu, optional, for the settings screen (the `N` key opens it too)

## Where to get a jar

MacroTech members get the jar from the group's hub, `https://macrotechmc.com`. Anyone else
builds it. There are no releases. The source for the released jar is the tag
`v1.2.10-mt3+mc1.21.11`. All other tags in this repository are the original author's and do
not contain these patches.

## Build

You need JDK 21. With a newer default JDK the build stops at once with
`Failed to init packages for /usr/lib/jvm/java-<n>-openjdk`; point Gradle at 21:

```sh
JAVA_HOME=/usr/lib/jvm/java-21-openjdk ./gradlew build
```

The first run downloads Gradle, Minecraft and mappings and takes a few minutes. The result is
`build/libs/freecursor-1.2.10-mt3+mc1.21.11.jar`. Put that file in `mods/`; do not use the
`-sources.jar` next to it.

## Branches and following upstream

- `1.21.11-mt` (the default branch) is this fork. It sits on upstream's 1.2.10.
- `main` mirrors the original and is never changed here.

Upstream has since moved to Minecraft 26.x, so we do not merge it. When the group changes
Minecraft version, the patches are ported by hand to a new `<minecraft-version>-mt` branch:
see `docs/agents/porting-patches.md`.

## Contributing

Bugs and changes to the patches go to this fork's issues and pull requests (pull requests to
`1.21.11-mt`). New features are welcome too. A change that would help every FreeCursor user
is worth offering to the original author as well.
Contributors and their coding agents: read `AGENTS.md` first.

## Licence

GPL-2.0, see `LICENSE`. The original author's credit stays in `fabric.mod.json`.

---

# The original README (Makev1ch)

Everything below is the original author's README, unchanged.

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
