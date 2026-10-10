# Changelog

All notable changes to this project will be documented in this file.

## [1.2.10-mt2+mc1.21.11] - 2026-10-10 (MacroTech fork)
### Changed
- Chat still shows while the cursor is free with the HUD hidden ("Simulate F1")
- Built for Minecraft 1.21.11 (Loom 1.15, Gradle 9.2.1)

## [1.2.10-mt1] - 2026-08-29 (MacroTech fork)
### Changed
- The world is no longer darkened while the cursor is free

## [1.2.9+mc1.21] - 2025-08-11
### Fixed
- В `freecursor.mixins.json` исправлено имя refmap на `client-freecursor.refmap.json`

## [1.2.8+mc1.21] - 2025-08-11
### Fixed
- Сборка: удалён ручной `annotationProcessor`; генерация refmap теперь через Loom (`defaultRefmapName`)

## [1.2.7+mc1.21] - 2025-08-11
### Fixed
- Сборка: добавлен `mavenCentral()` и корректный процессор `org.spongepowered:mixin:0.8.5:processor`
- Генерация `freecursor.refmap.json` включена

## [1.2.6+mc1.21] - 2025-08-11
### Fixed
- Настроен корректный `annotationProcessor` для генерации `freecursor.refmap.json`
- Добавлен репозиторий Fabric Maven
- Включён `PortalMixin` с `@Redirect(method = "tickNausea", at = FIELD currentScreen)`

## [1.2.5+mc1.21] - 2025-08-11
### Fixed
- Включён `PortalMixin` с корректным `refmap` (`freecursor.refmap.json`) как в рабочем моде
- Инъекция `@Redirect(method = "tickNausea", at = @At(FIELD currentScreen))` работает на 1.21.x (через refmap)

## [1.2.4+mc1.21] - 2024-08-11
### Fixed
- Временно отключен PortalMixin для устранения крашей при запуске
- Мод теперь работает без mixin, используя только базовую функциональность

## [1.2.3+mc1.21] - 2024-08-11
### Fixed
- **Portal GUI Access**: Исправлена работа мода в портале с использованием правильного mixin
- **Mixin Implementation**: Реализован mixin точно так же, как в allow-portal-guis
- **@Redirect Method**: Используется @Redirect для перехвата currentScreen в методе tickNausea

### Technical
- Обновлен PortalMixin для корректной работы с Minecraft 1.21.x
- Исправлены ошибки компиляции mixin
- Улучшена совместимость с Fabric Loader 0.17.1

## [1.2.2+mc1.21] - 2024-08-11

### Fixed
- **Portal GUI Access**: Исправлена работа мода в портале через PortalMixin
- **Mixin Implementation**: Добавлен PortalMixin для обхода ограничений портала
- **isInNetherPortal Override**: Принудительно возвращается false для работы GUI

### Technical
- Создан PortalMixin для ClientPlayerEntity
- Добавлен в freecursor.mixins.json
- Обновлена версия до 1.2.2+mc1.21

## [1.2.1+mc1.21] - 2024-08-11

### Fixed
- **Portal Support**: Исправлена работа мода в портале
- **GUI Access**: Убрана проверка на null экран для работы в портале
- **Portal Mode**: Мод теперь работает в портале как allow-portal-guis

### Technical
- Убрана проверка `if (client.currentScreen == null)` в FreeCursorClient
- Обновлена версия до 1.2.1+mc1.21

## [1.2.0+mc1.21] - 2024-08-11

### Added
- **Portal Support**: Мод теперь работает в портале в ад
- **GUI Access**: Возможность открывать экран мода в портале
- **Enhanced Compatibility**: Поддержка всех версий Minecraft 1.21.x

### Changed
- **Version Format**: Обновлен формат версии до 1.2.0+mc1.21
- **Portal Mode**: Экран мода открывается поверх экрана портала
- **Manual Activation**: Экран открывается только при нажатии клавиши (не автоматически)

### Technical
- Убрана проверка на null экран для работы в портале
- Обновлены зависимости для совместимости с Fabric Loader 0.17.1
- Улучшена совместимость с Minecraft 1.21.x

## [1.1.9+mc1.21] - 2024-08-11

### Added
- Initial release with basic functionality
- Free cursor functionality
- Configuration options
- Key bindings support

### Technical
- Fabric mod framework
- Client-side only implementation
- Gradle build system

## [1.1.9+mc1.21] - 2024-08-09

### Changed
- **Code Optimization**: Cleaned and optimized all Java source files
- **Removed Comments**: Removed all unnecessary comments for cleaner code
- **Better Performance**: Optimized imports and removed redundant code
- **Professional Polish**: Final code cleanup for production release

### Technical
- Streamlined all class implementations
- Removed verbose comments and documentation from source code
- Optimized import statements across all files
- Enhanced code readability through consistent formatting

## [1.1.8+mc1.21] - 2024-08-09

### Changed
- **Authors Section**: Moved Igr0man1y3_2 to authors list instead of description
- **License Display**: Updated license display to match project format (GPL v2)
- **Clean Description**: Removed author attribution from mod description for cleaner look

### Technical
- Updated fabric.mod.json authors array to include both Makev1ch and Igr0man1y3_2
- Enhanced README with proper authors and license information in Development section

## [1.1.7+mc1.21] - 2024-08-09

### Added
- **Issues Link**: Added proper bug tracker link in ModMenu for easy issue reporting
- **GPL v2 License**: Changed license from MIT to GNU General Public License v2
- **Dependencies Info**: Clear requirements section in README for Fabric API and ModMenu
- **Idea Attribution**: Credited Igr0man1y3_2 as the original idea author

### Changed
- **Improved Description**: Updated mod description to mention configurable key instead of fixed F6
- **Better Documentation**: Enhanced installation instructions with clear requirements
- **Professional Polish**: Final touches for a complete mod experience

### Technical
- Updated fabric.mod.json with issues URL for ModMenu integration
- License change to GPL-2.0-only for better open source compatibility
- Enhanced metadata with proper attribution

## [1.1.6+mc1.21] - 2024-08-09

### Changed
- **Improved Config UI**: Title is now displayed as regular text instead of a button
- **Unified Design**: ModMenu integration now uses the same clean interface as built-in config
- **Better Layout**: Cleaner spacing and visual hierarchy in settings screen

### Technical
- Simplified ModMenu integration to use native config screen
- Removed unused Cloth Config dependencies from ModMenu integration
- Enhanced visual consistency across all config access methods

## [1.1.5+mc1.21] - 2024-08-09

### Added
- **Built-in Config Screen**: Independent settings screen accessible without ModMenu
- **Config Key Binding**: New configurable key (N by default) to open mod settings
- **Standalone Functionality**: Full mod configuration available even without ModMenu installed
- **Native Settings UI**: Clean, simple settings interface using vanilla Minecraft UI elements

### Technical
- Added `FreeCursorConfigScreen` class for independent configuration
- Registered new key binding for config access
- Maintained full compatibility with existing ModMenu integration
- Enhanced key binding system with dual key support

## [1.1.4+mc1.21] - 2024-08-09

### Added
- **Disable Blur Setting**: New option to remove background blur when cursor is freed
- **Smart Blur Management**: Automatically saves and restores original blur settings
- **Perfect for Screenshots**: Clean background without blur for better visual quality

### Technical
- Added blur state management system
- Integration with Minecraft's `MenuBackgroundBlurriness` option
- Automatic restoration of user's original blur preferences

## [1.1.3+mc1.21] - 2024-08-09

### Changed
- **Replaced Black Screen**: Removed black screen option, replaced with F1 simulation
- **New Simulate F1 Setting**: Option to hide HUD elements when cursor is freed, exactly like pressing F1
- **Better World Visibility**: Perfect for screenshots and enjoying the Minecraft world view
- **Smart State Management**: Preserves original F1 state and restores it when closing

### Added
- F1 simulation functionality for HUD hiding
- Automatic state preservation and restoration
- Proper integration with Minecraft's built-in hideGui option

### Technical
- Updated configuration system to use `