# Buy Head - Project Context

A server-side only Fabric mod for Minecraft 26.1.2 that allows players to purchase decorative player heads through a chest-based GUI. This mod is designed to work with vanilla Minecraft clients, requiring no client-side modifications.

## Project Overview

- **Name:** Buy Head
- **Mod ID:** `buy-head`
- **Platform:** Minecraft 26.1.2 (Fabric)
- **Language:** Kotlin 2.3.20
- **Target JVM:** 25
- **Architecture:** Server-side only. All UI is handled through vanilla chest inventory menus.

## Key Technologies & Dependencies

- **Fabric Loader:** 0.19.2+
- **Fabric API:** 0.146.0+26.1.2
- **Fabric Language Kotlin:** 1.13.10+
- **Build System:** Gradle (Kotlin DSL)

## Building and Running

| Task | Command | Description |
|---|---|---|
| **Build JAR** | `./gradlew build` | Generates the mod JAR in `build/libs/`. |
| **Run Server** | `./gradlew runServer` | Launches a development Minecraft server with the mod loaded. |
| **Refresh Loom** | `./gradlew migrateMappings` | Regenerates mappings or refreshes Loom caches. |
| **Datagen** | `./gradlew runDatagen` | Runs data generation (if configured). |

## Development Conventions

### Architecture & UI
- **Server-Only:** The mod must remain server-side only. Do not add client-only classes or dependencies that would require a client mod.
- **Chest GUIs:** All menus use `ChestMenu` or `SimpleContainer`. 
- **Security:** To prevent players from taking items from the GUI:
    - Override `clicked()` in the menu class and return early without calling `super.clicked()`.
    - Override `quickMoveStack()` and return `ItemStack.EMPTY`.
- **State Management:** Player GUI states (e.g., current page) are tracked in `GuiStateManager` using a `ConcurrentHashMap<UUID, GuiPageState>`.

### Item Handling (Minecraft 26.x / 1.20.5+)
- **DataComponent API:** Use the DataComponent API for item metadata. **Do not use raw NBT.**
- **Player Heads:** Use `DataComponents.PROFILE` with a `ResolvableProfile` to set head textures.
- **Async Operations:** Mojang API lookups for player skins must be performed asynchronously using `Util.backgroundExecutor()` and returned to the main server thread via `server.execute()`.

### Configuration
- **Path:** `config/buy-head.json` (relative to the server root).
- **Format:** JSON, handled via Gson (included with Minecraft).
- **Currency:** Configurable via `currencyItem` and `currencyAmount`.

### Code Style
- Follow idiomatic Kotlin patterns.
- Ensure all blocking I/O (like config saving/loading or web requests) happens off the main server thread where possible.
- Clean up player-specific state in `ServerPlayConnectionEvents.DISCONNECT`.

## Project Structure

- `src/main/kotlin/dev/suwizx/buyhead/`
    - `BuyHead.kt`: Main entry point and event registration.
    - `command/`: Command handling logic (`/buyhead`).
    - `config/`: Configuration loading and data classes.
    - `gui/`: Custom chest inventory menus and GUI management.
    - `head/`: Player head creation, skin fetching, and caching.
    - `transaction/`: Currency deduction and item delivery logic.
