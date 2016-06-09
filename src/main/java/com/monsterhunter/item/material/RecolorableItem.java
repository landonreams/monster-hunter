package com.monsterhunter.item.material;

import java.util.List;

import com.monsterhunter.item.material.RecolorableItem.ItemMaterialType;
import com.monsterhunter.util.MaterialRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * All recolorable items have the same standard:
 * The last byte in the Metadata determines the color
 * as per the config file
 *
 * METADATA INFO:
 * LAST BYTE MUST DETERMINE ITEM COLOR
 * @author Landon
 *
 */
public abstract class RecolorableItem<T extends ItemMaterialType> extends Item {

	public static interface ItemMaterialType {
		public String getUnlocalizedName();
		public int getMetadata();
		public String getShortName();
		public static <T extends ItemMaterialType> T byName(String name) {
			return null;
		}
	}

	private static MaterialRegistry matreg = MaterialRegistry.INSTANCE;

	public RecolorableItem(T type) {
		super();
		this.setHasSubtypes(true);

		setMaxStackSize(64);
		setRegistryName(type.getUnlocalizedName());
		setUnlocalizedName(this.getRegistryName().toString());
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		String name = String.format("%s_%d", this.getUnlocalizedName(), meta);
		return name;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for(Integer i : matreg.getMetadataForType(type())) {
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}

	public abstract T type();
}
