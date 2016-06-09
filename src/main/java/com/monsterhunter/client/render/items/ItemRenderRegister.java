package com.monsterhunter.client.render.items;

import java.util.Set;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.init.ModBlocks;
import com.monsterhunter.init.ModItems;
import com.monsterhunter.item.material.ItemMonsterMaterial;
import com.monsterhunter.item.material.RecolorableItem;
import com.monsterhunter.util.MaterialRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLLog;

public class ItemRenderRegister {
	public static String modid = MonsterHunter.MODID;
	private static MaterialRegistry matreg = MaterialRegistry.INSTANCE;
	public static void registerItemRenderer() {
		for(RecolorableItem i : ModItems.itemMaterials.values()) {
			reg(i);
			Set<Integer> matdat = matreg.getMetadataForType(i.type());
			if(matdat != null && !matdat.isEmpty()) {
				FMLLog.log(Level.INFO, "[%s] RENDER: Entering metadata routine for type %s.", MonsterHunter.MODNAME,  i.type().getUnlocalizedName());
				for(Integer meta : matdat) {
					reg(i, meta);
				}
			}
		}

		reg(ModBlocks.itemMaterialChest);
		reg(ModItems.itemCarvingKnife);

		for(int i = 0; i < 16; i++) {
			reg(ModItems.paintball, i);
		}
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
		FMLLog.log(Level.INFO, "[%s] RENDER: Registering %s:%d", MonsterHunter.MODNAME, file, meta);
		//ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(file, "inventory"));
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, meta, new ModelResourceLocation(file, "inventory"));
	}

}
