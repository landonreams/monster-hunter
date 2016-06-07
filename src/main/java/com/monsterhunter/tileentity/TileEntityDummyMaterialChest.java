package com.monsterhunter.tileentity;

import org.apache.logging.log4j.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class TileEntityDummyMaterialChest extends TileEntity implements IInventory {
	private BlockPos primary_pos;

	public TileEntityDummyMaterialChest() {
		FMLLog.log(Level.WARN, "[MonsterHunter] WARNING: TileEntityDummyMaterialChest being created with default constructor!");
		primary_pos = new BlockPos(0,0,0);
	}

	public TileEntityDummyMaterialChest(BlockPos primary) {
		primary_pos = primary;
	}

	public void setPrimaryPos(BlockPos pos) {
		primary_pos = pos;
	}

	public BlockPos getPrimaryPos() {
		return primary_pos;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("px", primary_pos.getX());
		nbt.setInteger("py", primary_pos.getY());
		nbt.setInteger("pz", primary_pos.getZ());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		primary_pos = new BlockPos(
			nbt.getInteger("px"),
			nbt.getInteger("py"),
			nbt.getInteger("pz")
		);
	}

	public TileEntityMaterialChest getPrimaryEntity() {
		return (TileEntityMaterialChest) this.getWorld().getTileEntity(primary_pos);
	}

	@Override
	public String getName() {
		return getPrimaryEntity().getName();
	}

	@Override
	public boolean hasCustomName() {
		return getPrimaryEntity().hasCustomName();
	}

	@Override
	public ITextComponent getDisplayName() {
		return getPrimaryEntity().getDisplayName();
	}

	@Override
	public int getSizeInventory() {
		return getPrimaryEntity().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return getPrimaryEntity().getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return getPrimaryEntity().decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return getPrimaryEntity().removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		getPrimaryEntity().setInventorySlotContents(index, stack);

	}

	@Override
	public int getInventoryStackLimit() {
		return getPrimaryEntity().getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return getPrimaryEntity().isUseableByPlayer(player);
	}

	@Override
	public void openInventory(EntityPlayer player) {
		getPrimaryEntity().openInventory(player);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		getPrimaryEntity().closeInventory(player);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return getPrimaryEntity().isItemValidForSlot(index, stack);
	}

	@Override
	public int getField(int id) {
		return getPrimaryEntity().getField(id);
	}

	@Override
	public void setField(int id, int value) {
		getPrimaryEntity().setField(id, value);
	}

	@Override
	public int getFieldCount() {
		return getPrimaryEntity().getFieldCount();
	}

	@Override
	public void clear() {
		getPrimaryEntity().clear();
	}

}
