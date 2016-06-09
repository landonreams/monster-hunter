package com.monsterhunter.init;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.item.ItemPaintball;
import com.monsterhunter.item.material.ItemMonsterMaterial;
import com.monsterhunter.item.material.ItemMonsterMaterial.ItemMonsterMaterialType;
import com.monsterhunter.item.material.RecolorableItem;
import com.monsterhunter.item.material.RecolorableItem.ItemMaterialType;
import com.monsterhunter.item.weapon.ItemCarvingKnife;
import com.monsterhunter.util.ColorList;
import com.monsterhunter.util.MaterialRegistry;
import com.monsterhunter.util.MaterialRegistry.MaterialRegistryItem;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	private static ColorList col = ModConfig.colorList;

	/**
	 * Set containing all base monster materials
	 */
	public static Map<ItemMaterialType, RecolorableItem> itemMaterials = new HashMap<>();

	public static ItemCarvingKnife itemCarvingKnife;
	public static ItemPaintball paintball;

	public static final int BOSS_JAGGI = 0;

	public static final int BOSS_NULL = 127;

	/**
	 * ALL NEW ITEM METADATA MUST BE ADDED TO THIS METHOD!!!
	 * IF NOT, THE ITEM WILL NOT EXIST IN-GAME!
	 */
	protected static void setupMaterialRegistry() {
		MaterialRegistry matreg = MaterialRegistry.INSTANCE;

		Set<MaterialRegistryItem> temporarySet = new HashSet<>();

		for(ItemMonsterMaterialType type : ItemMonsterMaterialType.values()) {
			for(EnumDyeColor color : EnumDyeColor.values()) {
				temporarySet.add(new MaterialRegistryItem(BOSS_NULL, 0, color));
			}
			matreg.put(type, temporarySet);
			temporarySet.clear();
		}
	}

	public static void createItems() {
		setupMaterialRegistry();
		for(ItemMonsterMaterialType type : ItemMonsterMaterialType.values()) {
			itemMaterials.put(type, registerItem( new ItemMonsterMaterial(type) ));
			FMLLog.log(Level.INFO, "[%s] Registering base %s.",MonsterHunter.MODNAME,  type.getUnlocalizedName());
		}
		itemCarvingKnife = registerItem(new ItemCarvingKnife());
		paintball = registerItem(new ItemPaintball());
	}

	/**
	 * Register an Item
	 * Borrowed from Choonster
	 * (github.com/Choonster)
	 *
	 * @param item The Item instance
	 * @return The Item instance
	 */
	public static <T extends Item> T registerItem(T item) {
		GameRegistry.register(item);
		return item;
	}
}
