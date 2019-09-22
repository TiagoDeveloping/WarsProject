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
	public File generatorConfigFile;
	public static FileConfiguration generatorConfig;
	
	public void declareConfigFiles() {
			if (!(plugin.getDataFolder().exists())) {
				plugin.getDataFolder().mkdir();
			}
			
			generatorConfigFile = new File(plugin.getDataFolder(), "generators.yml");
			
			if (!(generatorConfigFile.exists())) {
				try {
					generatorConfigFile.createNewFile();
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The generators config file has been created!");
				} catch (IOException e) {
					plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "This is not a plugin error, but a USER error." + ChatColor.GREEN + " To fix:  try"  + ChatColor.AQUA + " restarting " + ChatColor.GREEN + "the server.");
				}
			}
			
			generatorConfig = YamlConfiguration.loadConfiguration(generatorConfigFile);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The generators config file has been initialized!!");
			
	}
	
}
