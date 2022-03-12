package ltd.indigostudios.spawnerwrenches.listeners;

import ltd.indigostudios.spawnerwrenches.Main;
import ltd.indigostudios.spawnerwrenches.api.Keys;
import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.api.events.PlayerPickUpSpawnerEvent;
import ltd.indigostudios.spawnerwrenches.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item == null) return;

        Player player = e.getPlayer();
        Block block = e.getClickedBlock();

        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (block == null || !block.getType().equals(Material.SPAWNER)) return;
        if (!item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        String type = container.get(Keys.WRENCHES, PersistentDataType.STRING);
        if (type == null || type.isEmpty()) return;
        if (!type.equalsIgnoreCase("wrench")) return;

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            EntityType entityType = ((CreatureSpawner) block.getState()).getSpawnedType();

            PlayerPickUpSpawnerEvent event = new PlayerPickUpSpawnerEvent(player, block, entityType);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            player.playSound(player.getLocation(), Sound.BLOCK_METAL_BREAK, 1, 1);
            block.getWorld().spawnParticle(Particle.SPELL_WITCH, block.getLocation().add(0.5, 0.5, 0.5), 30, 0.5, 0.5, 0.5);
            block.setType(Material.AIR);

            boolean remove = true;
            Integer uses = container.get(Keys.USES, PersistentDataType.INTEGER);
            if (uses != null) {
                if (uses > 1) {
                    remove = false;
                    int newUses = (uses - 1);
                    ItemBuilder.updateUses(item, newUses);
                }
            }

            if (remove) {
                if (item.getAmount() > 1) {
                    item.setAmount(item.getAmount() - 1);
                } else {
                    item.setAmount(0);
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0.5f);
            }

            HashMap<Integer, ItemStack> items = player.getInventory().addItem(ItemBuilder.getSpawner(entityType.name()));
            player.updateInventory();
            if (!items.isEmpty()) {
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    for (ItemStack leftover : items.values()) {
                        block.getWorld().dropItem(block.getLocation().add(0.5, 0.5, 0.5), leftover);
                    }
                }, 1);
            }

            player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 0.5f, 0);
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 0.5f, 0);
                player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 0.5f, 0);
            }, 10);
        }, 20L * Language.PICKUP_DELAY.toInt());
    }

}
