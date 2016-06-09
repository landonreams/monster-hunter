package com.monsterhunter.item.material;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.util.MaterialRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Class describing materials that monsters can drop.
 * The metadata determines the item as such:
 * 0xAABC
 *
 * A: 2 Bytes determining Item Category (Generally, this matches the boss ID).
 * B: 1 Byte  differentiating otherwise identical items (Rathian Scale and Rathian Plate)
 * C: 1 Byte  determining the Color of the item (As determined by the config).
 * @author Landon
 *
 */
public class ItemMonsterMaterial extends RecolorableItem {
	public static enum ItemMonsterMaterialType implements ItemMaterialType {
		SCALE(0, "scale"),
		BONE(1, "bone"),
		PART(2, "part"),
		SHARP(3, "sharp"),
		WEB(4, "web"),
		MANTLE(5, "mantle"),
		GEM(6, "gem"),
		CARAPACE(7, "carapace"),
		SAC(8, "sac"),
		PELT(9, "pelt"),
		ORE(10, "ore"),
		OIL(11, "oil");
		private static final String PREFIX = "monster_";
		private final String shortName;
		public final int metadata;
		private ItemMonsterMaterialType(int value, String un) {
			this.metadata = value;
			shortName = un;
		}
		public String getUnlocalizedName() {
			return PREFIX + shortName;
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
				if(value == type.metadata) {
					return type;
				}
			}
			FMLLog.log(Level.ERROR, "[ItemMonsterMaterialType] Unable to find type value: %d. Assuming Scale.", value);
			return SCALE;
		}
		@Override
		public int getMetadata() {
			return metadata;
		}
		@Override
		public String getShortName() {
			return shortName;
		}
	}


	private ItemMonsterMaterialType type;
	private static MaterialRegistry matreg = MaterialRegistry.INSTANCE;

	public ItemMonsterMaterial(ItemMonsterMaterialType type) {
		super(type);
		this.type = type;
		this.setCreativeTab(MonsterHunter.tabMonsterDrop);
	}

	public ItemMonsterMaterialType type() {
		return type;
	}
}
