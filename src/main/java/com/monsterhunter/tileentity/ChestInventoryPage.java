package com.monsterhunter.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class ChestInventoryPage  {


	private ItemStack[] inventory;
	private final int SIZE;
	private final int MAX_STACK;
	private final int PAGE_NUM;
	private String customName;

	private IInventory container;

	private static final String DEFAULT_NAME = "container.material_chest_page";

	public ChestInventoryPage(int size, int maxStack, int page) {
		this.SIZE = size;
		this.MAX_STACK = maxStack;
		this.PAGE_NUM = page;
	}
}
