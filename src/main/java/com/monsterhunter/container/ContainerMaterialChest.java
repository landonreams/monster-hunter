package com.monsterhunter.container;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.tileentity.TileEntityMaterialChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ContainerMaterialChest extends Container {

	private IInventory te;

	private static int NUM_SLOTS_CHEST = 54;

	/**
	 * SlotRecolorableItems: Tile Entity 0-26: 00 - 26 Player Inventory 27-53: 27 - 53 Player
	 * Inventory 0-8: 54 - 62
	 */
	public ContainerMaterialChest(IInventory playerInv, TileEntityMaterialChest te) {
		this.te = te;

		//TE: SlotRecolorableItems 0-53 (0-53)
		for(int y = 0; y < 6; ++y) {
			for(int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new SlotRecolorableItem(te, x + y * 9, 18 * x + 8, 18 * y + 18));
			}
		}

		//Player Inv SlotRecolorableItems 9-35 (54-79)
		for(int y = 0; y < 3; ++y) {
			for(int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 18 * x + 8, 18 * y + 139));
			}
		}

		//Player Inv SlotRecolorableItems 0-8 (80-89)
		for(int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 18 * x + 8, 197));
		}
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	      	if(fromSlot < NUM_SLOTS_CHEST) {
	      		if(!this.mergeItemStack(current, NUM_SLOTS_CHEST, NUM_SLOTS_CHEST + 35, true))
	      			return null;
	      	} else {
	      		if(!this.mergeItemStack(current, 0, NUM_SLOTS_CHEST, false))
      				return null;
	      	}

	        if (current.stackSize == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.stackSize == previous.stackSize)
	            return null;
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}

	/**
	 * This method handles the merging item stacks.
	 */
	@Override
	protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
	    boolean success = false;
	    int index = startIndex;

	    if (useEndIndex)
	        index = endIndex - 1;

	    Slot slot;
	    ItemStack stackinslot;

	    if (stack.isStackable()) {
	        while (stack.stackSize > 0 && (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex)) {
	            slot = (Slot) this.inventorySlots.get(index);
	            stackinslot = slot.getStack();

	            if (stackinslot != null && stackinslot.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == stackinslot.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, stackinslot)) {
	                int l = stackinslot.stackSize + stack.stackSize;
	                int maxsize = Math.min(stack.getMaxStackSize(), slot.getItemStackLimit(stack));

	                if (l <= maxsize) {
	                    stack.stackSize = 0;
	                    stackinslot.stackSize = l;
	                    slot.onSlotChanged();
	                    success = true;
	                } else if (stackinslot.stackSize < maxsize) {
	                    stack.stackSize -= stack.getMaxStackSize() - stackinslot.stackSize;
	                    stackinslot.stackSize = stack.getMaxStackSize();
	                    slot.onSlotChanged();
	                    success = true;
	                }
	            }

	            if (useEndIndex) {
	                --index;
	            } else {
	                ++index;
	            }
	        }
	    }

	    if (stack.stackSize > 0) {
	        if (useEndIndex) {
	            index = endIndex - 1;
	        } else {
	            index = startIndex;
	        }

	        while (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex && stack.stackSize > 0) {
	            slot = (Slot) this.inventorySlots.get(index);
	            stackinslot = slot.getStack();

	            // Forge: Make sure to respect isItemValid in the SlotRecolorableItem.
	            if (stackinslot == null && slot.isItemValid(stack)) {
	                if (stack.stackSize < slot.getItemStackLimit(stack)) {
	                    slot.putStack(stack.copy());
	                    stack.stackSize = 0;
	                    success = true;
	                    break;
	                } else {
	                    ItemStack newstack = stack.copy();
	                    newstack.stackSize = slot.getItemStackLimit(stack);
	                    slot.putStack(newstack);
	                    stack.stackSize -= slot.getItemStackLimit(stack);
	                    success = true;
	                }
	            }

	            if (useEndIndex) {
	                --index;
	            } else {
	                ++index;
	            }
	        }
	    }

	    return success;
	}

}
