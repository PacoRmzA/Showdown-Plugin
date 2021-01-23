package dev.TheCubicJedi.showdownPlugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.TheCubicJedi.showdownPlugin.App;


public class PlayerClickListener implements Listener {
    private App plugin;

    public PlayerClickListener(App plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCompassClick(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS || e.getPlayer().getInventory().getItemInOffHand().getType() == Material.COMPASS) {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                plugin.modes.replace(e.getPlayer().getUniqueId(), 0, 1);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Now tracking closest enemy player.");
            } else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                plugin.modes.replace(e.getPlayer().getUniqueId(), 1, 0);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Now tracking closest teammate.");
            }
        }
    }

}