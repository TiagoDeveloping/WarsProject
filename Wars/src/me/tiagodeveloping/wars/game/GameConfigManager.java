package me.tiagodeveloping.wars.game;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.tiagodeveloping.wars.Main;

public class GameConfigManager {

		private static Plugin plugin = Main.mainClass;
		
		public static File gameConfigFile;
		public static FileConfiguration gameConfig;
		
		private static String arenaConfigFileName = "games.yml";
		
		public static void declareGeneratorConfigFile() {
//			if (!plugin.getDataFolder().exists()) {
//				plugin.getDataFolder().mkdir();
//			}
			
			gameConfigFile = new File(plugin.getDataFolder(), arenaConfigFileName);

			if (!gameConfigFile.exists()) {
				try {
					gameConfigFile.createNewFile();
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The " + arenaConfigFileName + " file has been created");
				} catch (IOException e) {
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the " + arenaConfigFileName + " file");
				}
			}

			gameConfig = YamlConfiguration.loadConfiguration(gameConfigFile);
			
			if (!(gameConfig.contains("games"))) {
				gameConfig.createSection("games");
			}
			
			try {
				gameConfig.save(gameConfigFile);
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage("Could not save file: " + arenaConfigFileName);
				e.printStackTrace();
			}
		}
}