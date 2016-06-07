package com.monsterhunter.client.render.blocks;

import com.monsterhunter.init.ModBlocks;
import com.monsterhunter.tileentity.TileEntityMaterialChest;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class BlockRenderRegister {
	public static void registerBlockRenderer() {
		reg(ModBlocks.blockMaterialChest);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMaterialChest.class, new TileEntityRendererMaterialChest());
	}

	public static void reg(Block block) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getUnlocalizedName().substring(5), "inventory"));
	}
}
