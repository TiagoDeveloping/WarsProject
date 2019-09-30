package me.tiagodeveloping.wars.game;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.tiagodeveloping.wars.arena.ArenaUtil;

public class GameListener implements Listener {

	public void playerJoinGameEvent(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action aL = Action.LEFT_CLICK_BLOCK;
		Action aR = Action.RIGHT_CLICK_BLOCK;
		Action aE = e.getAction();
		
		if (aE == aL || aE == aR) {
			Block block = e.getClickedBlock();
			Sign sign = (Sign) block.getState();
			String signHeader = sign.getLine(0);
			if (signHeader == JoinGameSign.signHeader) {
				String mapName = sign.getLine(1);
				ArenaUtil.teleportPlayerToArena(p.getUniqueId(), mapName);
				sign.setLine(4, "Max 8 players");
			}
		}
		
	}
	
}
