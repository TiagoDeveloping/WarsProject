package me.tiagodeveloping.wars.arena;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.tiagodeveloping.wars.Main;

public class ArenaConfigManager {

	private static Plugin plugin = Main.mainClass;
	
	public static File arenaRegisterFile;
	public static FileConfiguration arenaRegisterConfig;
	
	private static String arenaConfigFileName = "arenas.yml";
	
	public static void declareGeneratorConfigFile() {
//		if (!plugin.getDataFolder().exists()) {
//			plugin.getDataFolder().mkdir();
//		}
		
		arenaRegisterFile = new File(plugin.getDataFolder(), arenaConfigFileName);

		if (!arenaRegisterFile.exists()) {
			try {
				arenaRegisterFile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The " + arenaConfigFileName + " file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the " + arenaConfigFileName + " file");
			}
		}

		arenaRegisterConfig = YamlConfiguration.loadConfiguration(arenaRegisterFile);
		
		if (!(arenaRegisterConfig.contains("arenas"))) {
			arenaRegisterConfig.createSection("arenas");
		}
		
		try {
			arenaRegisterConfig.save(arenaRegisterFile);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("Could not save file: " + arenaConfigFileName);
			e.printStackTrace();
		}
	}
	
}
