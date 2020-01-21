package ltd.indigostudios.spawnerwrenches;

import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.commands.*;
import ltd.indigostudios.spawnerwrenches.listeners.BlockPlaceListener;
import ltd.indigostudios.spawnerwrenches.listeners.PlayerInteractListener;
import ltd.indigostudios.spawnerwrenches.listeners.PrepareAnvilListener;
import ltd.indigostudios.spawnerwrenches.utils.YML;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private NamespacedKey key;

    private YML settings;

    // TODO: Toggle for sounds/particles?

    public void onEnable() {
        instance = this;

        key = new NamespacedKey(this, "spawner-wrenches");

        settings = loadSettings(new YML("settings"));

        // Commands
        registerCommands(
                new SpawnerWrenchesCommand()
                        .setName("spawnerwrenches")
                        .setDescription("Base command for spawner wrenches")
                        .setPermission("spawnerwrenches.admin"),
                new SpawnerCommand()
                        .setName("spawner")
                        .setDescription("Change a spawners type")
                        .setPermission("spawnerwrenches.setspawner"),
                new GiveWrenchCommand()
                        .setName("givewrench")
                        .setDescription("Gives a player a spawner wrench")
                        .setPermission("spawnerwrenches.admin"),
                new GiveSpawnerCommand()
                        .setName("givespawner")
                        .setDescription("Gives a player a spawner")
                        .setPermission("spawnerwrenches.admin")
        );

        // Listeners
        registerListeners(
                new BlockPlaceListener(),
                new PrepareAnvilListener(),
                new PlayerInteractListener()
        );
    }

    private void registerCommands(BaseCommand... commands) {
        for (BaseCommand baseCommand : commands) {
            PluginCommand cmd = getCommand(baseCommand.getName());
            if (cmd != null) {
                cmd.setTabCompleter(baseCommand.getTabCompleter());
                cmd.setDescription(baseCommand.getDescription());
                cmd.setPermission(baseCommand.getPermission());
                cmd.setPermissionMessage(baseCommand.getPermissionMessage());
                cmd.setExecutor(baseCommand);
            }
        }
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public YML loadSettings(YML yml) {
        yml.setup();
        Language.setConfiguration(yml.getFileConfiguration());
        for (Language message : Language.values()) {
            if (message.getList() != null) {
                yml.getFileConfiguration().set(message.getPath(), message.getList());
            } else {
                yml.getFileConfiguration().set(message.getPath(), message.getDefault());
            }
        }
        yml.save();

        return yml;
    }

    public static Main getInstance() { return instance; }

    public NamespacedKey getKey() {
        return key;
    }

    public YML getSettings() {
        return settings;
    }
}
