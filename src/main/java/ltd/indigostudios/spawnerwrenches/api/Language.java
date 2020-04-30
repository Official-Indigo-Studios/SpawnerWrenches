package ltd.indigostudios.spawnerwrenches.api;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public enum Language {
    PICKUP_DELAY("Functionality.pickup-delay", 0),
    DEFAULT_WRENCH_USES("Functionality.default-wrench-uses", 1),

    SPAWNER_ITEM_MATERIAL("Items.spawner.material", "SPAWNER"),
    SPAWNER_ITEM_MODEL("Items.spawner.custom-model-data", 0),
    SPAWNER_ITEM_NAME("Items.spawner.name", "&d{0} &5Spawner"),
    SPAWNER_ITEM_LORE("Items.spawner.lore", Arrays.asList(
            "",
            " &7Place to create a &7Spawner.",
            "",
            " &c&lNOTICE",
            "  &7Can only be picked up using a &5&l&nSpawner Wrench&7!",
            "",
            "&9Spawner"
    )),
    WRENCH_ITEM_MATERIAL("Items.wrench.material", "SHEARS"),
    WRENCH_ITEM_MODEL("Items.wrench.custom-model-data", 0),
    WRENCH_ITEM_NAME("Items.wrench.name", "&5&l&nSpawner Wrench"),
    WRENCH_ITEM_LORE("Items.wrench.lore", Arrays.asList(
            "",
            " &7Collect any Spawner by right",
            " &7clicking them with this wrench and",
            " &7it will be immediately placed into",
            " &7your inventory!",
            "",
            " &dUses: &a{USES}",
            "",
            "&bPurchased from &3store.example.com"
    )),

    NO_PERMISSION("Messages.permission", "&cYou do not have access to that command!"),
    SPAWNER_TYPE_SET("Messages.spawner-set-type", "&aSpawner type set to &d{0}"),
    SPAWNER_WRONG_TYPE("Messages.spawner-wrong-type", "&c{0} is not a valid entity!"),
    SPAWNER_NOT_FOUND("Messages.spawner-not-found", "&cNo spawner block found at target location."),
    SPAWNER_INVALID_ENTITY("Messages.spawner-invalid-entity", "&cIncorrect usage. Please provide a valid entity."),
    SPAWNER_GIVEN("Messages.spawner-given", "&aGave &e{0} &a{1}x &d{2} &5Spawner&a!"),
    PLAYER_NOT_FOUND("Messages.player-not-found", "&cFailed to find a player {0}"),
    COMMAND_USAGE_SPAWNER("Messages.commands-spawner", "&cInvalid usage. Correct usage: &7/givespawner &b<player> &e<type> &a[amount]"),
    COMMAND_USAGE_WRENCH("Messages.commands-wrench", "&cInvalid usage. Correct usage: &7/givewrench &b<player> &a[amount]"),
    INVALID_NUMERICAL_VALUE("Messages.invalid-numerical-value", "&cInvalid numerical value entered! Please use whole numbers."),
    WRENCH_GIVEN("Messages.wrench-given", "&aGave &e{0} &a{1}x &5&lSpawner Wrench&a!"),
    HELP_MENU_HEADER("Messages.help-menu.header", "&8----- &5&lSpawnerWrenches &d&lHelp &8-----"),
    HELP_MENU_CONTENTS("Messages.help-menu.contents", Arrays.asList(
            "",
            "&d/spawnerwrenches help &8- &7View the plugin help menu",
            "&d/spawner <entity> &8- &7Set target spawner to a specific type",
            "&d/givespawner <player> <entity> <amount> &8- &7Give a player a spawner",
            "&d/givewrench <player> <amount> &8- &7Give a player a spawner wrench",
            "&d/spawnerwrenches reload &8- &7Reload the plugin"
    )),
    RELOAD("Messages.reload", "&aPlugin reloaded successfully!")
    ;

    private String path, def;
    private List<String> list;
    private static FileConfiguration configuration;

    /**
     * Constructor to create a message with a config path and default value
     * @param path The path to find the message in the config
     * @param def The default value of the message
     */
    Language(String path, String def) {
        this.path = path;
        this.def = def;
    }

    /**
     * Constructor to create a message with a config path and default value
     * @param path The path to find the message in the config
     * @param def The default value of the message
     */
    Language(String path, int def) {
        this.path = path;
        this.def = Integer.toString(def);
    }

    /**
     * Constructor to create a message with a config path and default values
     * @param path The path to find the message in the config
     * @param list List of default values
     */
    Language(String path, List<String> list) {
        this.path = path;
        this.list = list;
    }

    public String getDefault() {
        return configuration.getString(path, def);
    }

    public List<String> toList() {
        return configuration.getStringList(path);
    }

    public static void setConfiguration(FileConfiguration configuration) {
        Language.configuration = configuration;
    }

    public String getPath() {
        return path;
    }

    public List<String> getList() {
        return list;
    }

    public int toInt() {
        return configuration.getInt(path, Integer.parseInt(def));
    }

    @Override
    public String toString() {
        return configuration.getString(path, def);
    }
}
