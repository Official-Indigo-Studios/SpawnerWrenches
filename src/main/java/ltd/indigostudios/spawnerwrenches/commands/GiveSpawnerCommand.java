package ltd.indigostudios.spawnerwrenches.commands;

import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.utils.ItemBuilder;
import ltd.indigostudios.spawnerwrenches.utils.Text;
import ltd.indigostudios.spawnerwrenches.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GiveSpawnerCommand extends BaseCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length == 3) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    String entityName = Text.convertUnformattedString(args[1]);
                    if (Utils.entityExists(args[1].toUpperCase())) {
                        Player target = Bukkit.getPlayer(args[0]);

                        int amount = 1;
                        if (StringUtils.isNumeric(args[2])) {
                            amount = Integer.parseInt(args[2]);
                            ItemStack item = ItemBuilder.getSpawner(args[1]);
                            item.setAmount(amount);

                            sender.sendMessage(Text.colour(Language.SPAWNER_GIVEN.toString().replace("{0}", args[0]).replace("{1}", Integer.toString(amount)).replace("{2}", entityName)));
                            target.getInventory().addItem(item);
                        } else {
                            sender.sendMessage(Text.colour(Language.INVALID_NUMERICAL_VALUE.toString()));
                        }
                    } else {
                        sender.sendMessage(Text.colour(Language.SPAWNER_INVALID_ENTITY.toString().replace("{0}", entityName)));
                    }
                } else {

                    sender.sendMessage(Text.colour(Language.PLAYER_NOT_FOUND.toString().replace("{0}", args[0])));
                }
            } else {
                sender.sendMessage(Text.colour(Language.COMMAND_USAGE_SPAWNER.toString()));
            }
        } else {
            sender.sendMessage(Text.colour(Language.NO_PERMISSION.toString()));
        }
        return true;
    }

    @Override
    public TabCompleter getTabCompleter() {
        return (commandSender, command, label, args) -> {
            if (args.length == 2) {
                List<String> entities = new ArrayList<>();
                for (EntityType entityType : EntityType.values()) {
                    if (entityType.isAlive()) {
                        entities.add(entityType.name());
                    }
                }
                return entities;
            }
            return null;
        };
    }
}
