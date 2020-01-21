# SpawnerWrenches
The new SilkSpawners. A simple and effective Spawner System using "wrenches" for collecting spawners.

## Configuration
There is one primary configuration file found in the plugins folder. This file contains every configurable option in the plugin including items and language options.

Configuration files:
- [settings.yml](https://github.com/Official-Indigo-Studios/SpawnerWrenches/blob/master/src/main/resources/settings.yml "Settings.yml")

## Commands and Permissions
Commands and permissions are extremely simple. Each command uses the admin permission for this plugin.

Command | Description | Permission
--- | --- | ---
`/spawnerwrenches` | Main command for the plugin | `spawnerwrenches.admin`
`/spawner <entity>` | Set target spawner to a specific type | `spawnerwrenches.setspawner`
`/givespawner <player> <entity> <amount>` | Give a player a spawner | `spawnerwrenches.admin`
`/givewrench <player> <amount>` | Give a player a spawner wrench | `spawnerwrenches.admin`

## API
Villages has a small API for developers, it contains two very useful events designed around this plugin. (Pickup event only fires when using the spawner wrench)

#### Events
```java
PlayerPickupSpawnerEvent
PlayerPlaceSpawnerEvent
```

## Support
You can find Indigo Studios and this plugin's developers on our Discord @ https://discord.gg/zVrtQvV
