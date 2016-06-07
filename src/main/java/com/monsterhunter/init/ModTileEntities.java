package com.monsterhunter.init;

import com.monsterhunter.tileentity.TileEntityDummyMaterialChest;
import com.monsterhunter.tileentity.TileEntityMaterialChest;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	public static void createTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMaterialChest.class, "material_chest_tile_entity");
		GameRegistry.registerTileEntity(TileEntityDummyMaterialChest.class, "material_chest_dummy_tile_entity");
	}
}
