package dev.TheCubicJedi.showdownPlugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import dev.TheCubicJedi.showdownPlugin.App;

public class PlayerRespawnListener implements Listener {
    private App plugin;

    public PlayerRespawnListener(App plugin) {
        this.plugin = plugin;
    }

    // When a hunter respawns, a compass is automatically added to their inventory.
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (plugin.getPlayerIds().contains(p.getUniqueId())) {
            p.getInventory().addItem(new ItemStack(Material.COMPASS));
            p.sendMessage(ChatColor.GREEN + "You died. Have a new compass.");
        }
    }
}