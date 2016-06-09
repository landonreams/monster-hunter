package com.monsterhunter.event;

import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.item.weapon.ItemWeaponBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class EventHandlerClient {
	private static final Minecraft mc = Minecraft.getMinecraft();

	private static final int LEFT_CLICK = 0;

	private static final int MAIN_KEY_CODE = Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode();
	private static final int OFFH_KEY_CODE = Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode();

	private int mainKeyCode, offKeyCode;
	private boolean isMainMouse, isOffMouse;

	ItemStack heldItem;

//	@SubscribeEvent
//	public void onMouseClick(MouseEvent event) {
//		if(event.getButton() == LEFT_CLICK) { // LEFT_CLICK = 0
//			FMLLog.log(Level.INFO, "[%s] Default attack button is: %s", MonsterHunter.MODNAME, Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode());
//			ItemStack heldItem = Minecraft.getMinecraft().thePlayer.getHeldItem(EnumHand.MAIN_HAND);
//			if(heldItem != null && heldItem.getItem() instanceof WeaponBase) {
//				FMLLog.log(Level.INFO, "[%s] Successfully intercepted left click!"MonsterHunter.MODNAME, );
//
//				event.setCanceled(true);
//			} else {
//				FMLLog.log(Level.INFO, "[%s] Unsuccessful intercept. Class is %s",MonsterHunter.MODNAME,  heldItem == null ? "null" : heldItem.getItem().getClass().getSimpleName());
//			}
//		}
//	}

	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onInputEvent(KeyInputEvent event) {
		if(Keyboard.getEventKey() == mc.gameSettings.keyBindAttack.getKeyCode()) {
			heldItem = mc.thePlayer.getHeldItemMainhand();
			if(heldItem != null && heldItem.getItem() instanceof ItemWeaponBase) {
				FMLLog.log(Level.INFO, "[%s] Attack key intercepted. Attempting to cancel input.",MonsterHunter.MODNAME);
				KeyBinding.setKeyBindState(Keyboard.getEventKey(), false);
			}
		}
	}
}
