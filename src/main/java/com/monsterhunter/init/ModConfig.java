package com.monsterhunter.init;

import com.monsterhunter.util.Color;
import com.monsterhunter.util.ColorList;

import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {

	private static Configuration config;

	public static final ColorList colorList = new ColorList();

	public static final int[] DEFAULT_COLOR_RARITY = {
			1, //White
			8, //Orange
			9, //Magenta
			6, //Light Blue
			3, //Yellow
			-1, //Lime
			4, //Pink
			0, //Gray
			-2, //Silver
			-3, //Cyan
			2, //Purple
			-4, //Blue
			-5, //Brown
			5, //Green
			7, //Red
			-6 //Black
	};

	public static final int[] DEFAULT_COLOR_RGBS = {
			0xFFFFFF, //White
			0xF89858, //Orange
			0xF931F7, //Magenta
			0x98D8F0, //Light Blue
			0xF8D058, //Yellow
			0x80B050, //Lime
			0xE890A0, //Pink
			0xA0A0A0, //Gray
			0x686868, //Silver
			0x31A0A0, //Cyan
			0xB890C0, //Purple
			0x5870A0, //Blue
			0x988050, //Brown
			0x79C888, //Green
			0xF85858, //Red
			0x303030 //Black
	};

	public static final int COLOR_MIN = 0x000000;
	public static final int COLOR_MAX = 0xFFFFFF;


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

		for(EnumDyeColor color : EnumDyeColor.values()) {
			colorName = color.getName();
			rgb = config.getInt(
				String.format("color%02d", color.getMetadata()),
				category,
				DEFAULT_COLOR_RGBS[color.getMetadata()],
				COLOR_MIN,
				COLOR_MAX,
				String.format("RGB Value for %s", colorName));

			if(rgb < COLOR_MIN) rgb = COLOR_MIN;
			if(rgb > COLOR_MAX) rgb = COLOR_MAX;

			colorList.add(new Color(color, rgb, DEFAULT_COLOR_RARITY[color.getMetadata()]));
		}
		config.save();
	}
}
