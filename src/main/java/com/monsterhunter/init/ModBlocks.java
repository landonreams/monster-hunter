package com.monsterhunter.init;

import org.apache.logging.log4j.Level;

import com.monsterhunter.block.BlockDummyMaterialChest;
import com.monsterhunter.block.BlockMaterialChest;
import com.monsterhunter.item.ItemMaterialChest;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {
	public static BlockMaterialChest blockMaterialChest;
	public static ItemMaterialChest itemMaterialChest;
	public static BlockDummyMaterialChest dummyMaterialChest;

	/**
	 * Dummy method to ensure blocks are generated.
	 */
	public static void init() {

	}

	public static final void createBlocks() {
		FMLLog.log(Level.INFO, "[MonsterHunter] Registering block: materialChest");

		GameRegistry.register(blockMaterialChest = new BlockMaterialChest());
		GameRegistry.register(itemMaterialChest = new ItemMaterialChest(blockMaterialChest));

		GameRegistry.register(dummyMaterialChest = new BlockDummyMaterialChest(Material.wood));
	}
}
