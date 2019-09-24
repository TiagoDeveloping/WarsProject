package me.tiagodeveloping.wars.arena;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;

import me.tiagodeveloping.wars.arenaCages.Cage;

public class Arena {

	private static String arenaName;
	private static ArrayList<UUID> playersInGame;
	private static Location lobbySpawnLocation;
	private static ArrayList<Cage> arenaCages;
	
	public Arena(String name, ArrayList<UUID> players, ArrayList<Cage> cages, Location lobbyLoc) {
		arenaName = name;
		playersInGame = players;
		lobbySpawnLocation = lobbyLoc;
		arenaCages = cages;
	}
	
//	@SuppressWarnings("unchecked")
	public ArrayList<UUID> getPlayersInGame(String name) {
		//return (ArrayList<UUID>) ArenaConfigManager.arenaRegisterConfig.getList("arenas." + name + ".players");
		return playersInGame;
	}
	
	public static void writeToConfig() {
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".players", playersInGame);
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".x", lobbySpawnLocation.getBlockX());
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".y", lobbySpawnLocation.getBlockY());
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".z", lobbySpawnLocation.getBlockZ());
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".world", lobbySpawnLocation.getWorld().getName());
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".spawnCages", arenaCages);
	}
	
//	@SuppressWarnings("unchecked")
	public void addPlayer(String arenaName, UUID playerUid) {
//		ArrayList<UUID> uidList = (ArrayList<UUID>) ArenaConfigManager.arenaRegisterConfig.getList("arenas." + arenaName + ".players");
//		uidList.add(playerUid);
//		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".players", uidList);
		playersInGame.add(playerUid);
		return;
	}
	
	public void addSpawnCage(String arenaName, Cage cage) {
		arenaCages.add(cage);
//		ArrayList<Cage> cages = (ArrayList<Cage>) ArenaConfigManager.arenaRegisterConfig.get("arenas." + arenaName + ".spawnCages");
//		cages.add(cage);
//		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".spawnCages", cages);
//		HashMap<String, String> cageData = new HashMap<String, String>();
//		Location loc = cage.getLocation();
//		cageData.put("name", cage.getName());
//		cageData.put("x", loc.getBlockX() + "");
//		cageData.put("x", loc.getBlockX() + "");
//		cageData.put("x", loc.getBlockX() + "");
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cage> getSpawnCages(String arenaName) {
		return (ArrayList<Cage>) ArenaConfigManager.arenaRegisterConfig.getList("arenas." + arenaName + ".spawnCages");
	}
	
	@SuppressWarnings("unchecked")
	public void removeSpawnCage(Cage spawnCage) {
		ArrayList<Cage> cageList = (ArrayList<Cage>) ArenaConfigManager.arenaRegisterConfig.getList("arenas." + arenaName + ".spawnCages");
		cageList.remove(spawnCage);
	}
	
}
