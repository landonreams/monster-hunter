package com.monsterhunter.client.render.items;

import java.util.Set;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.init.ModBlocks;
import com.monsterhunter.init.ModItems;
import com.monsterhunter.item.material.ItemMonsterMaterial;
import com.monsterhunter.util.MonsterMaterialRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;

public class ItemRenderRegister {
	public static String modid = MonsterHunter.MODID;
	private static MonsterMaterialRegistry matreg = MonsterMaterialRegistry.getInstance();
	public static void registerItemRenderer() {
		for(ItemMonsterMaterial i : ModItems.monsterMaterials) {
			reg(i);
			Set<Integer> matdat = matreg.getMetadataForType(i.type());
			for(Integer meta : matdat) {
				FMLLog.log(Level.INFO, "[MonsterHunter] RENDER: Entering metadata routine for type %s.", i.type().getUnlocalizedName());
				reg(i, meta);
			}
		}

		reg(ModBlocks.itemMaterialChest);
		reg(ModItems.carvingKnife);
	}

	public static void reg(Item item) {
		reg(item, 0, item.getUnlocalizedName().substring(5));
	}

	public static void reg(Item item, int meta) {
		reg(item, meta, item.getUnlocalizedName().substring(5));
	}

	public static void reg(Item item, String file) {
		reg(item, 0, file);
	}

	public static void reg(Item item, int meta, String file) {
		FMLLog.log(Level.INFO, "[MonsterHunter] RENDER: Registering %s:%d", file,meta);
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, meta, new ModelResourceLocation(file, "inventory"));
	}

}
