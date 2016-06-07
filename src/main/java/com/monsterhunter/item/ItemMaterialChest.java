package com.monsterhunter.item;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.block.BlockMaterialChest;
import com.monsterhunter.init.ModBlocks;
import com.monsterhunter.tileentity.TileEntityDummyMaterialChest;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class ItemMaterialChest extends Item {


	public ItemMaterialChest(BlockMaterialChest block) {
		super();

		setMaxStackSize(64);
		setRegistryName(block.unlocalizedName);
		setUnlocalizedName(this.getRegistryName().toString());
		setCreativeTab(MonsterHunter.tabMonsterDrop);
		FMLLog.log(Level.INFO, "[MonsterHunter] Created item %s.", this.getUnlocalizedName());
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.isRemote)
			return EnumActionResult.SUCCESS;
		else {
			if(!playerIn.capabilities.isCreativeMode) {
				--stack.stackSize;
			}
			Block block = worldIn.getBlockState(pos).getBlock();
			boolean repl = block.isReplaceable(worldIn, pos);
			if(!repl) {
				pos = pos.up();
			}

			int dir = MathHelper.floor_double((double)((playerIn.rotationYaw * 4F) / 360F) + 0.5D) & 3;

			EnumFacing enumfacing = EnumFacing.getHorizontal(dir).getOpposite();
			BlockPos oneRight = pos.offset(enumfacing.rotateYCCW());

			boolean canPlace =
					worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, oneRight) &&
					worldIn.getBlockState(oneRight).getBlock().isReplaceable(worldIn, oneRight);

			if(canPlace) {
				worldIn.setBlockState(pos, ModBlocks.blockMaterialChest.getDefaultState().withProperty(BlockMaterialChest.FACING, enumfacing), 11);
				worldIn.setBlockState(oneRight, ModBlocks.dummyMaterialChest.getDefaultState(), 11);
				FMLLog.log(Level.INFO, "[MonsterHunter] Placing Material Chest at %s. OneRight is %s.", pos, oneRight);
				TileEntityDummyMaterialChest te = (TileEntityDummyMaterialChest)worldIn.getTileEntity(oneRight);
				if(te != null) {
					FMLLog.log(Level.INFO, "[MonsterHunter] Setting TEDMC Primary Pos to %s.", pos);
					te.setPrimaryPos(pos);
				} else {
					FMLLog.log(Level.ERROR, "[MonsterHunter] TEDMC is null for some reason!");
				}
				return EnumActionResult.SUCCESS;
			} else {
				return EnumActionResult.FAIL;
			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = String.format("%s", this.getUnlocalizedName());
		return name;
	}
}
