package ltd.indigostudios.spawnerwrenches.listeners;

import ltd.indigostudios.spawnerwrenches.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PrepareAnvilListener implements Listener {

    @EventHandler
    public void onPrepare(PrepareAnvilEvent e) {
        ItemStack item = e.getResult();

        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();

            NamespacedKey key = Main.getInstance().getKey();
            if (container.has(key, PersistentDataType.STRING)) {
                e.setResult(null);
            }
        }

        //if (e.getResult().getType() == Material.SPAWNER) e.setResult(null);
    }

}
