package dev.TheCubicJedi.showdownPlugin.listeners;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import dev.TheCubicJedi.showdownPlugin.App;

public class MovementListener implements Listener {
    private App plugin;
    private HashMap<UUID, Integer> teams;
    private HashMap<UUID, Integer> modes;

    public MovementListener(App plugin) {
        this.plugin = plugin;
        this.teams = plugin.getTeams();
        this.modes = plugin.getModes();
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e) {
        for (Player p : e.getPlayer().getWorld().getPlayers()) {
            if ((p.getInventory().getItemInMainHand().getType() == Material.COMPASS || p.getInventory().getItemInOffHand().getType() == Material.COMPASS) && p != null && plugin.getPlayerIds().contains(p.getUniqueId())) {
                int mode = modes.get(p.getUniqueId());
                int teamTracking = teams.get(p.getUniqueId());;
                ItemStack item;
                ChatColor color = ChatColor.GREEN;
                if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) item = p.getInventory().getItemInMainHand();
                else item = p.getInventory().getItemInOffHand();
                if (mode == 1 && getClosestTeammate(p, (teamTracking + 1) % 2) != null) {
                    teamTracking = (teams.get(p.getUniqueId()) + 1) % 2;
                    color = ChatColor.RED;
                }
                Player closest = getClosestTeammate(p, teamTracking);
                p.sendMessage(color + closest.getName());
                //p.setCompassTarget(closest.getLocation());
                CompassMeta meta = (CompassMeta) item.getItemMeta();
                meta.setLodestoneTracked(false);
                meta.setLodestone(closest.getLocation());
                item.setItemMeta(meta);
            }  
        }
    }

    private Player getClosestTeammate(Player p, int team) {
        Set<UUID> teammates = plugin.getTeamIds(team);
        Location playerLocation = p.getLocation();
        Player closestTeammate = null;
        double closestDistanceSquared = Double.MAX_VALUE;
        for (UUID player : teammates) {
            if (p.getUniqueId() != Bukkit.getPlayer(player).getUniqueId() && p.getWorld() == Bukkit.getPlayer(player).getWorld() && Bukkit.getPlayer(player).getGameMode() == GameMode.SURVIVAL) {
                double distanceSquared = p.getLocation().distanceSquared(playerLocation);
                if (distanceSquared <= closestDistanceSquared) {
                    closestDistanceSquared = distanceSquared;
                    closestTeammate = Bukkit.getPlayer(player);
                }
            }
        }
        if (closestTeammate != null) return closestTeammate;
        else return p;
    }
}