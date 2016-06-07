package com.monsterhunter.item.material;

import org.apache.logging.log4j.Level;

import net.minecraftforge.fml.common.FMLLog;

public enum ItemMonsterMaterialType {
	SCALE(0, "monster_scale"),
	BONE(1, "monster_bone"),
	PART(2, "monster_part"),
	SHARP(3, "monster_sharp"),
	WEB(4, "monster_web"),
	MANTLE(5, "monster_mantle"),
	GEM(6, "monster_gem"),
	CARAPACE(7, "monster_carapace"),
	SAC(8, "monster_sac"),
	PELT(9, "monster_pelt"),
	ORE(10, "monster_ore"),
	OIL(11, "monster_oil");
	private final String unlocalizedName;
	public final int value;
	private ItemMonsterMaterialType(int value, String un) {
		this.value = value;
		unlocalizedName = un;
	}
	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	public static ItemMonsterMaterialType byName(String name) {
		for(ItemMonsterMaterialType type : ItemMonsterMaterialType.values()) {
			if(name.equalsIgnoreCase(type.getUnlocalizedName())) {
				return type;
			}
		}
		FMLLog.log(Level.ERROR, "[ItemMonsterMaterialType] Unable to find type: %s. Assuming Scale.", name);
		return SCALE;
	}

	public static ItemMonsterMaterialType byValue(int value) {
		for(ItemMonsterMaterialType type : ItemMonsterMaterialType.values()) {
			if(value == type.value) {
				return type;
			}
		}
		FMLLog.log(Level.ERROR, "[ItemMonsterMaterialType] Unable to find type value: %d. Assuming Scale.", value);
		return SCALE;
	}
}
