package com.caved_in.blockregeneration.blockdata;

import com.caved_in.blockregeneration.BlockRegeneration;
import com.caved_in.blockregeneration.runnables.OreDeplete;
import com.caved_in.blockregeneration.runnables.RestoreBlock;
import com.caved_in.commons.block.BlockData;
import com.caved_in.commons.block.BlockHandler;
import com.caved_in.commons.time.TimeHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 26/11/13
 * Time: 10:11 AM
 */

public class BlockManager {

	private static final long blockRestorationTime = TimeHandler.getTimeInTicks(6, TimeHandler.TimeType.Seconds);
	private static List<Location> blockLocations = new ArrayList<Location>();

	public static void scheduleBlockRestore(BlockData blockData, long restoreDelay) {
		BlockRegeneration.runnableManager.runTaskLater(new RestoreBlock(blockData),restoreDelay);
	}

	public static void scheduleBlockRestore(BlockData blockData, int delay, TimeHandler.TimeType timeType) {
		scheduleBlockRestore(blockData, TimeHandler.getTimeInTicks(delay, timeType));
	}

	public static void removeBlockLocation(Location location) {
		blockLocations.remove(location);
	}

	public static boolean isBlockLocation(Location location) {
		return blockLocations.contains(location);
	}

	public static boolean handleBlockBreak(Block block) {
		BlockData blockData = new BlockData(block);
		Location blockLocation = blockData.getLocation();
		//Check if the broken block was an ore
		if (!blockLocations.contains(blockLocation)) {
			if (BlockHandler.isOre(block)) {
				//Schedule the block for restoration
				scheduleBlockRestore(blockData, blockRestorationTime);
				//Changes broken ores to cobblestone
				BlockRegeneration.runnableManager.runTaskLater(new OreDeplete(block, Material.COBBLESTONE), 1);
			} else {
				//Schedule the block for restoration
				scheduleBlockRestore(blockData, blockRestorationTime);
			}
			blockLocations.add(blockLocation);
			return true;
		} else {
			return false;
		}
	}

}