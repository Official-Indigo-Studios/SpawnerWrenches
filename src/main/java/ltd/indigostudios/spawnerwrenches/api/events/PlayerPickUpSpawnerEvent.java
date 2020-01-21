package ltd.indigostudios.spawnerwrenches.api.events;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class PlayerPickUpSpawnerEvent extends PlayerSpawnerEvent {

    public PlayerPickUpSpawnerEvent(Player player, Block block, EntityType entityType) {
        super(player, block, entityType);
    }
}
