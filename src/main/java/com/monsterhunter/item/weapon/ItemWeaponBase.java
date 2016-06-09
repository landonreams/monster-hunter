package com.monsterhunter.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemWeaponBase extends Item {
	/**
	 * Sheath time of weapon in ticks.
	 */
	public final int sheathTicks;

	/**
	 * Unsheath time of weapon in ticks.
	 */
	public final int unsheathTicks;

	/**
	 * Whether or not the weapon is sheathed.
	 */
	public boolean isSheathed;


	public ItemWeaponBase() {
		this.sheathTicks = 20;
		this.unsheathTicks = 20;

		isSheathed = true;
	}


	@Override
	public abstract boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack);

	@Override
	public EnumAction getItemUseAction(ItemStack parlItemStack) {
		return EnumAction.NONE;
	}
}

