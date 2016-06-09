package com.monsterhunter.block;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.init.ModGuiHandler;
import com.monsterhunter.tileentity.TileEntityMaterialChest;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class BlockMaterialChest extends BlockContainer {

	public static final String unlocalizedName = "material_chest";
	public static final Material material = Material.wood;

	public TileEntityMaterialChest tileEntity;

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockMaterialChest() {
		super(material);


		this.setUnlocalizedName(unlocalizedName);

		this.setRegistryName(MonsterHunter.MODID, unlocalizedName);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setHarvestLevel("axe", 1);

		this.setStepSound(SoundType.WOOD);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		TileEntityMaterialChest te = (TileEntityMaterialChest) world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, te);

		super.breakBlock(world, pos, blockstate);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return (tileEntity = new TileEntityMaterialChest(1));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		FMLLog.log(Level.INFO, "[%s] Chest right click detected!", MonsterHunter.MODNAME);
		if (!world.isRemote) {
			player.openGui(MonsterHunter.instance, ModGuiHandler.MATERIAL_CHEST_GUI, world, pos.getX(), pos.getY(),
					pos.getZ());
		}
		return true;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		final List<ItemStack> drops = super.getDrops(world, pos, state, fortune);

		if (!(world.getTileEntity(pos) instanceof TileEntityMaterialChest)) {
			return null;
		}
		TileEntityMaterialChest te = (TileEntityMaterialChest) world.getTileEntity(pos);
		drops.addAll(te.dropItems());
		return drops;
	}
}
