package ltd.indigostudios.spawnerwrenches.commands;

import ltd.indigostudios.spawnerwrenches.api.Language;
import ltd.indigostudios.spawnerwrenches.utils.Text;
import ltd.indigostudios.spawnerwrenches.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnerCommand extends BaseCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        if (!player.hasPermission(getPermission())) {
            player.sendMessage(Text.colour(Language.NO_PERMISSION.toString()));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Text.colour(Language.SPAWNER_INVALID_ENTITY.toString()));
            return true;
        }

        Block block = player.getTargetBlock(null, 5);
        if (block.getType() == Material.SPAWNER) {
            String entityName = Text.convertUnformattedString(args[0]);
            if (Utils.entityExists(args[0].toUpperCase())) {
                CreatureSpawner spawner = (CreatureSpawner) block.getState();
                spawner.setSpawnedType(EntityType.valueOf(args[0].toUpperCase()));
                spawner.update();

                player.sendMessage(Text.colour(Language.SPAWNER_TYPE_SET.toString().replace("{0}", entityName)));
            } else {
                player.sendMessage(Text.colour(Language.SPAWNER_INVALID_ENTITY.toString().replace("{0}", entityName)));
            }
        } else {
            player.sendMessage(Text.colour(Language.SPAWNER_NOT_FOUND.toString()));
        }
        return true;
    }
}
