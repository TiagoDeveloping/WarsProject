package me.tiagodeveloping.wars;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main mainClass;
	
	private ConfigManager configManager;
	
	public void onEnable() {
		saveConfig();
		try {
			
			mainClass = this;
			
			//Register stuff
			registerCommands();
//			regiserMainRunnable();
			
			//Declare stuff
			configManager.declareConfigFiles();
			
			System.out.println("Wars has started succesfully!");
		} catch(Exception e) {
			e.printStackTrace();
		}
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
