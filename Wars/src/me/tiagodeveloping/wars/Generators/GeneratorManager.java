package me.tiagodeveloping.wars.Generators;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
	 * 		5 = worldId
	 * 		6 = name
	 */
	
	GeneratorTypeManager gTypeManager = new GeneratorTypeManager();

	//Main Main;
	
	@SuppressWarnings("unchecked")
	public void registerGenerator(GeneratorType type, Location generatorLocation, String generatorName) {
		int generator = gTypeManager.selectGeneratorType(type);
		ArrayList<ArrayList<String>> configList = (ArrayList<ArrayList<String>>) ConfigManager.generatorConfig.get("generators");
		if (generator == 3) {
			throw new IllegalArgumentException();
		} else if (generator == 0) {
			ArrayList<String> configData = new ArrayList<String>();
			IronGenerator.declareGenerator(generatorLocation);
			configData.add(0, IronGenerator.scheduler + "");
			configData.add(1, gTypeManager.selectGeneratorType(type) + "");
			configData.add(2, generatorLocation.getBlockX() + "");
			configData.add(3, generatorLocation.getBlockY() + "");
			configData.add(4, generatorLocation.getBlockZ() + "");
			String worldId = generatorLocation.getWorld().getName();
			configData.add(5, worldId + "");
			configData.add(6, generatorName);
			configList.add(configData);
		} else if (generator == 1) {
			ArrayList<String> configData = new ArrayList<String>();
			GoldGenerator.declareGenerator(generatorLocation);
			configData.add(0, GoldGenerator.scheduler + "");
			configData.add(1, gTypeManager.selectGeneratorType(type) + "");
			configData.add(2, generatorLocation.getBlockX() + "");
			configData.add(3, generatorLocation.getBlockY() + "");
			configData.add(4, generatorLocation.getBlockZ() + "");
			String worldId = generatorLocation.getWorld().getName();
			configData.add(5, worldId + "");
			configData.add(6, generatorName);
			configList.add(configData);
		} else if (generator == 2) {
			ArrayList<String> configData = new ArrayList<String>();
			DiamondGenerator.declareGenerator(generatorLocation);
			configData.add(0, DiamondGenerator.scheduler + "");
			configData.add(1, gTypeManager.selectGeneratorType(type) + "");
			configData.add(2, generatorLocation.getBlockX() + "");
			configData.add(3, generatorLocation.getBlockY() + "");
			configData.add(4, generatorLocation.getBlockZ() + "");
			String worldId = generatorLocation.getWorld().getName();
			configData.add(5, worldId + "");
			configData.add(6, generatorName);
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
	public static void enableRegisteredGenerators() {
		ArrayList<ArrayList<String>> configData = (ArrayList<ArrayList<String>>) ConfigManager.generatorConfig.get("generators");
		
		int x, y, z;
		String uuid;
		int i = 0;
		for (ArrayList<String> list : configData) {
			x = Integer.parseInt(list.get(2));
			y = Integer.parseInt(list.get(3));
			z = Integer.parseInt(list.get(4));
			uuid = list.get(5);
			Location loc = new Location(Bukkit.getWorld(uuid), x, y, z);
			
			Sign sign = (Sign) loc.getBlock().getState();
			sign.setLine(2, "Level " + 1);
			sign.update();
			
			int delay;
			
			Material b = loc.subtract(0, 1, 0).getBlock().getType();
			
			ItemStack item;
			
			if (b == Material.IRON_BLOCK) {
				delay = IronGenerator.ironGenDelay;
				item = IronGenerator.ironToken();
			} else if (b == Material.GOLD_BLOCK) {
				delay = GoldGenerator.goldGenDelay;
				item = GoldGenerator.ironToken();
			} else {
				delay = DiamondGenerator.diamondGenDelay;
				item = DiamondGenerator.ironToken();
			}
			
			int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.mainClass, new Runnable() {
				
				@Override
				public void run() {
					loc.getWorld().dropItem(loc.add(0.5,0.75,0.5), item);
					loc.subtract(0.5,0.75,0.5);
					
				}
				
			}, 100, delay);
			list.set(0, task + "");
			configData.set(i, list);
			i++;
		}
		ConfigManager.generatorConfig.set("generators", configData);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteGenerator(Location loc, Player p) {
		try {
			ArrayList<ArrayList<String>> configData = (ArrayList<ArrayList<String>>) ConfigManager.generatorConfig.get("generators");
			ArrayList<String> generator = configData.get(getGeneratorIndexByLocation(loc));
			Bukkit.getScheduler().cancelTask(Integer.parseInt(generator.get(0)));
			configData.remove(generator);
			ConfigManager.generatorConfig.set("generators", configData);
			loc.getBlock().setType(Material.AIR);
			loc.subtract(0, 1, 0).getBlock().setType(Material.AIR);
			
			try {
				ConfigManager.generatorConfig.save(ConfigManager.generatorConfigFile);
			} catch (IOException e) {
				p.sendMessage("Could not save the generators.yml file!");
				e.printStackTrace();
			}
			
			p.sendMessage(ChatColor.GREEN + "The generator has been removed!");
		} catch(ArrayIndexOutOfBoundsException e) {
			p.sendMessage(ChatColor.RED + "You have to be standing on a generator to delete it!");
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
		
		ArrayList<ArrayList<String>> configList = (ArrayList<ArrayList<String>>) ConfigManager.generatorConfig.get("generators");
		int index = 0;
		
		int dIndex = -1;
		//Bukkit.broadcastMessage(loc.toString());
		for (ArrayList<String> cData : configList) {
			cX = Integer.parseInt(cData.get(2));
			cY = Integer.parseInt(cData.get(3));
			cZ = Integer.parseInt(cData.get(4));
			
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
		} else if (line.contains("4")){
			lvl = 4;
		} else {
			throw new IllegalArgumentException("The generator is corrupted!");
		}
		return lvl;
	}
	
}
