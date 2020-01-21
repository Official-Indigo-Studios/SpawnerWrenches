package ltd.indigostudios.spawnerwrenches.utils;

import org.bukkit.entity.EntityType;

public class Utils {

    /**
     * Check to see if an entity type exists
     * @param entityName The entity type to check
     * @return <code>true</code> if the entity type exits; <code>false</code> if the entity type does not exist
     */
    public static boolean entityExists(String entityName) {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.name().equals(entityName)) {
                return true;
            }
        }

        return false;
    }

}
