package me.tiagodeveloping.wars;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

	private static String wars = ChatColor.DARK_RED + "[" + ChatColor.DARK_GRAY  + "Wars" + ChatColor.DARK_RED + "] ";
	
	public static void sendHelpMessage(Player p) {
		p.sendMessage(wars + ChatColor.DARK_GREEN + "Help page for: " + ChatColor.DARK_BLUE + ChatColor.BOLD + "WaterWars" + ChatColor.DARK_GREEN + "!");
		p.sendMessage(wars + ChatColor.DARK_GREEN + "/wars setGenerator <iron|gold|diamond> <name>" + ChatColor.BOLD + ChatColor.RED + " > " + ChatColor.RESET + ChatColor.DARK_BLUE + "Will set a generator at your location!");
		p.sendMessage(wars + ChatColor.DARK_GREEN + "/wars deleteGenerator" + ChatColor.BOLD + ChatColor.RED + " > " + ChatColor.RESET + ChatColor.DARK_BLUE + "Will delete the generator you are standing on!");
		p.sendMessage(wars + ChatColor.DARK_GREEN + "/wars setCage <cageName> <arenaName>" + ChatColor.BOLD + ChatColor.RED + " > " + ChatColor.RESET + ChatColor.DARK_BLUE + "Creates the cage for the arena");
		p.sendMessage(wars + ChatColor.DARK_GREEN + "/wars createArena <arenaName>" + ChatColor.BOLD + ChatColor.RED + " > " + ChatColor.RESET + ChatColor.DARK_BLUE + "Creates the cage for the arena");
		
	}
	
	public static void sendInfoMessage(Player p) {
		p.sendMessage(wars + ChatColor.DARK_GREEN + "Name: " + ChatColor.DARK_BLUE + "WaterWars");
		p.sendMessage(wars + ChatColor.DARK_GREEN + "Author: " + ChatColor.DARK_BLUE + "TiagoDeveloping");
		p.sendMessage(wars + ChatColor.DARK_GREEN + "Version: " + ChatColor.DARK_BLUE + "1.0");
		p.sendMessage(wars + ChatColor.DARK_GREEN + "'/wars help' : " + ChatColor.DARK_BLUE + " Shows the help message! ");
	}
	
//	public static void cageHelpMessage(Player p) {
//		p.sendMessage(wars + ChatColor.DARK_GREEN + "Usage: /wars setCage <cageName> <arenaName>");
//	}
}
