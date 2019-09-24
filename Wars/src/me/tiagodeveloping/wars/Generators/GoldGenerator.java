package me.tiagodeveloping.wars.Generators;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.tiagodeveloping.wars.Main;

public class GoldGenerator {
	
	private static String ironName = ChatColor.YELLOW + "Gold token!";
	
	private static String firstSignLine = "Gold Generator";
	private static String secondSignLine = "Level $lvl$";
	
	public static int scheduler;
	
	public static int goldGenDelay = 250;
	
	public static void declareGenerator(Location loc) {
		Block generatorBase = loc.subtract(0, 1, 0).getBlock();
		Block block = loc.add(0, 1, 0).getBlock();
		block.setType(Material.OAK_SIGN);
		
		Sign sign = (Sign) block.getState();
		sign.setLine(0, firstSignLine);
		sign.setLine(2, secondSignLine.replace("$lvl$", "1"));
		sign.setLine(3, ChatColor.GREEN + "Click to upgrade!");
		sign.update();
		loc.getBlock().setBlockData(sign.getBlockData());
		generatorBase.setType(Material.GOLD_BLOCK);
		
		scheduler = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, 
			new Runnable() {
				public void run() {
				loc.getWorld().dropItemNaturally(loc.add(0.5,1,0.5), ironToken());
				loc.subtract(0.5,1,0.5);
				}
			}, 
		100, goldGenDelay);
		
	}
	
	public static ItemStack ironToken() {
		ItemStack token = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta meta = token.getItemMeta();
		meta.setDisplayName(ironName);
		token.setItemMeta(meta);
		return token;
	}
	
	public static ItemStack generatorUpgradeToken(int amount) {
		ItemStack token = new ItemStack(Material.GOLD_INGOT, amount);
		ItemMeta meta = token.getItemMeta();
		meta.setDisplayName(ironName);
		token.setItemMeta(meta);
		return token;
	}
	
}
