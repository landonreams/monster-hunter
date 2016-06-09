package com.monsterhunter.item;

import java.util.List;

import com.monsterhunter.entity.projectile.EntityPaintball;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemPaintball extends Item {



	private static final String unlocalizedName = "paintball";
	public ItemPaintball() {
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setRegistryName(unlocalizedName);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setHasSubtypes(true);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!playerIn.capabilities.isCreativeMode) {
            --stack.stackSize;
        }

		if(!worldIn.isRemote) {
			EntityPaintball entitypball = new EntityPaintball(worldIn, playerIn, EnumDyeColor.byMetadata(stack.getMetadata()));
			entitypball.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entitypball);
		}

		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		String name = String.format("%s_%s", this.getUnlocalizedName(), EnumDyeColor.byMetadata(meta));
		return name;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for(int i = 0; i < 16; i++)
			subItems.add(new ItemStack(itemIn, 1, i));
	}
}
