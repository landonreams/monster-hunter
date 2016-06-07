package com.monsterhunter.container;

import com.monsterhunter.item.material.RecolorableItem;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRecolorableItem extends Slot {

	public SlotRecolorableItem(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack item) {
		return item.getItem() instanceof RecolorableItem;
	}

}
