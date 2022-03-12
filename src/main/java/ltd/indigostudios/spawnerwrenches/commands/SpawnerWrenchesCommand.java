package ltd.indigostudios.spawnerwrenches.commands;

import ltd.indigostudios.spawnerwrenches.Main;
import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.utils.Text;
import ltd.indigostudios.spawnerwrenches.utils.YML;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public class SpawnerWrenchesCommand extends BaseCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    // Reload language settings
                    YML config = new YML();
                    config.reload();
                    Main.getInstance().loadConfig(config);

                    // Reload command permission messages
                    Main.getInstance().getDescription().getCommands().keySet().forEach(name -> {
                        PluginCommand pCmd = Main.getInstance().getCommand(name);
                        if (pCmd == null) return;

                        pCmd.setPermissionMessage(Text.colour(Language.NO_PERMISSION.toString()));
                    });

                    sender.sendMessage(Text.colour(Language.RELOAD.toString()));
                } else if (args[0].equalsIgnoreCase("help")) {
                    help(sender);
                }
            } else {
                help(sender);
            }
        } else {
            sender.sendMessage(Text.colour(Language.NO_PERMISSION.toString()));
        }
        return true;
    }

    private void help(CommandSender sender) {
        sender.sendMessage(Text.colour(Language.HELP_MENU_HEADER.toString()));
        Language.HELP_MENU_CONTENTS.toList().forEach(help -> {
            sender.sendMessage(Text.colour(help));
        });
    }

}
