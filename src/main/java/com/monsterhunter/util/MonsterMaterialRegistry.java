package com.monsterhunter.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.monsterhunter.MonsterHunter;
import com.monsterhunter.item.material.ItemMonsterMaterialType;

import net.minecraftforge.fml.common.FMLLog;

public class MonsterMaterialRegistry {
	public static class MonsterMaterialRegistryItem {
		private String name;
		private int boss, unique, color;
		private ItemMonsterMaterialType type;

		public MonsterMaterialRegistryItem(String name, int boss, int unique, int color, ItemMonsterMaterialType type) {
			this.name = name;
			this.boss = boss;
			this.unique = unique;
			this.color = color;
			this.type = type;
		}

		public int getMetadata() {
			return color + 16 * unique + 256 * boss;
		}

		public String getName() {
			return name;
		}

		public ItemMonsterMaterialType getType() {
			return type;
		}
	}

	private Set<MonsterMaterialRegistryItem> metamaterials;
	private static MonsterMaterialRegistry instance;

	protected MonsterMaterialRegistry() {
		metamaterials = new HashSet<>();
		JsonObject json = GsonToolkit.jsonFromFile(MonsterHunter.proxy.configDir.getAbsolutePath() + "/monsterMaterials.json");

		JsonArray arr = json.getAsJsonArray("materials");

		String tName;
		int tBoss, tUnique, tColor;
		ItemMonsterMaterialType tType;
		JsonObject tObj;

		MonsterMaterialRegistryItem tItem = null;
		for(JsonElement i : arr){
			try {
				tObj = i.getAsJsonObject();
				tName = tObj.get("name").getAsString();
				tType = ItemMonsterMaterialType.byName(tObj.get("type").getAsString());
				tBoss = tObj.get("boss").getAsInt();
				tUnique = tObj.get("unique").getAsInt();
				tColor = tObj.get("color").getAsInt();
				tItem = new MonsterMaterialRegistryItem(tName, tBoss, tUnique, tColor, tType);
				FMLLog.log(Level.INFO, "[MMR] Creating entry for %s [Type: %s] [Metadata: %d]", tItem.getName(), tItem.getType().getUnlocalizedName(), tItem.getMetadata());
			 	metamaterials.add(tItem);
			} catch (Exception e) {
				FMLLog.log(Level.ERROR, "[MMR] Exception encountered!");
				e.printStackTrace();
			}
		}
	}

	public static MonsterMaterialRegistry getInstance() {
		if(instance == null)
			instance = new MonsterMaterialRegistry();
		return instance;
	}

	public Set<Integer> getMetadataForType(ItemMonsterMaterialType type) {
		Set<Integer> result = metamaterials
				.stream()
				.filter(i -> i.getType() == type)
				.map(i -> i.getMetadata())
				.collect(Collectors.toSet());

		return result;
	}
//
//	public boolean register(int metadata) {
//		return metamaterials.add(metadata);
//	}
}
