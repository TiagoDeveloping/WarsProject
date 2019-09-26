package me.tiagodeveloping.wars;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import me.tiagodeveloping.wars.Generators.GeneratorListener;
import me.tiagodeveloping.wars.Generators.GeneratorManager;
import me.tiagodeveloping.wars.arena.ArenaConfigManager;

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
			
			//Declare configs
				ConfigManager.declareGeneratorConfigFile();
				ConfigManager.declareStartingCageConfigFile();
			
				ArenaConfigManager.declareGeneratorConfigFile();
				
			
			//Enable generators
			GeneratorManager.enableRegisteredGenerators();
			
			//Save config
			
			System.out.println("Wars has started succesfully!");
	}
	
	public void onDisable() {
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
