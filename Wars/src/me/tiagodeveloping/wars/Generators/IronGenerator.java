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

public class IronGenerator {
	
	private static String ironName = ChatColor.YELLOW + "Iron token!";
	
	private static String firstSignLine = "Iron Generator";
	private static String secondSignLine = "Level $lvl$";
	
	public static void declareGenerator(Location loc) {
		Block generatorBase = loc.subtract(0, 1, 0).getBlock();
		Block block = loc.add(0, 1, 0).getBlock();
		block.setType(Material.OAK_SIGN);
		
		Sign sign = (Sign) block.getState();
		sign.setLine(0, firstSignLine);
		sign.setLine(2, secondSignLine.replace("$lvl$", "1"));
		sign.update();
		loc.getBlock().setBlockData(sign.getBlockData());
		generatorBase.setType(Material.IRON_BLOCK);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, 
			new Runnable() {
				public void run() {
				loc.getWorld().dropItem(loc, ironToken());
				}
			}, 
		100, 100);
		
	}
	
	private static ItemStack ironToken() {
		ItemStack token = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta meta = token.getItemMeta();
		meta.setDisplayName(ironName);
		token.setItemMeta(meta);
		return token;
	}
	
}
