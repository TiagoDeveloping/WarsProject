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
	private static String secondSignLine = "Level $";
	
	public static char lvl = '1';
	
	private static long dropDelay = 100;
	
	public static int scheduler;
	
	public static int ironGenDelay = 100;
	
	public static void declareGenerator(Location loc) {
		Block generatorBase = loc.subtract(0, 1, 0).getBlock();
		Block block = loc.add(0, 1, 0).getBlock();
		block.setType(Material.OAK_SIGN);
		
		Sign sign = (Sign) block.getState();
		sign.setLine(0, firstSignLine);
		sign.setLine(2, secondSignLine.replace('$', lvl));
		sign.setLine(3, ChatColor.GREEN + "Click to upgrade!");
		sign.update();
		loc.getBlock().setBlockData(sign.getBlockData());
		generatorBase.setType(Material.IRON_BLOCK);
		
		scheduler = 
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.mainClass, 
			new Runnable() {
				public void run() {
					loc.getWorld().dropItem(GeneratorManager.getCenterdBlock(generatorBase.getLocation()), ironToken());
				}
			}, 
		100, dropDelay);
		
	}
	
	
	
	public static void setDropDelay(int newDelay) {
		dropDelay = newDelay;
	}
	
	public static ItemStack ironToken() {
		ItemStack token = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta meta = token.getItemMeta();
		meta.setDisplayName(ironName);
		token.setItemMeta(meta);
		return token;
	}
	
	public static ItemStack generatorUpgradeToken(int amount) {
		ItemStack token = new ItemStack(Material.IRON_INGOT, amount);
		ItemMeta meta = token.getItemMeta();
		meta.setDisplayName(ironName);
		token.setItemMeta(meta);
		return token;
	}
	
}
