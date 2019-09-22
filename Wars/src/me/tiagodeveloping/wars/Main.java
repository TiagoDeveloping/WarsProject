package me.tiagodeveloping.wars;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main mainClass;
	
	public void onEnable() {
		try {
			mainClass = this;
			
			registerCommands();
//			regiserMainRunnable();
			
			System.out.println("Wars has started succesfully!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onDisable() {
		
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
