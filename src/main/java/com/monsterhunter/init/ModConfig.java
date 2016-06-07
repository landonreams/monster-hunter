package com.monsterhunter.init;

import com.monsterhunter.util.Color;
import com.monsterhunter.util.ColorList;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {

	private static Configuration config;

	public static final ColorList colorList = new ColorList();

	public static final String[] DEFAULT_COLOR_NAMES = {
			"Gray",
			"White",
			"Purple",
			"Yellow",
			"Pink",
			"Green",
			"Blue",
			"Red",
			"Orange",
			"Magenta"
	};

	public static final int[] DEFAULT_COLOR_RGBS = {
			0xA0A0A0,
			0xFFFFFF,
			0xB890C0,
			0xF8D058,
			0xE890A0,
			0x79C888,
			0x98D8F0,
			0xF85858,
			0xF89858,
			0xF931F7
	};


	public static void createConfigs(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}

	protected static void syncConfig() {
		config.load();

		String category;

		int rgb;
		String colorName;

		category = "Colors";
		config.addCustomCategoryComment(category, "Color configuration. These colors are used in the dynamic generation of all recolorable items.");

		for(int i = 0; i < Math.min(DEFAULT_COLOR_NAMES.length, DEFAULT_COLOR_RGBS.length); i++) {
			colorName = DEFAULT_COLOR_NAMES[i];
			rgb = config.getInt(
					String.format("color%d", i),
					category,
					DEFAULT_COLOR_RGBS[i],
					0x000000,
					0xFFFFFF,
					String.format("RGB value for %s", colorName));
			colorList.add(new Color(i, rgb, colorName));
		}





		config.save();
	}
}
