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

public class DiamondGenerator {
	
	private static String ironName = ChatColor.YELLOW + "Diamond token!";
	
	private static String firstSignLine = "Diamond Generator";
	private static String secondSignLine = "Level $lvl$";
	
	public static int scheduler;
	
	public static int diamondGenDelay = 500;
	
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
		generatorBase.setType(Material.DIAMOND_BLOCK);
		
		scheduler = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, 
			new Runnable() {
				public void run() {
				loc.getWorld().dropItem(loc, ironToken());
				}
			}, 
		100, diamondGenDelay);
		
	}
	
	public static ItemStack ironToken() {
		ItemStack token = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = token.getItemMeta();
		meta.setDisplayName(ironName);
		token.setItemMeta(meta);
		return token;
	}
	
}
