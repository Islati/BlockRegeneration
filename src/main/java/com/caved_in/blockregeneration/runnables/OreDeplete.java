package com.caved_in.blockregeneration.runnables;

import com.caved_in.blockregeneration.blockdata.BlockManager;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 26/11/13
 * Time: 4:42 PM
 */
public class OreDeplete implements Runnable {

	private Block blockReplace;
	private Material material;

	public OreDeplete(Block block, Material material) {
		this.blockReplace = block;
		this.material = material;
	}

	@Override
	public void run() {
		BlockManager.setBlock(blockReplace,material);
	}
}
