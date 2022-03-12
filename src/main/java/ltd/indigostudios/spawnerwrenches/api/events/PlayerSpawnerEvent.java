package ltd.indigostudios.spawnerwrenches.api.events;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public abstract class PlayerSpawnerEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private final EntityType entityType;
    private boolean isCancelled;

    public PlayerSpawnerEvent(Player player, Block block, EntityType entityType) {
        super(player);
        this.block = block;
        this.entityType = entityType;
        this.isCancelled = false;
    }

    public Block getBlock() {
        return block;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public boolean isCancelled() {
        return false;
    }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

}
