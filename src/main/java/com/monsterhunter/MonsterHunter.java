package com.monsterhunter;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.monsterhunter.init.ModItems;
import com.monsterhunter.item.material.ItemMonsterMaterialType;
import com.monsterhunter.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MonsterHunter.MODID, name = MonsterHunter.MODNAME, version = MonsterHunter.VERSION)
public class MonsterHunter {
	public static final String MODID   = "monsterhunter";
	public static final String MODNAME = "Monster Hunter";
	public static final String VERSION = "0.0.0";

	public static File configPath;

	public static final CreativeTabs tabMonsterDrop = new CreativeTabs(MODID) {

		@Override
		public Item getTabIconItem() {
			return ModItems.monsterMaterials.get(ItemMonsterMaterialType.byName("monster_part").value);
		}
		@Override
		public int getIconItemDamage() {
			return 0;
		}
		public String getTranslatedTabLabel() {
			return "Monster Materials";
		}


	};

	@Instance(MODID)
	public static MonsterHunter instance = new MonsterHunter();

	@SidedProxy(clientSide="com.monsterhunter.proxy.ClientProxy",serverSide="com.monsterhunter.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		this.proxy.preInit(e);
		FMLLog.log(Level.INFO, "[MonsterHunter]: Pre-initialization stage.");
		configPath = this.proxy.configDir;
	}

	@EventHandler
    public void init(FMLInitializationEvent e) {
		this.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	this.proxy.postInit(e);
    }


}
