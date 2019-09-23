package me.tiagodeveloping.wars.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;

import me.tiagodeveloping.wars.ConfigManager;

public class DefineStartingCages {
	
	@SuppressWarnings("unchecked")
	public void setStartingCage(Location loc, int map) {
		ArrayList<HashMap<String, Integer>> configList = (ArrayList<HashMap<String, Integer>>) ConfigManager.startingCagesConfig.get("startingCages");
		HashMap<String, Integer> startingCageDataMap = new HashMap<>();
		startingCageDataMap.put("x", loc.getBlockX());
		startingCageDataMap.put("y", loc.getBlockY());
		startingCageDataMap.put("z", loc.getBlockZ());
		startingCageDataMap.put("map", map);
		
		configList.add(startingCageDataMap);
		
		ConfigManager.startingCagesConfig.set("startingCages", configList);
	}
	
}
