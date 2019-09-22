package me.tiagodeveloping.wars;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import me.tiagodeveloping.wars.Generators.GeneratorListener;

public class Main extends JavaPlugin {

	public static Main mainClass;
	
	public void onEnable() {
		saveConfig();
			mainClass = this;
			
			//Register listeners
			this.getServer().getPluginManager().registerEvents(new GeneratorListener(), this);
			
			//Register stuff
			registerCommands();
//			regiserMainRunnable();
			
			//Declare stuff
				new ConfigManager().declareConfigFiles();
			
			System.out.println("Wars has started succesfully!");
	}
	
	public void onDisable() {
		try {
			ConfigManager.generatorConfig.save(ConfigManager.generatorConfigFile);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Wars] Error, Wars was not able to save the configuragion file!");
		}
		saveConfig();
		System.out.println("Wars has succesfully been disabled!");
	}
	
	private void registerCommands() {
		this.getCommand("wars").setExecutor((CommandExecutor) new warsCommand());
	}
	
//	private void regiserMainRunnable() {
//		new BukkitRunnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//	}
	
}
