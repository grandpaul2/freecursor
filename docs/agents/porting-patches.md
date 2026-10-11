# Porting the patches to a new Minecraft version or upstream release

This recipe is derived from the existing mt1 and mt2 commits, but it has not been run end to
end; expect to adjust it, and tell the maintainer where it was wrong.

Use it when the group changes Minecraft version, or when upstream makes a release you want
under the patches. The fork is not merged with upstream, because upstream's releases after
1.2.10 target Minecraft 26.x. The patches are small enough to reapply by hand.

## Steps

1. Add the original as a remote if it is missing, and fetch it:
   `git remote add upstream https://github.com/Makev1ch/freecursor` then
   `git fetch upstream --tags`.
2. Pick the upstream tag or commit that matches the Minecraft version (upstream tags look like
   `v1.2.10+mc1.21.9+`). Make the new branch from it:
   `git switch -c <minecraft-version>-mt <tag>`. This fork's branch `1.21.11-mt` was made from
   `8920747`, upstream's 1.2.10.
3. Reapply each patch. List them with `git grep -n "MacroTech patch" 1.21.11-mt` and read the
   blocks in `FreeCursorScreen.java` on the old branch (`render` for mt2, `renderBackground`
   for mt1). mt3 is `mixin/OutlineMixin.java`, its line in `freecursor.mixins.json`, and the
   `hideGlow` setting in `FreeCursorConfig.java`, `FreeCursorConfigScreen.java` and the two
   language files. `git show 504fb43` (mt1) and `git show 5e59142` (mt2) are the exact changes.
   Copy the blocks in, keeping the `MacroTech patch (mt<n>, <date>)` comments.
4. Fix what the new Minecraft version breaks. mt2 had to update `gradle.properties`
   (`minecraft_version`, `yarn_mappings`, `fabric_version`), the Loom plugin and Gradle
   wrapper versions (`build.gradle`, `gradle/wrapper/gradle-wrapper.properties`) and
   the `jar` block (`project.archivesBaseName` became `project.base.archivesName.get()`); the exact old and new lines are in
   `git show 5e59142`. Take the new values from the Fabric develop page for your version.
   The patches call Minecraft code (`ChatHud.render`, `DrawContext.createNewRootLayer`,
   `Screen.renderBackground`, `MinecraftClient.hasOutline`), so a changed signature or name fails the compile; fix the call.
5. In `src/main/resources/fabric.mod.json`, set `depends.minecraft` to the version range you
   build for (mt2 used `>=1.21.11 <1.21.12`).
6. Set `mod_version` in `gradle.properties` by hand: the upstream version, `-mt<n>`, and
   `+mc<minecraft-version>` (`1.2.10-mt2+mc1.21.11`). Do not use `update_version.sh`.
7. Update `README.md` (versions, the tag name, what the fork changes) and add a section to
   `CHANGELOG.md` with the date and "Based on upstream <version>".
8. Build: `JAVA_HOME=/usr/lib/jvm/java-21-openjdk ./gradlew build`. A newer Minecraft may need
   a newer Java; if so use the Java version Fabric's develop page names, and update
   `options.release` and `java` in `build.gradle` and `fabric.mod.json`.
9. The maintainer tries it in game, then tags the released commit and makes the new branch the
   default branch on GitHub. An agent does not tag.

## Check it worked

The build succeeds and the jar is named for the new version. Look at the diff against the
upstream tag: it should contain only the two patches, the build and version lines, and the
docs.

## What people get wrong

- Merging upstream's `main` into the branch. It is on Minecraft 26.x and replaces the build.
- Forgetting `fabric.mod.json`: the jar then loads on the wrong Minecraft version, or refuses
  the right one.
- Leaving a `-SNAPSHOT` Loom version in `build.gradle` (the fork uses `1.15-SNAPSHOT`; a
  release version is better when one exists for your Minecraft version).
