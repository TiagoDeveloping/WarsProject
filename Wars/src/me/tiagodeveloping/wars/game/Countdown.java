package me.tiagodeveloping.wars.game;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.tiagodeveloping.wars.Main;

public class Countdown {

	private static Plugin plugin = Main.mainClass;
	private static int taskId;
	private static int secondsCounter = 0;
	
	public static void startCountdown(int timeInSeconds) {
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				secondsCounter++;
				if (secondsCounter == timeInSeconds) {
					Bukkit.getScheduler().cancelTask(taskId);
				}
			}
			
		}, 100, 20);
	}
	
}
