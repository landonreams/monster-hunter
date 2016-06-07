package com.monsterhunter.block;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.init.ModBlocks;
import com.monsterhunter.tileentity.TileEntityDummyMaterialChest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class BlockDummyMaterialChest extends BlockContainer {

	public BlockDummyMaterialChest(Material material) {
		super(material);

		this.setRegistryName(MonsterHunter.MODID, "dummyMaterialChest");
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setHarvestLevel("axe", 1);
		this.setStepSound(SoundType.WOOD);
	}

	@Override
	public boolean isReplaceable(IBlockAccess wolrldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityDummyMaterialChest te = (TileEntityDummyMaterialChest) worldIn.getTileEntity(pos);
		if (te != null) {
			worldIn.destroyBlock(te.getPrimaryPos(), false);
			worldIn.removeTileEntity(te.getPrimaryPos());
		}
		worldIn.removeTileEntity(pos);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		TileEntityDummyMaterialChest te = (TileEntityDummyMaterialChest) worldIn.getTileEntity(pos);
		if (te != null) {
			if (worldIn.getBlockState(te.getPrimaryPos()).getBlock() != ModBlocks.blockMaterialChest) {
				worldIn.destroyBlock(pos, false);
				worldIn.removeTileEntity(pos);
			}
		}
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDummyMaterialChest();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntityDummyMaterialChest te = (TileEntityDummyMaterialChest) world.getTileEntity(pos);

		FMLLog.log(Level.INFO, "[MonsterHunter] Primary Pos: %s", te.getPrimaryPos().toString());

		Block bl = world.getBlockState(te.getPrimaryPos()).getBlock();
		if(bl instanceof BlockMaterialChest) {

			return ((BlockMaterialChest)bl).onBlockActivated(world, te.getPrimaryPos(), state, player, hand, heldItem, side, hitX, hitY, hitZ);
		} else {
			return false;
		}
	}
}
