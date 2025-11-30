package plugin.sample;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LevelUpCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
    if(sender instanceof Player player) {
      if(args.length == 1) {
        player.setLevel(Integer.parseInt(args[0]));
      } else {
        player.sendMessage("Nooooooooo!!!");
      }
    }
    return false;
  }
}
