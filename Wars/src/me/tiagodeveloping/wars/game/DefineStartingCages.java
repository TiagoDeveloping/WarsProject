package me.tiagodeveloping.wars.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;

import me.tiagodeveloping.wars.ConfigManager;

public class DefineStartingCages {
	
	@SuppressWarnings("unchecked")
	public void setStartingCage(Location loc, String mapName) {
		ArrayList<HashMap<String, String>> configList = (ArrayList<HashMap<String, String>>) ConfigManager.startingCagesConfig.get("startingCages");
		HashMap<String, String> startingCageDataMap = new HashMap<>();
		startingCageDataMap.put("x", loc.getBlockX() + "");
		startingCageDataMap.put("y", loc.getBlockY() + "");
		startingCageDataMap.put("z", loc.getBlockZ() + "");
		startingCageDataMap.put("name", mapName);
		
		configList.add(startingCageDataMap);
		
		ConfigManager.startingCagesConfig.set("startingCages", configList);
	}
	
}
