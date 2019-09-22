package me.tiagodeveloping.wars.Generators;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.tiagodeveloping.wars.Main;

public class IronGenerator {
	
	private String ironName = "Iron token!";
	
	private String firstSignLine = "Iron Generator";
	private String secondSignLine = "Level $lvl$";
	
	public void declareGenerator(Location loc) {
		Block generatorBase = loc.subtract(0, 1, 0).getBlock();
		Sign sign = (Sign) loc.getBlock();
		sign.setLine(0, firstSignLine);
		sign.setLine(2, secondSignLine);
		loc.getBlock().setBlockData(sign.getBlockData());
		
		generatorBase.setType(Material.IRON_BLOCK);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.mainClass, 
			new Runnable() {
				public void run() {
				loc.getWorld().dropItemNaturally(loc, ironToken());
				}
			}, 
		100);
		
	}
	
	private ItemStack ironToken() {
		ItemStack token = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta meta = token.getItemMeta();
		meta.setDisplayName(ironName);
		token.setItemMeta(meta);
		return token;
	}
	
}
