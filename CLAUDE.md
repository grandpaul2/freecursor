# CLAUDE.md

Guidance for an AI agent (or a person) working on MacroTech's fork of FreeCursor.

## What this is

A fork of [Makev1ch/freecursor](https://github.com/Makev1ch/freecursor) (GPL-2.0), a
client-side Fabric mod that frees the mouse cursor on a key. Our work is the branch
`1.21.11-mt`: the original mod plus a few small patches, built for Minecraft 1.21.11. The
branch `main` is the original author's and is never changed here.

## Commands

    ./gradlew build        # build/libs/freecursor-<version>.jar
    ./gradlew runClient    # a game with the mod

JDK 21 (`JAVA_HOME=/usr/lib/jvm/java-21-openjdk ./gradlew build` if the default is newer).
There are no automated tests: build it and try it in `runClient`. Nothing on GitHub builds
anything.

## Rules

- **Keep the fork small.** Each change is a patch on top of the original, marked in the code
  with a comment that starts "MacroTech patch" and says why. Don't reformat or reorganise the
  original's code: it makes taking the author's updates harder.
- **Say what changed and when**, in the notice at the top of the README and in
  `CHANGELOG.md`. The licence asks this of a modified version.
- **Version**: `mod_version` in `gradle.properties` is the original's version with `-mt<n>`
  added, counting up with each release of ours (`1.2.10-mt2+mc1.21.11`).
- **It stays GPL-2.0**, with the original `LICENSE` and the author's notices.
- **A fix that isn't specific to us belongs with the original author**: offer it there too.

## Pull requests

Branch from `1.21.11-mt`, keep the change to one patch, and open a pull request against
`1.21.11-mt` that says what a player will notice and how you tried it.
