package me.tiagodeveloping.wars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

	private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Wars");
	
	//Creating variables
	public static FileConfiguration generatorConfig;
	public static File generatorConfigFile;
	
	public void declareConfigFiles() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
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
			e.printStackTrace();
		}
	}
	
}