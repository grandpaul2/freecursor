# AGENTS.md

Guidance for a coding agent (or a person) working on MacroTech's fork of FreeCursor.

## What this is

A fork of [Makev1ch/freecursor](https://github.com/Makev1ch/freecursor) (GPL-2.0): a
client-side Fabric mod that frees the mouse cursor on a key. The fork is the branch
`1.21.11-mt`: the original mod plus two small patches (mt1, mt2), built for Minecraft 1.21.11.
MacroTech is a small technical Minecraft group. The branch `main` mirrors the original author
and is never changed here.

## What you can build

Anything your human asked for: a fix to one of the patches, a new feature, a new setting, an
experiment. It is a game and this is for fun. The maintainer builds and tries every pull request
before merging, so it does not have to be perfect.

Two things help a change land well here, because this is a fork:

- **Add, rather than rewrite.** A change written as a marked patch on top of the original's code
  can be carried to the next Minecraft version. A rewrite or a tidy-up of the original's files
  has to be redone by hand every time.
- **Offer it upstream too** when it would help every FreeCursor user, not only MacroTech. The
  original author's repository is where most players get the mod.

Another Minecraft version is a new branch, not a change to this one: see
`docs/agents/porting-patches.md`.

## Set up and commands

JDK 21 is required. With a newer default JDK the build fails in seconds with
`java.lang.IllegalStateException: Failed to init packages for /usr/lib/jvm/java-<n>-openjdk`.
Fix: prefix the command with `JAVA_HOME=/usr/lib/jvm/java-21-openjdk` (or your JDK 21 path).

```sh
JAVA_HOME=/usr/lib/jvm/java-21-openjdk ./gradlew build
```

- Needs network on the first run (Gradle, Minecraft, mappings, Fabric API): a few minutes.
  Later runs take seconds.
- Success is `BUILD SUCCESSFUL` and `build/libs/freecursor-<mod_version>.jar`. Install that
  one, not `-sources.jar`.
- **Run `./gradlew runClient` only when your human asks.** It opens a game window on their
  screen.
- Do not run `update_version.sh`. It is upstream's and writes `+mc1.21.9+` into the version.

## Find your task

| If you were asked to... | Read | Main files |
|---|---|---|
| Fix a patch (darkening, blur, chat) | this file | `src/client/java/com/makev1ch/freecursor/FreeCursorScreen.java` |
| Move the patches to a new Minecraft version or upstream release | `docs/agents/porting-patches.md` | `gradle.properties`, `build.gradle`, `fabric.mod.json` |
| Fix the docs | this file | `README.md`, `CHANGELOG.md` |
| Add a feature or a setting | this file | `FreeCursorScreen.java`, `FreeCursorClient.java`, `FreeCursorConfig.java` |

## Where things are

- `src/client/java/com/makev1ch/freecursor/FreeCursorScreen.java`: the screen shown while the
  cursor is free. **Both patches live here** (`render`, `renderBackground`).
- `src/client/java/com/makev1ch/freecursor/FreeCursorClient.java`: key binding (F6) and
  opening the screen.
- `FreeCursorConfigScreen.java`, `FreeCursorModMenuIntegration.java`: settings screen.
- `src/client/java/com/makev1ch/freecursor/mixin/PortalMixin.java`: portal screens.
- `src/main/java/com/makev1ch/freecursor/FreeCursorConfig.java`: settings, saved to
  `freecursor.json`.
- `src/main/resources/fabric.mod.json`: mod metadata and the Minecraft version it accepts.
- `gradle.properties`: Minecraft, mappings, loader, Fabric API and `mod_version`.
- `update_version.sh`, most of `CHANGELOG.md`: upstream's, left as they are.

## Rules

- **Write each change as a patch on top of the original.** That keeps taking the author's
  updates, and moving to a new Minecraft version, possible.
- **Mark each patch in the code** with a comment starting `MacroTech patch (mt<n>, <date>)`
  that says why, and list it in the README and `CHANGELOG.md`. The licence asks that a
  modified version says what changed and when.
- **The repository is GPL-2.0, so a contribution is GPL-2.0.** Do not change `LICENSE`, and
  keep the original authors' credit in `fabric.mod.json`.
- **Keep the mod id `freecursor`.** Other mods and saved settings refer to it.
- **Set the version by hand** in `mod_version` in `gradle.properties`, as the original's
  version plus `-mt<n>+mc<minecraft-version>` (`1.2.10-mt2+mc1.21.11`). Only the maintainer
  does this, when releasing.
- **Leave the original's files as they are where you are not changing behaviour.** Reformatting,
  translating or tidying them makes every later port harder. Russian comments, changelog
  entries and scripts stay.

## Check your work

There are no automated tests. What must pass: the build above, with `BUILD SUCCESSFUL`.
Whether the screen looks right can only be seen in a running game, which you cannot do.
Say "built, not tested in game" in the pull request and describe what the maintainer should
look at (cursor free: world not dimmed, no blur; HUD hidden by "Simulate F1": chat shows).
Never claim you tried it in game if you did not.

## Send the pull request

- Branch from `1.21.11-mt` and open the pull request against `1.21.11-mt` on
  `grandpaul2/freecursor`, never against `main`.
- One patch or fix per pull request. Do not bump the version.
- Fill in the pull request template: what a player will notice, the commands you ran and
  their result, what you did not check, and which AI agent wrote it (welcome, the maintainer
  just wants to know).
- Put no personal data, addresses, tokens or home-folder paths in code, docs or commit
  messages.

## Mistakes agents make here

- **Building with the default JDK.** Symptom: `Failed to init packages for ...java-27-openjdk`.
  Fix: `JAVA_HOME` set to JDK 21.
- **Running `update_version.sh`.** Symptom: `mod_version` ends in `+mc1.21.9+`. Fix: restore
  `gradle.properties`, edit the version by hand.
- **Tidying upstream's code or the Russian text.** Revert it; it is upstream's.
- **Treating the other tags as this fork's.** Only `v1.2.10-mt2+mc1.21.11` has the patches.
