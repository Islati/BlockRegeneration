package com.caved_in.blockregeneration.runnables;

import com.caved_in.blockregeneration.blockdata.BlockManager;
import com.caved_in.commons.block.BlockData;
import com.caved_in.commons.block.BlockHandler;
import com.caved_in.commons.effects.EffectPlayer;
import org.bukkit.Effect;
import org.bukkit.Location;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 26/11/13
 * Time: 10:35 AM
 */
public class RestoreBlock implements Runnable{

	private final BlockData blockData;

	public RestoreBlock(BlockData block) {
		this.blockData = block;
	}

	@Override
	public void run() {
		BlockHandler.setBlock(blockData);
		Location blockLocation = blockData.getLocation();
		BlockManager.removeBlockLocation(blockLocation);
		EffectPlayer.playBlockEffectAt(blockLocation,6, Effect.STEP_SOUND,blockData.getBlockType());
	}
}
