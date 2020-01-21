package ltd.indigostudios.spawnerwrenches.utils;

import ltd.indigostudios.spawnerwrenches.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class YML {

    private Main main = Main.getInstance();

    private String name;

    private FileConfiguration fileConfiguration;
    private File file;

    /**
     * Constructor to create a new YML class to manage a YML file
     * @param name The name of the YML file
     */
    public YML(String name) {
        this.name = name;
    }

    /**
     * Setup the config file if necessary
     */
    public void setup() {
        if (file == null) {
            file = new File(main.getDataFolder(), name + ".yml");
        }

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            main.saveResource(name + ".yml", false);
        }

        reload();
        fileConfiguration.options().copyDefaults(true);
        save();
    }

    /**
     * Reload the config file
     */
    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        InputStream is = main.getResource(name + ".yml");
        if (is != null) {
            fileConfiguration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(is)));
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Save the config file
     */
    public void save() {
        // contain try catch in here because it makes life easier
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert class to a string to get file name
     * @return The file name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Get the YML classes file configuration
     * @return The file configuration
     */
    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

}
