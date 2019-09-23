package me.tiagodeveloping.wars;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tiagodeveloping.wars.Generators.GeneratorManager;
import me.tiagodeveloping.wars.Generators.GeneratorType;

public class warsCommand implements CommandExecutor {

	GeneratorManager gManager = new GeneratorManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length < 1) {
			sendHelpMessage(sender);
			return true;
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("[wars] commands can only be executed by players");
			return true;
		}
		
		Player p = (Player) sender;
		Location loc = p.getLocation();
		
		if (args[0].equalsIgnoreCase("setGenerator")) {
			
			if (args.length < 2) {
				sendGeneratorSetCommandHelpMessage(p);
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
		}
		
		return true;
	}

	private void sendGeneratorSetCommandHelpMessage(Player sender) {
		sender.sendMessage("[wars] generator set command error");
		return;
	}
	
	private void sendHelpMessage(CommandSender sender) {
		sender.sendMessage("[wars] help message");
		return;
	}
	
}
