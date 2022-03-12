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
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(Text.colour(Language.NO_PERMISSION.toString()));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(Text.colour(Language.COMMAND_USAGE_WRENCH.toString()));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Text.colour(Language.PLAYER_NOT_FOUND.toString().replace("{0}", args[0])));
            return true;
        }

        if (!StringUtils.isNumeric(args[1])) {
            sender.sendMessage(Text.colour(Language.INVALID_NUMERICAL_VALUE.toString()));
            return true;
        }

        sender.sendMessage(Text.colour(Language.WRENCH_GIVEN.toString().replace("{0}", args[0]).replace("{1}", args[1])));
        if (args.length >= 3) {
            if (StringUtils.isNumeric(args[2])) {
                target.getInventory().addItem(ItemBuilder.getSpawnerWrench(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
            } else {
                sender.sendMessage(Text.colour(Language.INVALID_NUMERICAL_VALUE.toString()));
            }
        } else {
            target.getInventory().addItem(ItemBuilder.getSpawnerWrench(Integer.parseInt(args[1]), Language.DEFAULT_WRENCH_USES.toInt()));
        }
        return true;
    }
}
