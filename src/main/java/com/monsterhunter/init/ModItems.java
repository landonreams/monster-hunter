package com.monsterhunter.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.monsterhunter.item.material.ItemMonsterMaterial;
import com.monsterhunter.item.material.ItemMonsterMaterialType;
import com.monsterhunter.item.weapon.CarvingKnife;
import com.monsterhunter.util.ColorList;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	private static ColorList col = ModConfig.colorList;

	/**
	 * Set containing all base monster materials
	 */
	public static List<ItemMonsterMaterial> monsterMaterials = new ArrayList<>();

	public static CarvingKnife carvingKnife;



	public static void createItems() {
		for(ItemMonsterMaterialType type : ItemMonsterMaterialType.values()) {
			registerItem( new ItemMonsterMaterial(type), monsterMaterials);
			FMLLog.log(Level.INFO, "[MonsterHunter] Registering base %s.", type.getUnlocalizedName());
		}
		carvingKnife = registerItem(new CarvingKnife());
	}

	/**
	 * Register an Item
	 * Borrowed from Choonster
	 * (github.com/Choonster)
	 *
	 * @param item The Item instance
	 * @param <T>  The Item type
	 * @return The Item instance
	 */
	public static <T extends Item> T registerItem(T item, List<T> list) {
		list.add(registerItem(item));

		return item;
	}

	public static <T extends Item> T registerItem(T item) {
		GameRegistry.register(item);
		return item;
	}
}
