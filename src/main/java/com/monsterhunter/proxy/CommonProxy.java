package com.monsterhunter.proxy;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.init.ModBlocks;
import com.monsterhunter.init.ModConfig;
import com.monsterhunter.init.ModGuiHandler;
import com.monsterhunter.init.ModItems;
import com.monsterhunter.init.ModTileEntities;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public File configDir;

    public void preInit(FMLPreInitializationEvent e) {
    	configDir = new File(e.getModConfigurationDirectory(), MonsterHunter.MODID);
    	FMLLog.log(Level.INFO, e.getModConfigurationDirectory().toString());
    	configDir.mkdir();

    	ModConfig.createConfigs(e);
    	ModItems.createItems();
    	ModBlocks.createBlocks();
    	ModTileEntities.createTileEntities();
    }

    public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(MonsterHunter.instance, new ModGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
