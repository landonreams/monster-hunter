package com.monsterhunter.proxy;

import org.apache.logging.log4j.Level;

import com.monsterhunter.client.render.ModColorManager;
import com.monsterhunter.client.render.blocks.BlockRenderRegister;
import com.monsterhunter.client.render.items.ItemRenderRegister;
import com.monsterhunter.entity.EntityParticleFXPaint;
import com.monsterhunter.event.EventHandlerClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        EventHandlerClient clientHandler = new EventHandlerClient();

        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerBlockRenderer();
        ModColorManager.registerColourHandlers();
        MinecraftForge.EVENT_BUS.register(clientHandler);

        FMLLog.log(Level.INFO, "[MonsterHunter] ATTEMPTING TO REGISTER CLIENT HANDLER.");
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    @Override
    public void generatePaintParticle(Entity theEntity, EnumDyeColor color) {
    	BlockPos pos = theEntity.getPosition();
    	EntityFX particle = new EntityParticleFXPaint(
    			theEntity.worldObj,
    			color,
    			pos.getX(),
    			pos.getY() + 0.5F,
    			pos.getZ()
			);
    	Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }
}
