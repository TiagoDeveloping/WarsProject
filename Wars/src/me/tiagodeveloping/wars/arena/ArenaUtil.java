package me.tiagodeveloping.wars.arena;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ArenaUtil {

	@SuppressWarnings("unchecked")
	public static void teleportPlayerToArena(UUID playerUid, String arenaName) {
		double x = ArenaConfigManager.arenaRegisterConfig.getDouble("arenas." + arenaName + "x");
		double y = ArenaConfigManager.arenaRegisterConfig.getDouble("arenas." + arenaName + "y");
		double z = ArenaConfigManager.arenaRegisterConfig.getDouble("arenas." + arenaName + "z");
		String worldName = ArenaConfigManager.arenaRegisterConfig.getString("arenas." + arenaName + "world");
		Location arenaLocation = new Location(Bukkit.getWorld(worldName), x, y, z);
		ArrayList<UUID> players = (ArrayList<UUID>) ArenaConfigManager.arenaRegisterConfig.get("arenas." + arenaName + "players");
		players.add(playerUid);
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName, players);
		Bukkit.getPlayer(playerUid).teleport(arenaLocation);
	}
	
}
