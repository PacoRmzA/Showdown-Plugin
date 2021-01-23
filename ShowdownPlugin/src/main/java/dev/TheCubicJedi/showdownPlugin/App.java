package dev.TheCubicJedi.showdownPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.TheCubicJedi.showdownPlugin.commands.AddTeammateCommand;
import dev.TheCubicJedi.showdownPlugin.commands.GetTeammatesCommand;
import dev.TheCubicJedi.showdownPlugin.commands.RemovePlayerCommand;
import dev.TheCubicJedi.showdownPlugin.listeners.MovementListener;
import dev.TheCubicJedi.showdownPlugin.listeners.PlayerClickListener;
import dev.TheCubicJedi.showdownPlugin.listeners.PlayerRespawnListener;
public class App extends JavaPlugin {
    private HashMap<UUID, Integer> teams = new HashMap<UUID, Integer>();
    public HashMap<UUID, Integer> modes = new HashMap<UUID, Integer>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MovementListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerClickListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
        getCommand("addplayer").setExecutor(new AddTeammateCommand(this));
        getCommand("removeplayer").setExecutor(new RemovePlayerCommand(this));
        getCommand("getplayers").setExecutor(new GetTeammatesCommand(this));
    }

    @Override
    public void onDisable() {}

    public HashMap<UUID, Integer> getTeams() {
        return this.teams;
    }

    public HashMap<UUID, Integer> getModes() {
        return this.modes;
    }

    public Set<UUID> getTeamIds(int team) {
        Set<UUID> teamIds = new HashSet<UUID>();
        for (Map.Entry<UUID, Integer> set : teams.entrySet()) {
            if (set.getValue() == team) teamIds.add(set.getKey());
        }
        return teamIds;
    }

    public Set<UUID> getPlayerIds() {
        return teams.keySet();
    }

    public void addPlayer(Player player, int team) {
        teams.put(player.getUniqueId(), team);
        modes.put(player.getUniqueId(), 1);
    }

    public void removePlayer(Player player) {
        teams.remove(player.getUniqueId());
    }
}