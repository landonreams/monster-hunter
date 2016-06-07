package com.monsterhunter.item.weapon;

import org.apache.logging.log4j.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CarvingKnife extends WeaponBlademaster {


	private static final String unlocalizedName = "carving_knife";
	public CarvingKnife() {
		super();

		this.setRegistryName(unlocalizedName);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer)entityLiving;
			World worldIn = playerIn.getEntityWorld();
			//if(Minecraft.getMinecraft().gameSettings.keyBindAttack.)
				FMLLog.log(Level.INFO, "[MonsterHunter] Detected a swing!");
			return false;
		}
		else
			return false;
	}
}
