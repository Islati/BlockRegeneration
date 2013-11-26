package com.caved_in.blockregeneration.blockdata;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 26/11/13
 * Time: 10:37 AM
 */
public class BlockData {

	private byte blockData;
	private MaterialData blockMaterialData;
	private Location location;

	public BlockData(Block block) {
		this(block,block.getType());
	}

	public BlockData(Block block, Material changeMaterial) {
		this.location = block.getLocation();
		this.blockMaterialData = new MaterialData(changeMaterial);
		this.blockData = block.getData();
	}

	public MaterialData getBlockMaterialData() {
		return blockMaterialData;
	}

	public Material getBlockType() {
		return blockMaterialData.getItemType();
	}

	public byte getBlockData() {
		return blockData;
	}

	public Location getLocation() {
		return location;
	}
}
