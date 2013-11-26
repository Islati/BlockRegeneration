package com.caved_in.blockregeneration.listeners;

import com.caved_in.blockregeneration.BlockRegeneration;
import com.caved_in.blockregeneration.blockdata.BlockManager;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 26/11/13
 * Time: 10:00 AM
 */
public class BukkitListeners implements Listener {

	public BukkitListeners(BlockRegeneration blockRegeneration) {
		blockRegeneration.getServer().getPluginManager().registerEvents(this, blockRegeneration);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		GameMode playerGameMode = event.getPlayer().getGameMode();
		if (playerGameMode == GameMode.SURVIVAL) {
			Block block = event.getBlock();
			if (!BlockManager.handleBlockBreak(block)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		GameMode playerGameMode = event.getPlayer().getGameMode();
		if (playerGameMode == GameMode.SURVIVAL) {
			Block block = event.getBlockPlaced();
			if (BlockManager.isBlockLocation(block.getLocation())) {
				event.setCancelled(true);
			}
		}
	}
}
