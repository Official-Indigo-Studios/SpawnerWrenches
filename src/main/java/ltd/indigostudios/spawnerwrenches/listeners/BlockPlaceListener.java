package ltd.indigostudios.spawnerwrenches.listeners;

import ltd.indigostudios.spawnerwrenches.api.Keys;
import ltd.indigostudios.spawnerwrenches.api.events.PlayerPlaceSpawnerEvent;
import ltd.indigostudios.spawnerwrenches.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        if (!item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (!container.has(Keys.WRENCHES, PersistentDataType.STRING)) return;

        String entityName = container.get(Keys.WRENCHES, PersistentDataType.STRING);
        if (!Utils.entityExists(entityName)) return;

        EntityType entityType = EntityType.valueOf(entityName);

        PlayerPlaceSpawnerEvent event = new PlayerPlaceSpawnerEvent(e.getPlayer(), e.getBlock(), entityType);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;

        e.getBlock().setType(Material.SPAWNER);
        CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
        spawner.setSpawnedType(entityType);
        spawner.update();
    }

}
