package dev.suwizx.buyhead.config

object DefaultHeads {
    fun categories(): List<CategoryConfig> = listOf(
        CategoryConfig(
            id = "mobs",
            displayName = "Mobs",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzlkOWM0MWRhOWZkMmMyOGIyNjE4OTIyNGExZGZkM2YwZWYyYzI2OGUxNmE2YzVmYTkzOTliMTUxMDJhYzA5ZCJ9fX0=",
            heads = listOf(
                HeadDefinition("Creeper", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzlkOWM0MWRhOWZkMmMyOGIyNjE4OTIyNGExZGZkM2YwZWYyYzI2OGUxNmE2YzVmYTkzOTliMTUxMDJhYzA5ZCJ9fX0="),
                HeadDefinition("Zombie", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzUzOWNiZTRjZjdjZTc2NGMwNmFiYzgyNzliNzhlNTM3ZDNkNjdiYmQ5YjY1YjgxMGFhMzY0NmM5MDZiOWZkNmIifX19"),
                HeadDefinition("Skeleton", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMwMWMwNGRlMzc1MDhmNjA2OWJiZGY4NGVlNDU0OWE4NjQwNzVhOWIxMjkzMDZiNGI2MGYyYzFjNDhjZjE3MSJ9fX0="),
                HeadDefinition("Spider", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZjMzNkN2NlYzY4YzUyNzVlMTY5YjlhNGUzMDQwMDE3ZWNlN2NhODhlNjlkOGY4ZGQzNGE5YzViZjE5ZGJmYjMifX19"),
                HeadDefinition("Enderman", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NkMWM0M2U5OTMzZDlkMTk2NjA1MDFhNGI4MDZjYzI0ZTBjNzVmNzFhNzZhNGM0N2RkYjNmMTViZGY5NDdlIn19fQ=="),
                HeadDefinition("Blaze", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFlOGQzZWZkOTZiMjE3NjVlYTA1ZDUzZjBlYzBiNzNlZDllMTY4Njc2MzRjYjFhNDZkNDhlY2IwMTkyMGNkYiJ9fX0="),
                HeadDefinition("Ghast", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE4ZGM4MjFjNjQ3NzUwNjRhZjliNmUzYWVhNDQ0ZTM3OTI2ZGM3MDUwMGE5NWJkODI3NTI5ZjliMGJkNDcifX19"),
                HeadDefinition("Slime", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY1YmZiMTZiOGUxYjFiZTBjNDkzMGRkNmZhNGRmYThiNWE0ZTdjMmVlMTlhOTg2YzBlOTEzNDZlZGZiMzMifX19"),
                HeadDefinition("Iron Golem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZhZGYzM2MzMTcxNzliNzZlZDBkY2UxNjRhOWViOWYzMWVkYjgxMTlkNjVjNjFkZmM0ZjZhMzU5NWViNmM3OCJ9fX0="),
                HeadDefinition("Wither Skeleton", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc2OGI4YWVhZTU2MzVlY2M2OTgyOGZiN2IyYTFiN2JiMjg4MDllNTczZDRiZWFiNTFhMTJhMjM3MzA5OWIifX19")
            )
        ),
        CategoryConfig(
            id = "animals",
            displayName = "Animals",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y3NzlmZjk5ZGZhM2Q1MTFiMGJiZThhMDFlNGZlYjg1NzVlZjZjYjFiYzlhNTkyZDlmOGQ1YjgxZDFkMzIifX19",
            heads = listOf(
                HeadDefinition("Cow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y3NzlmZjk5ZGZhM2Q1MTFiMGJiZThhMDFlNGZlYjg1NzVlZjZjYjFiYzlhNTkyZDlmOGQ1YjgxZDFkMzIifX19"),
                HeadDefinition("Pig", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzliMjcyZjZlYTY3MDU2ZGVlMDZjOWEzNzZmOWViYzIwMWI3ZmMxYmY0MjFjYWI1YzgzOGIyNTUyNmY0YzIifX19"),
                HeadDefinition("Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2IyYjZkNjUxMjU1ZjA5MDBlZjRlZDViZGQ3MTA2MzI1ZDZiNjczNjcwZWY1NzdiMzZkMmVkZDYxNzc0NjJhIn19fQ=="),
                HeadDefinition("Chicken", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzJjYTE5MzRkNzY2M2UzNTM5MTNjNGFhZGYzNzI1NDI4MzZhNmE1OTg4ODhkMjkxNTFiOTNkZTJmZjA4MDkifX19"),
                HeadDefinition("Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzk4OGI0YzljZWU3ZWQ5NmI5ZjczMWZkN2E2N2FhMDY0MzYyMTZkMTcxOGNmNTI2YzFhYzM0N2I3Njc2NTYifX19"),
                HeadDefinition("Fox", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y4MDU1ZGZmOGZjMzQyNDlkNWFjOGNkMjkwNGE5YTI4ZjVmNGQ2ZGIxNzI4YWViZjI2NzFjY2MzZGVkZDMifX19"),
                HeadDefinition("Bee", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNlMGZmZGFhMzIxZDgwOGZhZTM1NjhmZmFiOTkzNGY4OTlhZGE3MGM5ODMxNGZmN2I4YTE0ZTNjYjc0MDkifX19"),
                HeadDefinition("Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzM2ZTYyNzJhODFlNzM2YmQ5YmFjNmRjYTcyZDY4Y2IyODU5OTkwMmFkNjljMGUzMDc3MDhiYmRjNjkwODkifX19"),
                HeadDefinition("Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEyODMwNzRmNTExNzRhNzZkOWMzMzJmODE0ZGI5NjVlNTVhMWQzZmNmYjY2NDU3NTkwOTY5NTNkMmM3NzkifX19"),
                HeadDefinition("Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFmNzFjOTdiMmM5OTc4YWJlY2ZhYzU2NzFiMGZmMDU5MDcwZmY0OTk1OGU2NTk2OTMyY2EwNjA5Mzg5OWYifX19")
            )
        ),
        CategoryConfig(
            id = "aquatic",
            displayName = "Aquatic",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdlOTE0YzVlMTM1YWRjMzkzMzg1YTJkYmYyNDE1ZmU1YzE0MjgwNjA1YmE5YzYwZWFhZmEzZjNmMjM1ZDExYSJ9fX0=",
            heads = listOf(
                HeadDefinition("Dolphin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVhZGFiNzQ1ZWFhMzJjNGMxMzg2NjlkYTMxNzFlMjBjMGI2YmVhY2ZiNWIzNzAzNzMzODNmNGJlMjlmZjc5ZSJ9fX0="),
                HeadDefinition("Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E5ODExMzcyNWU3ZTE0NmQ5OWExMGFmZTE5OGYzMmMzY2Q2YWMxNTUxOWY1NzExMWI4MjZmMmM0ZjcxIn19fQ=="),
                HeadDefinition("Turtle", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q0YjFlNWEwODkwNzUwMzk0ZjQ3NjkzOGViZTgxNjk0MmM2ZjgxMTliNzZlNjgzODMzYzRiYjBjMzlhMDUifX19"),
                HeadDefinition("Squid", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRlNjZjNmQyODI5YzNiZjE3OGJmNjIyMGNhYzk4NTc2MDU4NzAyYjFmNDEzNjM3NzI1NGFiM2NlNWU4MjkifX19"),
                HeadDefinition("Guardian", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2IzNjFkYThlYWFlNWZhZTE3OGE5NzE0Y2I0NTFkMTQ4NDk0YzI5YmE1MzIxMzlkODIxYTY0NDcxOThiMzEifX19"),
                HeadDefinition("Elder Guardian", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ0YTY5YTE4MWExNWFiOGE1MmNhMjE4YTkxNzQ3OThiMGM3MmM5MzhjMjdiNzE3YjBmYmYzMmViOWNhOCJ9fX0="),
                HeadDefinition("Drowned", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E2YzhhYzQzNThkZDUxZDlkODIyMmNkNjk5MGE0NTZkN2I1OGZlN2M4ZjExMjlmNGNkZDI2ZTA1ZjczNDYifX19"),
                HeadDefinition("Frog", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzgxMDdkYzRhZGE5MDNkYjU5ZjExYzAyY2VlYTAxYmZhZmI5OGI2ZjdhNzJmNzNhNjI5M2ZkMjZlMTY2YWYifX19")
            )
        ),
        CategoryConfig(
            id = "nether",
            displayName = "Nether",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQxZTk2ZDViMzI2ZDUxZTVjOWU4YmUzN2QzYTA0ZjNjMTljZWQ4MDk0MjY5MWM0YzU4MGViY2I3NzUwZDNiIn19fQ==",
            heads = listOf(
                HeadDefinition("Piglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQxZTk2ZDViMzI2ZDUxZTVjOWU4YmUzN2QzYTA0ZjNjMTljZWQ4MDk0MjY5MWM0YzU4MGViY2I3NzUwZDNiIn19fQ=="),
                HeadDefinition("Hoglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2FkMTk5NWJiNjFiMzVlYmJhNDE4ODNhZjg1MDVjYTRlZDNhMDE1M2U0OTk4NjA3MmIxNGNkYTFmNTgwYjQyIn19fQ=="),
                HeadDefinition("Strider", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE2ZWQ5YWNhMzM4MzM5OWYyMjQ1YmQ3ZTViZGFhMzQ4MDQzNjZmZWFiZTRlMGYzNWFmZGZmOWVkOTJmZjgifX19"),
                HeadDefinition("Zoglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI3MTRlZmE4MzVmNzE1MTIyOGMzZjQ2NGVlNDZiNTE1NzY2MmQwNTg0NDQ5ZTRkMTk3NGZlNGI2YWEzMTkifX19"),
                HeadDefinition("Magma Cube", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ExMzhmMjI3NTIwODFhMWI4MmIxODIzMGIxMzExZjQ5MjFlMTYyMjliNmI3ZGZjYzZhMjI2NDkwYTk3ZTJmIn19fQ=="),
                HeadDefinition("Piglin Brute", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVlYjFlMjU0MzJhZWYwYWM0MmY4ZTQ3ZTFkYWZmODYzNzMxZDJmYjlmNDZlNjMxNWJlMWI2ZDhhNDFhZGYifX19"),
                HeadDefinition("Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzViN2Q5ZTExZWE5MTc1ZDQzMTg4N2VlNmU3Yjc2YjA0ODNiMmNiNWVkNzdiOWU0ODQ3MjhlMzJkMmQzMmNiIn19fQ==")
            )
        ),
        CategoryConfig(
            id = "end",
            displayName = "The End",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NkMWM0M2U5OTMzZDlkMTk2NjA1MDFhNGI4MDZjYzI0ZTBjNzVmNzFhNzZhNGM0N2RkYjNmMTViZGY5NDdlIn19fQ==",
            heads = listOf(
                HeadDefinition("Enderman", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NkMWM0M2U5OTMzZDlkMTk2NjA1MDFhNGI4MDZjYzI0ZTBjNzVmNzFhNzZhNGM0N2RkYjNmMTViZGY5NDdlIn19fQ=="),
                HeadDefinition("Endermite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNkNjg4ZWI0N2M5MjliOWY3Zjg5OGZlZDM0OTUzMGRjYjRhZGQwYzYyMDE1MzU4ZjhkMmFhMTExYzE0MmIifX19"),
                HeadDefinition("Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFiYzE3ZDhkNzk1OTQ0MTFiYTRmNzVhMGEyZmMwNzJlYzE4MDRkMmQ4YzI1ZTFhNzQ3NDhiZGE4ZTM3ZTEifX19"),
                HeadDefinition("Phantom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzhkZjlmOGYzYTk0OGQ2N2YwMjFkNjQ5MDRmMDM3MDdlNzZlMDM5ZGEzNGZjYTVhYTU3OWRhZjM1NmJhMzcifX19")
            )
        ),
        CategoryConfig(
            id = "food",
            displayName = "Food",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg4ZjUwOGNkYjNjNzM4YzNkMWU0OTJiYTNhYzVhNzFkMTU5NDExZjY0NDliODEzZDc1ZjdiNzIzN2JkZiJ9fX0=",
            heads = listOf(
                HeadDefinition("Cake", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RjMzQzNjUwMjg4YzQ5YWUzMTliN2YwZTY5NGY1OGNkNGNlN2RjNThlZGE5NTczMDZiN2E3YzM0MWQzYzYifX19"),
                HeadDefinition("Cookie", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg4ZjUwOGNkYjNjNzM4YzNkMWU0OTJiYTNhYzVhNzFkMTU5NDExZjY0NDliODEzZDc1ZjdiNzIzN2JkZiJ9fX0="),
                HeadDefinition("Apple", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYxMjRkZDkxMTc5MjM1ODI1YjVhNTJhMmE4NzMxNzlhYWM4NjA0YTkzN2ViNDZjNDllYWMwOWIyNmFkNjEifX19"),
                HeadDefinition("Watermelon", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzM3ZmNhNzdhNjhkYjgxNWYzZTgxZWQyMTJhNWEzZTNkNzI2NTYwNjY2MDVmNzMzNjgyMzFjNzJjM2RhYmYifX19"),
                HeadDefinition("Bread", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4NGZlZjMwNzI5NDdlMGQxZmQ1MDY5NDZhM2IxMzQ4NWEzZjVjMzZkMjE1N2M4YTMzOTQ1ZjkyZGU0NjQifX19"),
                HeadDefinition("Pumpkin Pie", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNhNTlmMGY3YzhhMTYwY2RjNTcyMmMzMDhiZTk5YjM5ZWVmMjRlN2ZiZTI5ZGY4YTZiZjBhNjA1NGQxNDUifX19"),
                HeadDefinition("Carrot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzk5MzkxNTMxNjM3ZWJiNWFkMmI2ZDE4ZGI0Njg0MmZiZmExMDJmNTUxMzY5N2M1OGZmMjkzNjlkMzY5ZiJ9fX0="),
                HeadDefinition("Mushroom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I1NjkyNGY3YWRhMmUzNGZhYjBhNmYwOTU1Y2U5Mjc4MDU2NGNkZGNjNGQ2YmM4Y2FhNjA5MTRlOTM2ZmIifX19")
            )
        ),
        CategoryConfig(
            id = "blocks",
            displayName = "Blocks",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZhMWZiMTA0ZDI1YjIzNjFhNDA3MWJiYjM2NGUzMGNkNzQxMjZhZTM0OGRlMWNhMTViZTBmMWMxZTczODkifX19",
            heads = listOf(
                HeadDefinition("TNT", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg4NjllMzFlNjI4NTU3OTEzMzg3MzU5MWVmM2I2NmZhYTg1YzgyNzRkMTRhMDA0YTBhZjJhMDQ0MzFiNzMifX19"),
                HeadDefinition("Crafting Table", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2QxZjc1YjE3MDQ3YTc5NTQxNmVhNzdkMWE2NTUxMWFjYzM1ZmM3NGNhZWJiNjM5NGE1MTEwZDkxMTMwMGYifX19"),
                HeadDefinition("Chest", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzc3OTRhYjhkNjdiMjcwM2UwNjVhMzZjN2E5ZmVmNzJkYmI5ZjQ4NDI4ZjkzNWI3YWI5ZmFmM2I1NDZhNDMifX19"),
                HeadDefinition("Grass Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZhMWZiMTA0ZDI1YjIzNjFhNDA3MWJiYjM2NGUzMGNkNzQxMjZhZTM0OGRlMWNhMTViZTBmMWMxZTczODkifX19"),
                HeadDefinition("Diamond Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE5MTYxMjBmZjljMmQxOTQ0MjRjZTM2YjFmNjA3N2IyNTc3NTg4ZDVkMWU0ZjI5MjZkYzZhMjRiMzQ3ZjMifX19"),
                HeadDefinition("Bookshelf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU2ZjJkZTY4YmQ1OTQwMjk5NjNhNTIzMGRhMDZmMDE5NmI5N2E0ZGZjYjQwNzFkMGJlYTJjMjFiNjM1MzAifX19"),
                HeadDefinition("Furnace", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2FjNDhiMDA4MTVlNGVmZWIzM2Y2Nzc2YzliNDM5MjZjYzlhMGIyOGVkNGU5MjZmMjFlZTFjY2Q4NDhjOSJ9fX0=")
            )
        ),
        CategoryConfig(
            id = "fantasy",
            displayName = "Fantasy",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNlMDNhNGI5MDUyNTZiNzAyY2ZiNTI3ZmMxMzZlMWZiNzRhN2Y5NDQwMjFjNjYzOGVhYTU4ZjgzMjVmMmMifX19",
            heads = listOf(
                HeadDefinition("Dragon", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNlMDNhNGI5MDUyNTZiNzAyY2ZiNTI3ZmMxMzZlMWZiNzRhN2Y5NDQwMjFjNjYzOGVhYTU4ZjgzMjVmMmMifX19"),
                HeadDefinition("Phoenix", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdiMzFiZGNmZWFhM2NmMjNkZjFlY2YxMzJmMDE1ZGQyYTE2MTNkMGVkN2I5YjQxZjk5ZDlhMzFjNmZiNmIifX19"),
                HeadDefinition("Fairy", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2YzMjY0ZjdiZWUzOTJlNWQyZDZlZjViNTY4ZjZkZWViMTYzNTI3ZjUzNWM5YThiMmYzOGEyZjg2OGNiNjgifX19"),
                HeadDefinition("Robot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NhZDE0ZmZhZDlhNDljOTFlMzYzMWVkMGVhMzQ3M2Q3NzliMzM5ZWU5NmU5MWUwMWE0ZGZhODY3ZmE3NmQifX19"),
                HeadDefinition("Demon", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzJmNDZlNjY3MTA2MTY1MWE5MTAyNWZhMzRiYjc5Yjg0Njk4OWI1NWRlZjFhODVkMWIzODEyMWMwNDNiMzMifX19"),
                HeadDefinition("Angel", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZlMzNkYzE3ZjM0NzMxZTNkNzUxNmI3OWVhNzlmMjkwZGRhN2VhMjU3YjA0ZDE3M2RmYmZiMjgxZjhlNyJ9fX0=")
            )
        ),
        CategoryConfig(
            id = "misc",
            displayName = "Misc",
            iconTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZhNGFiNGI2NDQ2NzNmN2QzMWM4YzZhM2IzMjMxM2JhOTYzMTgwMzVhOGM3OGRlZGU2OWZlNGY1YmViNDkifX19",
            heads = listOf(
                HeadDefinition("Santa", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg3MTdiOGY5ODY0ZDYxNTllOGY3MGRhY2Q0YjBmZmJlNzk5ZWRjYzI5YTMwZjJkNTYxMjM1YjUxMjE0MzIifX19"),
                HeadDefinition("Pirate", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZhNGFiNGI2NDQ2NzNmN2QzMWM4YzZhM2IzMjMxM2JhOTYzMTgwMzVhOGM3OGRlZGU2OWZlNGY1YmViNDkifX19"),
                HeadDefinition("Ninja", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzJiNzgxZDMyYzdiNDFlNjE2M2IwY2ZiZDkyMDJkOTVjN2QzYTYzMGMxZTFmYWM4OGQwNzc1NGRhNGM0MmIifX19"),
                HeadDefinition("Astronaut", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNhYzI0MjZmYzIxMzZmZjBhYWM3Yzg2MmNjZjFjZjk2MzZhNTY5OTZkZDQ3NWU2OWJiNjkyYjQ2YjEyZjkifX19"),
                HeadDefinition("Viking", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg1OWMwMzViNDQwMjI4OThjMzdjZmExODM5ZjU5ZjNlNDc5NzVlYjQ3M2Q2YzZjZWVlNDkyMmVmNmYifX19"),
                HeadDefinition("Wizard", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg3NzY2MzAyMzk1NzE2ZGJhZGQxMTQxOGFmYjZlNjliN2Y0ZjE2ZDhlMDcyMzgzZjIxMzAyYjI4NDJmNDkifX19")
            )
        )
    )
}
