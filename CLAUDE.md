# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

**BuyHead** — a server-side only Fabric mod for Minecraft 26.1.2 that lets players buy decorative player heads via chest-based GUI. Vanilla clients can join without a client mod.

- Mod ID: `buy-head`
- Group: `dev.suwizx.buyhead`
- Minecraft: `26.1.2` | Fabric Loader: `0.19.2` | Fabric API: `0.146.0+26.1.2`
- Kotlin: `2.3.20` | JVM target: `25`

## Commands

```bash
# Build the mod jar (outputs to build/libs/)
./gradlew build

# Build without running tests
./gradlew build -x test

# Run a development server (launches Minecraft server for testing)
./gradlew runServer

# Run the development client (not needed — this is a server-only mod)
./gradlew runClient

# Regenerate mappings / refresh Loom caches
./gradlew migrateMappings

# Generate data (datagen entrypoint — remove once client source set is deleted)
./gradlew runDatagen
```

The built jar is at `build/libs/buy-head-<version>.jar`. Copy it to a Fabric server's `mods/` folder alongside Fabric API and fabric-language-kotlin.

## Architecture

This mod is a **server-side only** Fabric mod. The current template still has a split client/server source set structure that must be removed before the mod is complete — see the implementation plan in the conversation history.

### Source sets (current template state)
- `src/main/` — server-side code (`BuyHead.kt` entry point, mixins in Java)
- `src/client/` — client-side code (`BuyHeadClient.kt`, `BuyHeadDataGenerator.kt`) — **must be deleted** for server-only deployment

### Making it server-only
Three files need changes together:
1. `fabric.mod.json` → `"environment": "server"`, remove `client` and `fabric-datagen` entrypoints and client mixin block
2. `build.gradle.kts` → remove `splitEnvironmentSourceSets()`, client source set registration, and `fabricApi { configureDataGeneration { client = true } }` block
3. Delete all files under `src/client/`

### GUI system
All UI is chest-inventory based (`ChestMenu` / `SimpleContainer`) — the only screen type a vanilla client can render that the server controls. Custom screens are not possible without a client mod. GUI state per player is tracked in a `ConcurrentHashMap<UUID, GuiPageState>`.

### Head item construction (MC 1.20.5+ / 26.x)
Uses the DataComponent API — **no raw NBT**:
```kotlin
val stack = ItemStack(Items.PLAYER_HEAD)
stack.set(DataComponents.PROFILE, ResolvableProfile.createResolved(gameProfile))
stack.set(DataComponents.ITEM_NAME, Component.literal(name))
```
The `GameProfile` must have the `textures` property populated with a base64 skin texture value.

### Async skin resolution
Custom player head lookup (`/buyhead player <username>`) hits the Mojang API. Always run blocking HTTP calls on `Util.backgroundExecutor()` and bring results back to the server tick thread via `.thenAcceptAsync(..., server)`. `MinecraftServer` implements `Executor`.

### Config
JSON config at `config/buy-head.json` (relative to server root), loaded via Gson (already on classpath as a Minecraft transitive dep — no extra declaration needed). Loaded in `onInitialize()` via `FabricLoader.getInstance().configDir`.

### Click protection
Any `ChestMenu` used as a GUI **must** override both `clicked()` (block all item movement — return early without calling super) and `quickMoveStack()` (return `ItemStack.EMPTY`) to prevent players from extracting items for free via shift-click or drag.
