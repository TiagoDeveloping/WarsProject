package me.tiagodeveloping.wars.Generators;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import me.tiagodeveloping.wars.ConfigManager;
import me.tiagodeveloping.wars.Main;

public class GeneratorManager {

	/**
	 * 
	 * Generator Configuration Array Index:
	 * 		0 = taskId
	 * 		1 = type
	 * 		2 = x
	 * 		3 = y
	 * 		4 = z
	 * 
	 */
	
	GeneratorTypeManager gTypeManager = new GeneratorTypeManager();

	Main Main;
	
	public void enableRegisteredGenerators() {
		
	}
	
	@SuppressWarnings("unchecked")
	public void registerGenerator(GeneratorType type, Location generatorLocation, String generatorName) {
		int generator = gTypeManager.selectGeneratorType(type);
		ArrayList<ArrayList<Integer>> configList = (ArrayList<ArrayList<Integer>>) ConfigManager.generatorConfig.get("generators");
		if (generator == 3) {
			throw new IllegalArgumentException();
		} else if (generator == 0) {
			ArrayList<Integer> configData = new ArrayList<Integer>();
			IronGenerator.declareGenerator(generatorLocation);
			configData.add(0, IronGenerator.scheduler);
			configData.add(1, gTypeManager.selectGeneratorType(type));
			configData.add(2, generatorLocation.getBlockX());
			configData.add(3, generatorLocation.getBlockY());
			configData.add(4, generatorLocation.getBlockZ());
			configList.add(configData);
		} else if (generator == 1) {
			ArrayList<Integer> configData = new ArrayList<Integer>();
			GoldGenerator.declareGenerator(generatorLocation);
			configData.add(0, GoldGenerator.scheduler);
			configData.add(1, gTypeManager.selectGeneratorType(type));
			configData.add(2, generatorLocation.getBlockX());
			configData.add(3, generatorLocation.getBlockY());
			configData.add(4, generatorLocation.getBlockZ());
			configList.add(configData);
		} else if (generator == 2) {
			ArrayList<Integer> configData = new ArrayList<Integer>();
			DiamondGenerator.declareGenerator(generatorLocation);
			configData.add(0, DiamondGenerator.scheduler);
			configData.add(1, gTypeManager.selectGeneratorType(type));
			configData.add(2, generatorLocation.getBlockX());
			configData.add(3, generatorLocation.getBlockY());
			configData.add(4, generatorLocation.getBlockZ());
			configList.add(configData);
		}
		ConfigManager.generatorConfig.set("generators", configList);
		try {
			ConfigManager.generatorConfig.save(ConfigManager.generatorConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static int getGeneratorIndexByLocation(Location loc) {
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		int cX;
		int cY;
		int cZ;
		
		ArrayList<ArrayList<Integer>> configList = (ArrayList<ArrayList<Integer>>) ConfigManager.generatorConfig.get("generators");
		int index = 0;
		
		int dIndex = -1;
		Bukkit.broadcastMessage(loc.toString());
		for (ArrayList<Integer> cData : configList) {
			cX = cData.get(2);
			cY = cData.get(3);
			cZ = cData.get(4);
			
			if (x == cX && y == cY && z == cZ) {
				dIndex = index;
			} else {
				index++;
			}
		}
		return dIndex;
	}
	
	public static int getGeneratorLevel(Sign sign) {
		int lvl = 1;
		String line = sign.getLine(2);
		if (line.contains("1")) {
			lvl = 1;
		} else if (line.contains("2")) {
			lvl = 2;
		} else if (line.contains("3")) {
			lvl = 3;
		} else {
			throw new IllegalArgumentException();
		}
		return lvl;
	}
	
}
