package com.monsterhunter.event;

import org.apache.logging.log4j.Level;

import com.monsterhunter.item.weapon.WeaponBase;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerClient {

	private static final int LEFT_CLICK = 0;

	private static final int MAIN_KEY_CODE = Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode();
	private static final int OFFH_KEY_CODE = Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode();

	private int mainKeyCode, offKeyCode;
	private boolean isMainMouse, isOffMouse;

	Item heldItem;

	@SubscribeEvent
	public void onMouseClick(MouseEvent event) {
		if(event.getButton() == LEFT_CLICK) { // LEFT_CLICK = 0
			FMLLog.log(Level.INFO, "[MonsterHunter] Default attack button is: %s", Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode());
			ItemStack heldItem = Minecraft.getMinecraft().thePlayer.getHeldItem(EnumHand.MAIN_HAND);
			if(heldItem != null && heldItem.getItem() instanceof WeaponBase) {
				FMLLog.log(Level.INFO, "[MonsterHunter] Successfully intercepted left click!");

				event.setCanceled(true);
			} else {
				FMLLog.log(Level.INFO, "[MonsterHunter] Unsuccessful intercept. Class is %s", heldItem == null ? "null" : heldItem.getItem().getClass().getSimpleName());
			}
		}
	}
}
