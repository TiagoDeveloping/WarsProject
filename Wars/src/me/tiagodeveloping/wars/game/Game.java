package me.tiagodeveloping.wars.game;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.tiagodeveloping.wars.arena.Arena;
import me.tiagodeveloping.wars.arena.ArenaConfigManager;
import me.tiagodeveloping.wars.arenaCages.Cage;

public class Game {

	public ArrayList<UUID> players;
	public Arena arena;
	public String gameName;
	public ArrayList<Cage> startingCages;
	
	@SuppressWarnings("unchecked")
	public Game(String arenaName) {
		ArrayList<UUID> players = (ArrayList<UUID>) ArenaConfigManager.arenaRegisterConfig.get("arenas." + arenaName + "players");
		ArrayList<Cage> cages = (ArrayList<Cage>) ArenaConfigManager.arenaRegisterConfig.get("arenas." + arenaName + "cages");
		double x = ArenaConfigManager.arenaRegisterConfig.getDouble("arenas." + arenaName + "x");
		double y = ArenaConfigManager.arenaRegisterConfig.getDouble("arenas." + arenaName + "y");
		double z = ArenaConfigManager.arenaRegisterConfig.getDouble("arenas." + arenaName + "z");
		String worldName = ArenaConfigManager.arenaRegisterConfig.getString("arenas." + arenaName + "world");
		Location arenaLocation = new Location(Bukkit.getWorld(worldName), x, y, z);
		this.arena = new Arena(arenaName, players, cages, arenaLocation);
	}
	
	
	
	public void addPlayer(UUID playerUid) {
		
	}
	
	public void removePlayer(UUID playerUid) {
		
	}
	
	public void start(Arena arena) {
		Countdown.startCountdown(5);
	}
	
}
