package com.monsterhunter.util;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.Level;

import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.FMLLog;

public class Color {
	public final EnumDyeColor baseColor;
	public final int rgb;
	public final int defaultRarity;
	public Color(EnumDyeColor baseColor, int rgb, int defaultRarity) {
		this.baseColor = baseColor;
		this.defaultRarity = defaultRarity;
		this.rgb   = rgb;
	}

	public String getName() {
		return WordUtils.capitalize(baseColor.getName().replace("_", " "));
	}

	public float[] getFloats() {
		return intToFloats(rgb, false);
	}

	/**
	 * Returns float array of int / hex color.
	 * Index 0: R
	 * Index 1: G
	 * Index 2: B
	 * Index 3: A (if alpha flag enabled)
	 * @param color
	 * @param hasAlpha
	 * @return
	 */
	public static float[] intToFloats(int color, boolean hasAlpha) {
		float[] result;
		if(hasAlpha)
			result = new float[4];
		else
			result = new float[3];

		result[0] = ((float)(color >> 16 & 0xFF))/255F;
		result[1] = ((float)(color >>  8 & 0xFF))/255F;
		result[2] = ((float)(color       & 0xFF))/255F;

		if(hasAlpha)
			result[3] = ((float)(color >> 24 & 0xFF))/255F;

		return result;
	}

	public static int floatsToInt(float[] color) {
		if(color.length < 3 || color.length > 4) {
			FMLLog.log(Level.FATAL, "[MonsterHunter] Warning! Unable to convert float array to colors!");
			return -1;
		}
		boolean hasAlpha = color.length == 4;



		int result = ((int)(255F*color[0])) << 16
				   | ((int)(255F*color[1])) << 8
				   | ((int)(255F*color[2]))
				   | (hasAlpha ? ((int)(255F*color[3])) << 24 : 0);

		return result;
	}
}
