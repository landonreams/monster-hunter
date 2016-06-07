package com.monsterhunter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;

import net.minecraftforge.fml.common.FMLLog;

/**
 * Immutable color list generated from /assets/colors.json
 * @TODO Possibly move file to /config/
 * @author Landon
 *
 */
public class ColorList implements Iterable<Color> {
	private List<Color> colors;
	private File configPath;
	public static final String PATH = "colors.json";

	public ColorList() {
		colors = new ArrayList<>();
	}

	public Color get(int index) {
		try {
			//return new Color(0, 0xFF0000, "Test Color Please Ignore");
			return colors.get(index);
		} catch (Exception e) {
			return null;
		}
	}

	public Color byName(String name) {
		for(Color col : colors) {
			if(name.equalsIgnoreCase(col.getName()))
				return col;
		}
		FMLLog.log(Level.ERROR, "[ColorList] Could not find color: %s. Assuming color at index 0.", name);
		return colors.get(0);
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
					return colors.get(index - 1);
				} catch (Exception e) {
					throw e;
				}
			}

		};
	}

	public ColorList add(Color color) {
		colors.add(color);
		return this;
	}
}
