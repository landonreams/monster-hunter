package com.monsterhunter.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Immutable color list generated from /assets/colors.json
 * @TODO Possibly move file to /config/
 * @author Landon
 *
 */
public class ColorList implements Iterable<Color> {
	private Map<EnumDyeColor, Color> colors;
	private File configPath;

	public ColorList() {
		colors = new HashMap<>();
	}

	public Color get(EnumDyeColor enumColor) {
		return colors.get(enumColor);
	}

	@Override
	public Iterator<Color> iterator() {
		return new Iterator<Color>() {

			int index = 0;

			@Override
			public boolean hasNext() {
				return index < colors.size();
			}

			@Override
			public Color next() {
				try {
					index++;
					return colors.get(EnumDyeColor.byMetadata(index - 1));
				} catch (Exception e) {
					throw e;
				}
			}

		};
	}

	public ColorList add(Color color) {
		colors.put(color.baseColor, color);
		return this;
	}
}
