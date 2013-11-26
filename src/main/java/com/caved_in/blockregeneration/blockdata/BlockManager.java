package com.caved_in.blockregeneration.blockdata;

import com.caved_in.blockregeneration.BlockRegeneration;
import com.caved_in.blockregeneration.runnables.OreDeplete;
import com.caved_in.blockregeneration.runnables.RestoreBlock;
import com.caved_in.commons.misc.TimeHandler;
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
			if (isOre(block)) {
				//Schedule the block for restoration
				scheduleBlockRestore(blockData, blockRestorationTime);
				//Changes broken ores to cobblestone
				BlockRegeneration.runnableManager.runTaskLater(new OreDeplete(block,Material.COBBLESTONE),2);
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

	public static void setBlock(Block block, BlockData blockData) {
		//Update the blocks material data
		block.getState().setData(blockData.getBlockMaterialData());
		//Update the type
		block.setType(blockData.getBlockType());
		//Update the byte-data (Positioning, etc)
		block.setData(blockData.getBlockData());
		//Update the block state
		block.getState().update(true);
	}

	public static void setBlock(Block block, Material changeMaterial) {
		block.setType(changeMaterial);
		block.getState().setType(changeMaterial);
		block.getState().update(true);
	}

	public static void setBlock(BlockData blockData) {
		setBlock(blockData.getLocation(),blockData);
	}

	public static void setBlock(Location location, BlockData blockData) {
		setBlock(location.getWorld().getBlockAt(location), blockData);
	}

	public static boolean isOre(Block block) {
		return isOre(block.getType());
	}

	public static boolean isOre(Material material) {
		switch (material) {
			case COAL_ORE:
			case IRON_ORE:
			case DIAMOND_ORE:
			case EMERALD_ORE:
			case REDSTONE_ORE:
				return true;
			default:
				return false;
		}
	}

}
