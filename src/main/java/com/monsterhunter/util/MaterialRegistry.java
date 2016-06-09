package com.monsterhunter.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.monsterhunter.item.material.ItemMiscMaterial.ItemMiscMaterialType;
import com.monsterhunter.item.material.ItemMonsterMaterial.ItemMonsterMaterialType;
import com.monsterhunter.item.material.RecolorableItem.ItemMaterialType;

import net.minecraft.item.EnumDyeColor;

public class MaterialRegistry {
	public static class MaterialRegistryItem {
		private int boss, unique, color;

		public MaterialRegistryItem(int unique, EnumDyeColor color) {
			this(0, unique, color);
		}

		public MaterialRegistryItem(int boss, int unique, EnumDyeColor color) {
			Preconditions.checkArgument(boss >= 0 && boss < 128, "Boss must be in range [0,127]!");
			Preconditions.checkArgument(unique >= 0 && unique < 16, "Unique must be in range [0,15]!");
			this.boss = boss;
			this.unique = unique;
			this.color = color.getMetadata();
		}

		public int getMetadata() {
			return   color
				   | unique << 4
				   | boss   << 8;
		}
	}

	private Map<ItemMaterialType, Set<MaterialRegistryItem>> metamaterials;
	public static final MaterialRegistry INSTANCE = new MaterialRegistry();

	protected MaterialRegistry() {
		metamaterials = new HashMap<>();
		initMaterials();
	}

	protected void initMaterials() {
		for(ItemMaterialType type : ItemMonsterMaterialType.values())
			metamaterials.put(type, new HashSet<>());

		for(ItemMaterialType type : ItemMiscMaterialType.values())
			metamaterials.put(type, new HashSet<>());
	}

	public void put(ItemMaterialType type, Set<MaterialRegistryItem> set) {
		metamaterials.get(type).clear();
		metamaterials.get(type).addAll(set);
	}

	public Set<Integer> getMetadataForType(ItemMaterialType type) {
		Set<Integer> result = metamaterials.get(type).stream()
				.map(i -> i.getMetadata())
				.collect(Collectors.toSet());

		return result;
	}
}
