package me.tiagodeveloping.wars.arenaCages;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;

import me.tiagodeveloping.wars.arena.ArenaConfigManager;

public class Cage {

	String arenaName;
	String cageName;
	Location loc;
	
	public Cage(String arenaName, String cageName, Location loc) {
		this.arenaName = arenaName;
		this.cageName = cageName;
		this.loc = loc;
	}
	
	@SuppressWarnings("unchecked")
	public void registerToConfig() {
		ArrayList<HashMap<String, String>> cages = (ArrayList<HashMap<String, String>>) ArenaConfigManager.arenaRegisterConfig.get("arenas." + arenaName + ".spawnCages");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("world", loc.getWorld().getName());
		map.put("x", loc.getBlockX() + "");
		map.put("y", loc.getBlockY() + "");
		map.put("z", loc.getBlockZ() + "");
		map.put("name", cageName);
		cages.add(map);
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".spawnCages", cages);
	}
	
	public String getName() {
		return cageName;
	}
	
	public String getArena() {
		return arenaName;
	}
	
	@SuppressWarnings("unchecked")
	public void delete() {
		ArrayList<Cage> cages = (ArrayList<Cage>) ArenaConfigManager.arenaRegisterConfig.get("arenas." + arenaName + ".spawnCages");
		cages.remove(this);
		ArenaConfigManager.arenaRegisterConfig.set("arenas." + arenaName + ".spawnCages", cages);
	}
	
	public Location getLocation() {
		return this.loc;
	}
	
}
