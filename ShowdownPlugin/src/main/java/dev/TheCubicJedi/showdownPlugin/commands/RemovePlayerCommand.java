package dev.TheCubicJedi.showdownPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.TheCubicJedi.showdownPlugin.App;

public class RemovePlayerCommand implements CommandExecutor {
    private App plugin;

    public RemovePlayerCommand(App plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equals("removeplayer")) {
                if (args.length == 1) {
                    Player target = Bukkit.getServer().getPlayerExact(args[0]);
                    if (target != null) {
                        if (plugin.getPlayerIds().contains(target.getUniqueId())) {
                            plugin.removePlayer(target);
                            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "[Server Broadcast] " + ChatColor.RED + 
                                target.getName() + ChatColor.GREEN + " is no longer in a team.");
                        } else {
                            p.sendMessage(ChatColor.RED + target.getName() + " is not in a team.");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Could not find the player " + args[0] + ".");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Correct command usage: /removeplayer <username>");
                }
            }
            return true;
        }
        return false;
    }

}