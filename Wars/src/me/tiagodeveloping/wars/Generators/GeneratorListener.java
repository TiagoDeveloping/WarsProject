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
			ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>) ConfigManager.generatorConfig.get("generators");
			ArrayList<String> iList = list.get(configIndex);
			
			int targetGeneratorScheduleId = Integer.parseInt(iList.get(0));
			int newLevel = GeneratorManager.getGeneratorLevel(sign) + 1;
			int newDelayTime = IronGenerator.ironGenDelay / newLevel;
			
			if (newLevel == 2 && !(e.getPlayer().getInventory().containsAtLeast(IronGenerator.generatorUpgradeToken(32), 32))) {
				e.getPlayer().sendMessage("You don't have enough iron to upgrade the generator! You need 32!");
				return;
			} else if (newLevel == 3 && !(e.getPlayer().getInventory().containsAtLeast(IronGenerator.generatorUpgradeToken(96), 96))) {
				e.getPlayer().sendMessage("You don't have enough iron to upgrade the generator! You need 96!");
				return;
			} else if (newLevel == 4 && !(e.getPlayer().getInventory().containsAtLeast(IronGenerator.generatorUpgradeToken(128), 128))) {
				e.getPlayer().sendMessage("You don't have enough iron to upgrade the generator! You need 128!");
				return;
			}
			
			if (newLevel > 4) {
				e.getPlayer().sendMessage(ChatColor.GREEN + "The generator is already max level!");
				return;
			}
			
			if (newLevel == 2) {
				e.getPlayer().getInventory().removeItem(IronGenerator.generatorUpgradeToken(32));
			}
			
			if (newLevel == 3) {
				e.getPlayer().getInventory().removeItem(IronGenerator.generatorUpgradeToken(96));
			}
			
			if (newLevel == 4) {
				e.getPlayer().getInventory().removeItem(IronGenerator.generatorUpgradeToken(128));
			}
			
			//e.getPlayer().sendMessage(targetGeneratorScheduleId + " is the scheduler id!");
			
			Bukkit.getServer().getScheduler().cancelTask(targetGeneratorScheduleId);
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, new Runnable() {
				
				@Override
				public void run() {
					e.getClickedBlock().getLocation().getWorld().dropItemNaturally(e.getClickedBlock().getLocation().add(0.5,0.5,0.5), IronGenerator.ironToken());
					e.getClickedBlock().getLocation().subtract(0.5,0.5,0.5);
					
					
					//ConfigManager.generatorConfig.set(arg0, arg1);
				}
				
			}, 100, newDelayTime);
			
			iList.set(0, task + "");
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
			ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>) ConfigManager.generatorConfig.get("generators");
			ArrayList<String> iList = list.get(configIndex);
			
			int targetGeneratorScheduleId = Integer.parseInt(iList.get(0));
			int newLevel = GeneratorManager.getGeneratorLevel(sign) + 1;
			int newDelayTime = GoldGenerator.goldGenDelay / newLevel;
			
			if (newLevel == 2 && !(e.getPlayer().getInventory().containsAtLeast(GoldGenerator.generatorUpgradeToken(24), 24))) {
				e.getPlayer().sendMessage("You don't have enough gold to upgrade the generator! You need 24!");
				return;
			} else if (newLevel == 3 && !(e.getPlayer().getInventory().containsAtLeast(GoldGenerator.generatorUpgradeToken(48), 48))) {
				e.getPlayer().sendMessage("You don't have enough gold to upgrade the generator! You need 48!");
				return;
			} else if (newLevel == 4 && !(e.getPlayer().getInventory().containsAtLeast(GoldGenerator.generatorUpgradeToken(72), 72))) {
				e.getPlayer().sendMessage("You don't have enough gold to upgrade the generator! You need 72!");
				return;
			}
			
			if (newLevel > 4) {
				e.getPlayer().sendMessage(ChatColor.GREEN + "The generator is already max level!");
				return;
			}
			
			if (newLevel == 2) {
				e.getPlayer().getInventory().removeItem(GoldGenerator.generatorUpgradeToken(24));
			}
			
			if (newLevel == 3) {
				e.getPlayer().getInventory().removeItem(GoldGenerator.generatorUpgradeToken(48));
			}
			
			if (newLevel == 4) {
				e.getPlayer().getInventory().removeItem(GoldGenerator.generatorUpgradeToken(72));
			}
			
			//e.getPlayer().sendMessage(targetGeneratorScheduleId + " is the scheduler id!");
			
			Bukkit.getServer().getScheduler().cancelTask(targetGeneratorScheduleId);
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, new Runnable() {
				
				@Override
				public void run() {
					e.getClickedBlock().getLocation().getWorld().dropItemNaturally(e.getClickedBlock().getLocation().add(0.5,1,0.5), GoldGenerator.ironToken());					//ConfigManager.generatorConfig.set(arg0, arg1);
					e.getClickedBlock().getLocation().subtract(0.5,1,0.5);
				}
				
			}, 100, newDelayTime);
			
			iList.set(0, task + "");
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
			ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>) ConfigManager.generatorConfig.get("generators");
			ArrayList<String> iList = list.get(configIndex);
			
			int targetGeneratorScheduleId = Integer.parseInt(iList.get(0));
			int newLevel = GeneratorManager.getGeneratorLevel(sign) + 1;
			int newDelayTime = DiamondGenerator.diamondGenDelay / newLevel;
			
			if (newLevel == 2 && !(e.getPlayer().getInventory().containsAtLeast(DiamondGenerator.generatorUpgradeToken(16), 16))) {
				e.getPlayer().sendMessage("You don't have enough diamond to upgrade the generator! You need 16!");
				return;
			} else if (newLevel == 3 && !(e.getPlayer().getInventory().containsAtLeast(DiamondGenerator.generatorUpgradeToken(48), 48))) {
				e.getPlayer().sendMessage("You don't have enough diamond to upgrade the generator! You need 48!");
				return;
			}
			
			if (newLevel > 3) {
				e.getPlayer().sendMessage(ChatColor.GREEN + "The generator is already max level!");
				return;
			}
			
			if (newLevel == 2) {
				e.getPlayer().getInventory().removeItem(DiamondGenerator.generatorUpgradeToken(16));
			}
			
			if (newLevel == 3) {
				e.getPlayer().getInventory().removeItem(DiamondGenerator.generatorUpgradeToken(48));
			}
			
			
			//e.getPlayer().sendMessage(targetGeneratorScheduleId + " is the scheduler id!");
			
			Bukkit.getServer().getScheduler().cancelTask(targetGeneratorScheduleId);
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, new Runnable() {
				
				@Override
				public void run() {
					e.getClickedBlock().getLocation().getWorld().dropItemNaturally(e.getClickedBlock().getLocation().add(0.5,0.75,0.5), DiamondGenerator.ironToken());					//ConfigManager.generatorConfig.set(arg0, arg1);
					e.getClickedBlock().getLocation().subtract(0.5,0.75,0.5);
				}
				
			}, 100, newDelayTime);
			
			iList.set(0, task + "");
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
