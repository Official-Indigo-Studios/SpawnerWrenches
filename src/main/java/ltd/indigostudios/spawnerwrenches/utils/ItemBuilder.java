package ltd.indigostudios.spawnerwrenches.utils;

import ltd.indigostudios.spawnerwrenches.api.Keys;
import ltd.indigostudios.spawnerwrenches.api.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    /**
     * Build an item stack
     * @param material The material to use in the item stack
     * @param customModelData The custom model data for the item stack
     * @param amount How many items should be in the item stack
     * @param name The display name of the item stack
     * @param description The lore of the item stack
     * @return The finished item stack
     */
    public static ItemStack buildItem(Material material, int customModelData, int amount, String name, List<String> description) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Text.colour(name));
        meta.setLore(Text.colourArray(description));
        if (customModelData > 0) {
            meta.setCustomModelData(customModelData);
        }
        item.setItemMeta(meta);

        return item;
    }
    /***
     * Get a spawner by entity name
     * @param entityName The name of the entity for the spawner
     * @return The item stack of the spawner
     */
    public static ItemStack getSpawner(String entityName) {
        ItemStack item = buildItem(
                Material.valueOf(Language.SPAWNER_ITEM_MATERIAL.toString()),
                Integer.parseInt(Language.SPAWNER_ITEM_MODEL.toString()),
                1,
                Language.SPAWNER_ITEM_NAME.toString().replace("{0}", Text.convertUnformattedString(entityName)),
                Language.SPAWNER_ITEM_LORE.toList()
        );

        // Use 1.14 persistent data holder to keep track of spawners
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(Keys.WRENCHES, PersistentDataType.STRING, entityName.toUpperCase());
            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Get a quantity of spawner wrenches
     * @param amount The amount of spawner wrenches to get
     * @return The item stack of the spawner
     */
    public static ItemStack getSpawnerWrench(int amount, int uses) {
        ItemStack item = buildItem(
                Material.valueOf(Language.WRENCH_ITEM_MATERIAL.toString()),
                Integer.parseInt(Language.WRENCH_ITEM_MODEL.toString()),
                amount,
                Language.WRENCH_ITEM_NAME.toString(),
                Language.WRENCH_ITEM_LORE.toList()
        );
        updateUses(item, uses);

        // Use 1.14 persistent data holder to keep track of wrenches
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(Keys.WRENCHES, PersistentDataType.STRING, "wrench");
            meta.getPersistentDataContainer().set(Keys.USES, PersistentDataType.INTEGER, uses);
            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Update the lore of an item to contain the correct uses
     * @param item The item to update the lore of
     * @param uses The number of uses
     */
    public static void updateUses(ItemStack item, int uses) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        List<String> lore = new ArrayList<>();
        for (String loreLine : Language.WRENCH_ITEM_LORE.toList()) {
            lore.add(loreLine.replace("{USES}", String.valueOf(uses)));
        }
        meta.setLore(Text.colourArray(lore));

        meta.getPersistentDataContainer().set(Keys.USES, PersistentDataType.INTEGER, uses);

        item.setItemMeta(meta);
    }

}
