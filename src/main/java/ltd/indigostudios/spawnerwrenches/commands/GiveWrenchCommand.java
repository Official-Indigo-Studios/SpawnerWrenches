package ltd.indigostudios.spawnerwrenches.commands;

import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.utils.ItemBuilder;
import ltd.indigostudios.spawnerwrenches.utils.Text;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class GiveWrenchCommand extends BaseCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length == 2) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    if (StringUtils.isNumeric(args[1])) {
                        sender.sendMessage(Text.colour(Language.WRENCH_GIVEN.toString().replace("{0}", args[0]).replace("{1}", args[1])));
                        Bukkit.getPlayer(args[0]).getInventory().addItem(ItemBuilder.getSpawnerWrench(Integer.parseInt(args[1])));
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
