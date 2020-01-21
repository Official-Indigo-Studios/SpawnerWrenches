package ltd.indigostudios.spawnerwrenches.commands;

import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.utils.Text;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

public abstract class BaseCommand implements CommandExecutor {

    private String name, description, permission, permissionMessage;

    public BaseCommand setName(String name) {
        this.name = name;
        return this;
    }

    public BaseCommand setDescription(String description) {
        this.description = description;
        return this;
    }

    public BaseCommand setPermission(String permission) {
        this.permission = permission;
        setPermissionMessage(Text.colour(Language.NO_PERMISSION.toString()));
        return this;
    }

    public BaseCommand setPermissionMessage(String permissionMessage) {
        this.permissionMessage = permissionMessage;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public String getPermissionMessage() {
        return permissionMessage;
    }

    public TabCompleter getTabCompleter() {
        return null;
    }
}
