package ltd.indigostudios.spawnerwrenches.commands;

import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.utils.ItemBuilder;
import ltd.indigostudios.spawnerwrenches.utils.Text;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveWrenchCommand extends BaseCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length >= 2) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    if (StringUtils.isNumeric(args[1])) {
                        sender.sendMessage(Text.colour(Language.WRENCH_GIVEN.toString().replace("{0}", args[0]).replace("{1}", args[1])));
                        Player player = Bukkit.getPlayer(args[0]);
                        if (args.length >= 3) {
                            if (StringUtils.isNumeric(args[2])) {
                                player.getInventory().addItem(ItemBuilder.getSpawnerWrench(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                            } else {
                                sender.sendMessage(Text.colour(Language.INVALID_NUMERICAL_VALUE.toString()));
                            }
                        } else {
                            player.getInventory().addItem(ItemBuilder.getSpawnerWrench(Integer.parseInt(args[1]), Language.DEFAULT_WRENCH_USES.toInt()));
                        }
                    } else {
                        sender.sendMessage(Text.colour(Language.INVALID_NUMERICAL_VALUE.toString()));
                    }
                } else {
                    sender.sendMessage(Text.colour(Language.PLAYER_NOT_FOUND.toString().replace("{0}", args[0])));
                }
            } else {
                sender.sendMessage(Text.colour(Language.COMMAND_USAGE_WRENCH.toString()));
            }
        } else {
            sender.sendMessage(Text.colour(Language.NO_PERMISSION.toString()));
        }
        return true;
    }
}
