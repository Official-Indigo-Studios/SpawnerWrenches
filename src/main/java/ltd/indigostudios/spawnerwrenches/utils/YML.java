package ltd.indigostudios.spawnerwrenches.utils;

import ltd.indigostudios.spawnerwrenches.Main;

import java.io.File;

public class YML {

    private final Main main = Main.getInstance();

    /**
     * Setup the config file if necessary
     */
    public void setup() {
        File file = new File(main.getDataFolder(), "config.yml");
        if (!file.exists()) {
            main.saveDefaultConfig();
        }
    }

    /**
     * Reload the config file
     */
    public void reload() {
        main.reloadConfig();
    }

    /**
     * Save the config file
     */
    public void save() {
        main.saveConfig();
    }
}
