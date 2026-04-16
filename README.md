# Buy Head

A server-side Fabric mod for Minecraft that lets players buy decorative player heads through an in-game chest GUI. No client mod required — works with vanilla Minecraft clients.

## Features

- **Browse & Buy:** Access hundreds of decorative heads organized by category (Mobs, Animals, Food, Blocks, etc.).
- **HeadDB Integration:** Dynamically fetches thousands of heads from the HeadDB API if enabled.
- **Custom Player Heads:** Buy any player's head by username with `/buyhead player <username>`.
- **1.21.x Optimized:** Built for Minecraft 26.1.2 (1.21.2 snapshots) with strict adherence to DataComponent APIs.
- **Enhanced Stability:** Includes full inventory padding and forced synchronization to prevent client-side crashes with mods like JEI/REI.
- **Unique Variants:** Every head variant (even those with the same name) has a unique UUID to prevent texture caching issues.
- **Configurable:** Change currency (item & amount), toggle HeadDB, and customize categories.

## Commands

| Command | Description | Permission |
|---|---|---|
| `/buyhead` | Open the category browser | All players |
| `/buyhead player <username>` | Buy a specific player's head by username | All players |
| `/buyhead reload` | Reload the config from disk | Op level 2+ |

## Requirements

- Minecraft 26.1.2 (1.21.2 Snapshot)
- [Fabric Loader](https://fabricmc.net/) 0.19.2+
- [Fabric API](https://modrinth.com/mod/fabric-api)
- [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin)
- Java 25+

## Configuration

Config file is created automatically at `config/buy-head.json` on first launch:

```json
{
  "currencyItem": "minecraft:emerald",
  "currencyAmount": 64,
  "allowCustomPlayerHead": true,
  "useHeadDbApi": true,
  "headDbCategories": ["blocks", "food", "characters", ...],
  "categories": [ ... ]
}
```

## Building

```bash
./gradlew build
```

Output JAR is in `build/libs/`. 

## License

MIT — see [LICENSE](LICENSE).

---

> [!CAUTION]
> This project was built with AI assistance. While rigorously tested for stability in Minecraft 1.21.2, please report any issues found!
