package ltd.indigostudios.spawnerwrenches.api;

import ltd.indigostudios.spawnerwrenches.Main;
import org.bukkit.NamespacedKey;

public class Keys {

    private static final Main main = Main.getInstance();

    public static final NamespacedKey WRENCHES = new NamespacedKey(main, "spawner-wrenches");
    public static final NamespacedKey USES = new NamespacedKey(main, "wrench-uses");

}
