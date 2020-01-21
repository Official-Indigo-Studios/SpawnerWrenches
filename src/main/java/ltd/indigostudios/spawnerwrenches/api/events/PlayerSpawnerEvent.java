package ltd.indigostudios.spawnerwrenches.api.events;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class PlayerSpawnerEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();

    private Player player;
    private Block block;
    private EntityType entityType;
    private boolean isCancelled;

    public PlayerSpawnerEvent(Player player, Block block, EntityType entityType) {
        this.player = player;
        this.block = block;
        this.entityType = entityType;
        this.isCancelled = false;
    }

    public Player getPlayer() {
        return player;
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
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

}
