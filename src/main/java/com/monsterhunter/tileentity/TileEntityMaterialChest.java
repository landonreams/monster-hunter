package com.monsterhunter.tileentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.container.ContainerMaterialChest;
import com.monsterhunter.item.material.RecolorableItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import scala.actors.threadpool.Arrays;

public class TileEntityMaterialChest extends TileEntityLockableLoot implements IInventory {

	private ItemStack[] inventory;
	private final int INVENTORY_SIZE = 54;
	private final int MAX_STACK = 64;

	private final static int DEFAULT_PAGES = 1;
	private int numPages;

	private String customName;

	private static final String DEFAULT_NAME = "container.material_chest_tile_entity";

	public TileEntityMaterialChest() {
		this(DEFAULT_PAGES);
	}

	public TileEntityMaterialChest(int numberOfPages) {
		this.inventory = new ItemStack[INVENTORY_SIZE];
		
		FMLLog.log(Level.INFO, "[MonsterHunter] Created ItemStackHandler of size %d.", INVENTORY_SIZE);
	}

	public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

	/**
	 * Get a list of the {IItemHandler's contents with the stacks randomly
	 * split. Adapted from InventoryHelper#dropInventoryItems}.
	 *
	 * @param itemHandler
	 *            The inventory
	 * @param random
	 *            The Random object
	 * @return The drops list
	 */
	public static List<ItemStack> dropItemHandlerContents(IItemHandler itemHandler, Random random) {
		final List<ItemStack> drops = new ArrayList<ItemStack>();

		for (int slot = 0; slot < itemHandler.getSlots(); ++slot) {
			while (itemHandler.getStackInSlot(slot) != null) {
				final int amount = random.nextInt(21) + 10;

				if (itemHandler.extractItem(slot, amount, true) != null) {
					final ItemStack itemStack = itemHandler.extractItem(slot, amount, false);
					drops.add(itemStack);
				}
			}
		}
		return drops;
	}

	/**
	 * This method returns a integer which is the size of the inventory in
	 * question.
	 */
	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}

	/**
	 * This method returns the item stack that correlates with a given slot in
	 * the inventory, or null if that index does not exist.
	 */
	@Nullable
	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
	}

	@Nullable
	private void setStackInSlot(int index, ItemStack stack) {
		inventory[index] = stack;
	}

	@Nullable
	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					// Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	/**
	 * This method clears a slot and returns its previous content.
	 */
	@Nullable
	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, null);
		return stack;
	}

	/**
	 * This method will set the contents of each slot based on the parameters.
	 */
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory()) {
			return;
		}

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		if (stack != null && stack.stackSize == 0) {
			stack = null;
		}

		this.setStackInSlot(index, stack);
		this.markDirty();
	}

	/**
	 * This method will return the maximum stack size in the inventory. (64 in
	 * most cases).
	 */
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Returns boolean based on whether or not tile entity is useable by player
	 * in question.
	 *
	 * @param player
	 *            Player in question
	 */
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.getPos()) == this
				&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	/**
	 * This method is not needed in this case, and as such, is left blank.
	 */
	@Override
	public void openInventory(EntityPlayer player) {

	}

	/**
	 * This method is not needed in this case, and as such, is left blank.
	 */
	@Override
	public void closeInventory(EntityPlayer player) {

	}

	/**
	 * This method will return whether or not an item stack is valid for a
	 * certain slot in the inventory. If certain slots are reserved, set
	 * conditional. Otherwise, as in this case, return true.
	 */
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.getItem() instanceof RecolorableItem;
	}

	/**
	 * This method is not needed in this case, and as such, is left blank.
	 */
	@Override
	public int getField(int id) {
		return 0;
	}

	/**
	 * This method is not needed in this case, and as such, is left blank.
	 */
	@Override
	public void setField(int id, int value) {

	}

	/**
	 * This method is not needed in this case, and as such, is left blank.
	 */
	@Override
	public int getFieldCount() {
		return 0;
	}

	/**
	 * This method clears the inventory
	 */
	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++) {
			this.setStackInSlot(i, null);
		}
	}

	/**
	 * This method will return a name for the tile entity, depending on whether
	 * or not a custom name is set already.
	 */
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.red_chest_tile_entity";
	}

	/**
	 * This method returns whether or not a custom name is set.
	 */
	@Override
	public boolean hasCustomName() {
		return (customName != null) && (customName != "");
	}

	/**
	 * This method returns the custom name of the method in chat format.
	 */
	@Override
	public ITextComponent getDisplayName() {
		if (this.hasCustomName()) {
			return new TextComponentString(this.getName());
		} else {
			return new TextComponentTranslation(this.getName());
		}
	}

	/**
	 * This method checks to see whether the tile entity has a capability, a
	 * peudo implementation.
	 */
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return false;
	}


	@Override
	public void writeToNBT(NBTTagCompound nbt) {
	    super.writeToNBT(nbt);

	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) {
	        if (this.getStackInSlot(i) != null) {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);

	    if (this.hasCustomName()) {
	        nbt.setString("CustomName", this.getCustomName());
	    }
	}


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);

	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
	    }

	    if (nbt.hasKey("CustomName", 8)) {
	        this.setCustomName(nbt.getString("CustomName"));
	    }
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerMaterialChest(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return MonsterHunter.MODID + ":guiMaterialChest";
	}

	public Collection<? extends ItemStack> dropItems() {
		return Arrays.asList(inventory);
	}

}
