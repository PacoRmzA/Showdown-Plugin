package dev.TheCubicJedi.showdownPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.TheCubicJedi.showdownPlugin.App;

public class AddTeammateCommand implements CommandExecutor {
    private App plugin;

    public AddTeammateCommand(App plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equals("addplayer")) {
                if (args.length == 2) {
                    Player target = Bukkit.getServer().getPlayerExact(args[0]);
                    if (target != null) {
                        if (!plugin.getPlayerIds().contains(target.getUniqueId())) {
                            plugin.addPlayer(target, Integer.parseInt(args[1]));
                            target.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
                            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "[Server Broadcast] " + ChatColor.RED + 
                                target.getName() + ChatColor.GREEN + " is now in team " + args[1] + ".");
                        } else {
                            p.sendMessage(ChatColor.RED + target.getName() + "is already in a team.");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Could not find the player " + args[0] + ".");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Correct command usage: /addplayer <username> <team>");
                }
            }
            return true;
        }
        return false;
    }
}