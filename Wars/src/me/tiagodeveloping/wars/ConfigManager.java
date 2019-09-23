package me.tiagodeveloping.wars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

	private static Plugin plugin = Main.mainClass;
	
	//Creating variables
	public static FileConfiguration generatorConfig;
	public static File generatorConfigFile;

	public static FileConfiguration startingCagesConfig;
	public static File StartingCagesFile;
	
	public static void declareGeneratorConfigFile() {
//		if (!plugin.getDataFolder().exists()) {
//			plugin.getDataFolder().mkdir();
//		}
		
		generatorConfigFile = new File(plugin.getDataFolder(), "generators.yml");

		if (!generatorConfigFile.exists()) {
			try {
				generatorConfigFile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The generators.yml file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the generators.yml file");
			}
		}

		generatorConfig = YamlConfiguration.loadConfiguration(generatorConfigFile);
		generatorConfig.createSection("generators");
		try {
			generatorConfig.save(generatorConfigFile);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("Could not save file: generators.yml");
			e.printStackTrace();
		}
	}
	
	public static void declareStartingCageConfigFile() {
//		if (!plugin.getDataFolder().exists()) {
//			plugin.getDataFolder().mkdir();
//		}
		
		StartingCagesFile = new File(plugin.getDataFolder(), "startingBlocks.yml");

		if (!StartingCagesFile.exists()) {
			try {
				StartingCagesFile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The startingBlocks.yml file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the startingBlocks.yml file");
			}
		}

		startingCagesConfig = YamlConfiguration.loadConfiguration(StartingCagesFile);
		startingCagesConfig.createSection("startingCages");
		try {
			startingCagesConfig.save(StartingCagesFile);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("Could not save file: startingBlocks.yml");
			e.printStackTrace();
		}
	}
	
}