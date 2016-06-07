package com.monsterhunter.client.render;

import com.monsterhunter.init.ModConfig;
import com.monsterhunter.init.ModItems;
import com.monsterhunter.item.material.ItemMonsterMaterial;
import com.monsterhunter.util.Color;
import com.monsterhunter.util.ColorList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Registers {@link IBlockColor}/{@link IItemColor} handlers for this mod's blocks/items.
 *
 * @author Choonster
 */
@SideOnly(Side.CLIENT)
public class ModColorManager {
	private static final Minecraft minecraft = Minecraft.getMinecraft();
	private static ColorList colors = ModConfig.colorList;

	/**
	 * Register the colour handlers.
	 */
	public static void registerColourHandlers() {
		final BlockColors blockColors = minecraft.getBlockColors();
		final ItemColors itemColors = minecraft.getItemColors();

		registerBlockColourHandlers(blockColors);
		registerItemColourHandlers(itemColors);
	}

	/**
	 * Register the {@link IBlockColor} handlers.
	 *
	 * @param blockColors The BlockColors instance
	 */
	private static void registerBlockColourHandlers(final BlockColors blockColors) {
		// Use the grass colour of the biome or the default grass colour

	}

	/**
	 * Register the {@link IItemColor} handlers
	 *
	 * @param blockColors The BlockColors instance
	 * @param itemColors  The ItemColors instance
	 */
	private static void registerItemColourHandlers(final ItemColors itemColors) {
		// Use the Block's colour handler for an ItemBlock
		final IItemColor materialColorHandler = (stack, tintIndex) -> colors.get(stack.getMetadata() & 0xF).rgb;
		//final IItemColor materialColorHandler = (stack, tintIndex) -> 0xFF00FF;

		for(ItemMonsterMaterial mat : ModItems.monsterMaterials)
			itemColors.registerItemColorHandler(materialColorHandler, mat);
	}
}