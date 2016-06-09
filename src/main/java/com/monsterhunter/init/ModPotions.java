package com.monsterhunter.init;

import com.monsterhunter.potion.PotionPaint;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModPotions {


	public static void registerPotions() {
		GameRegistry.register(new PotionPaint(true));
	}
}
