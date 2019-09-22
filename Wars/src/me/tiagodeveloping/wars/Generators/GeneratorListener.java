package me.tiagodeveloping.wars.Generators;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.tiagodeveloping.wars.ConfigManager;
import me.tiagodeveloping.wars.Main;

public class GeneratorListener implements Listener {

	@SuppressWarnings("unchecked")
	@EventHandler
	public void generatorUpgradeEvent(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		
		if (!(e.getClickedBlock().getType() == Material.OAK_SIGN)) return;
		Sign sign = (Sign) e.getClickedBlock().getState();
		
		if (!(sign.getLine(3)).equals(ChatColor.GREEN + "Click to upgrade!")) return;
		
		Block genBase = e.getClickedBlock().getLocation().subtract(0, 1, 0).getBlock();
		
		if (genBase.getType() == Material.IRON_BLOCK) {
			
			int configIndex = GeneratorManager.getGeneratorIndexByLocation((e.getClickedBlock().getLocation()));
			if (configIndex == -1) {
				e.getPlayer().sendMessage("An error occured with your generator!");
				return;
			}
			ArrayList<ArrayList<Integer>> list = (ArrayList<ArrayList<Integer>>) ConfigManager.generatorConfig.get("generators");
			ArrayList<Integer> iList = list.get(configIndex);
			
			int targetGeneratorScheduleId = iList.get(0);
			int newLevel = GeneratorManager.getGeneratorLevel(sign) + 1;
			int newDelayTime = 100 / newLevel;
			
			e.getPlayer().sendMessage(targetGeneratorScheduleId + " is the scheduler id!");
			
			Bukkit.getServer().getScheduler().cancelTask(targetGeneratorScheduleId);
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, new Runnable() {
				
				@Override
				public void run() {
					e.getClickedBlock().getLocation().getWorld().dropItem(e.getClickedBlock().getLocation(), IronGenerator.ironToken());
					//ConfigManager.generatorConfig.set(arg0, arg1);
				}
				
			}, 100, newDelayTime);
			
			iList.set(0, task);
			list.set(configIndex, iList);
			ConfigManager.generatorConfig.set("generators", list);
			
			sign.setLine(2, "Level " + newLevel);
			sign.update();
			
			e.getPlayer().sendMessage(ChatColor.GREEN + "The generator has been upgraded!");
		} else if (genBase.getType() == Material.GOLD_BLOCK) {
			
			int configIndex = GeneratorManager.getGeneratorIndexByLocation((e.getClickedBlock().getLocation()));
			if (configIndex == -1) {
				e.getPlayer().sendMessage("An error occured with your generator!");
				return;
			}
			ArrayList<ArrayList<Integer>> list = (ArrayList<ArrayList<Integer>>) ConfigManager.generatorConfig.get("generators");
			ArrayList<Integer> iList = list.get(configIndex);
			
			int targetGeneratorScheduleId = iList.get(0);
			int newLevel = GeneratorManager.getGeneratorLevel(sign) + 1;
			int newDelayTime = 100 / newLevel;
			
			e.getPlayer().sendMessage(targetGeneratorScheduleId + " is the scheduler id!");
			
			Bukkit.getServer().getScheduler().cancelTask(targetGeneratorScheduleId);
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, new Runnable() {
				
				@Override
				public void run() {
					e.getClickedBlock().getLocation().getWorld().dropItem(e.getClickedBlock().getLocation(), GoldGenerator.ironToken());
					//ConfigManager.generatorConfig.set(arg0, arg1);
				}
				
			}, 100, newDelayTime);
			
			iList.set(0, task);
			list.set(configIndex, iList);
			ConfigManager.generatorConfig.set("generators", list);
			
			sign.setLine(2, "Level " + newLevel);
			sign.update();
			
			e.getPlayer().sendMessage(ChatColor.GREEN + "The generator has been upgraded!");
		} else if(genBase.getType() == Material.DIAMOND_BLOCK) {
			
			int configIndex = GeneratorManager.getGeneratorIndexByLocation((e.getClickedBlock().getLocation()));
			if (configIndex == -1) {
				e.getPlayer().sendMessage("An error occured with your generator!");
				return;
			}
			ArrayList<ArrayList<Integer>> list = (ArrayList<ArrayList<Integer>>) ConfigManager.generatorConfig.get("generators");
			ArrayList<Integer> iList = list.get(configIndex);
			
			int targetGeneratorScheduleId = iList.get(0);
			int newLevel = GeneratorManager.getGeneratorLevel(sign) + 1;
			int newDelayTime = 100 / newLevel;
			
			e.getPlayer().sendMessage(targetGeneratorScheduleId + " is the scheduler id!");
			
			Bukkit.getServer().getScheduler().cancelTask(targetGeneratorScheduleId);
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, new Runnable() {
				
				@Override
				public void run() {
					e.getClickedBlock().getLocation().getWorld().dropItem(e.getClickedBlock().getLocation(), DiamondGenerator.ironToken());
					//ConfigManager.generatorConfig.set(arg0, arg1);
				}
				
			}, 100, newDelayTime);
			
			iList.set(0, task);
			list.set(configIndex, iList);
			ConfigManager.generatorConfig.set("generators", list);
			
			sign.setLine(2, "Level " + newLevel);
			sign.update();
			
			e.getPlayer().sendMessage(ChatColor.GREEN + "The generator has been upgraded!");
		} else {
			e.getPlayer().sendMessage("That seems to be a corrupted generator!");
		}
	}
	
//	@EventHandler
//	public void interactEvent(PlayerInteractEvent e) {
//		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK)) return;
//		
//		if (e.getClickedBlock().getType() == Material.OAK_SIGN) {
//			Sign sign = (Sign) e.getClickedBlock().getState();
//			if (sign.getLine(3).equals(ChatColor.GREEN + "Click to upgrade!")) {
//				Player p = e.getPlayer();
//				p.sendMessage("got here!");
//				int currentLevel = GeneratorManager.getGeneratorLevel(sign);
//				if (currentLevel == 3) {
//					p.sendMessage("The generator is max level already!");
//					return;
//				} else {
//				Location clickLoc = e.getClickedBlock().getLocation();
//				p.sendMessage("got here 2!");
//				Block b = clickLoc.subtract(0, 1, 0).getBlock();
//				if (b.getType() == Material.IRON_BLOCK) {
//					if (p.getInventory().contains(IronGenerator.upgradeToLevel2IronToken())) {
//						int newLevel = currentLevel++;
//						IronGenerator.setDropDelay(100 / newLevel);
//						sign.setLine(2, "Level " + newLevel);
//						sign.update();
//						p.sendMessage(ChatColor.GREEN + "The generator has been upgraded to level " + newLevel);
//						return;
//					} else {
//						p.sendMessage(ChatColor.RED + "Sorry," + ChatColor.WHITE + " but you don't have enough iron for that!");
//						return;
//					}
//				}
//				
//				}
//				
//			}
//		}
//		
//	}
	
	
}
