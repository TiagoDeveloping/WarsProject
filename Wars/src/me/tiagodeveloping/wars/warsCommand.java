package me.tiagodeveloping.wars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tiagodeveloping.wars.Generators.GeneratorManager;
import me.tiagodeveloping.wars.Generators.GeneratorType;
import me.tiagodeveloping.wars.arena.Arena;
import me.tiagodeveloping.wars.arena.ArenaConfigManager;
import me.tiagodeveloping.wars.arenaCages.Cage;
import net.md_5.bungee.api.ChatColor;

public class warsCommand implements CommandExecutor {

	GeneratorManager gManager = new GeneratorManager();
	
	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("[wars] commands can only be executed by players");
			return true;
		}
		
		if (args.length == 0) {
			Messages.sendInfoMessage(((Player) sender));
			return true;
		}
		
		
		Player p = (Player) sender;
		Location loc = p.getLocation();
		
		if (args[0].equalsIgnoreCase("setGenerator")) {
			
			if (args.length < 3) {
				Messages.sendHelpMessage(p);
				return true;
			}
			
			String generatorName;
			
			if (args[1].equalsIgnoreCase("gold")) {
				generatorName = args[2];
				gManager.registerGenerator(GeneratorType.GOLD, loc, generatorName);
				p.sendMessage("Gold generator has been positioned at you location!");
				return true;
			} else if (args[1].equalsIgnoreCase("diamond")) {
				generatorName = args[2];
				gManager.registerGenerator(GeneratorType.DIAMOND, loc, generatorName);
				p.sendMessage("Diamond generator has been positioned at you location!");
				return true;
			} else if (args[1].equalsIgnoreCase("iron")) {
				generatorName = args[2];
				gManager.registerGenerator(GeneratorType.IRON, loc, generatorName);
				p.sendMessage("Iron generator has been positioned at you location!");
				return true;
			} else {
				p.sendMessage("generator type help");
			}
			
		} else if (args[0].equalsIgnoreCase("deleteGenerator")) {
			gManager.deleteGenerator(loc, p);
			
		} else if (args[0].equalsIgnoreCase("setcage")) {
			if (args.length < 2) {
				Messages.sendHelpMessage(p);
			}
			Cage cage = new Cage(args[2], args[1], p.getLocation());
			cage.registerToConfig();
			safeArenaConfig();
			p.sendMessage(ChatColor.GREEN + "The cage " + ChatColor.ITALIC + args[1] + ChatColor.RESET + ChatColor.GREEN + " has been created!");
		} else if (args[0].equalsIgnoreCase("createArena")) {
			if (args.length < 1) {
				Messages.sendHelpMessage(p);
			}
			Arena arena = new Arena(args[1], new ArrayList<UUID>(), new ArrayList<Cage>(), p.getLocation());
			arena.writeToConfig();
			safeArenaConfig();
			p.sendMessage(ChatColor.GREEN + "The arena " + ChatColor.ITALIC + args[1] + ChatColor.RESET + ChatColor.GREEN + " has been created!");
		} else {
			Messages.sendHelpMessage(p);
		}
		
		return true;
	}

	private void safeArenaConfig() {
		try {
			ArenaConfigManager.arenaRegisterConfig.save(ArenaConfigManager.arenaRegisterFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
