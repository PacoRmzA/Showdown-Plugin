package dev.TheCubicJedi.showdownPlugin.commands;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.TheCubicJedi.showdownPlugin.App;

public class GetTeammatesCommand implements CommandExecutor {
    private App plugin;

    public GetTeammatesCommand(App plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equals("getplayers")) {
                if (args.length == 1) {
                    Set<UUID> teamIds = plugin.getTeamIds(Integer.parseInt(args[0]));
                    if (!teamIds.isEmpty()) {
                        StringBuilder teammates = new StringBuilder();
                        Iterator<UUID> it = teamIds.iterator();
                        String oneTeammate = Bukkit.getOfflinePlayer(it.next()).getName();
                        while (it.hasNext()) {
                            teammates.append(Bukkit.getOfflinePlayer(it.next()).getName()).append(", ");
                        }
                        teammates.append(oneTeammate);
                        p.sendMessage(ChatColor.GREEN + "The players in team " + args[0] + " are: "
                                + teammates.toString() + ".");
                    } else {
                        p.sendMessage(ChatColor.RED + "Could not find players in team " + args[0] + ".");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Correct command usage: /getplayers <team>");
                }
            }
            return true;
        }
        return false;
    }

}