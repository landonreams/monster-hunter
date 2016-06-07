package com.monsterhunter.item.material;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.util.MonsterMaterialRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Class describing materials that monsters can drop.
 * The metadata determines the item as such:
 * 0xAABC
 *
 * A: 2 Bytes determining Item Category (Generally, this matches the boss ID).
 * B: 1 Byte  differentiating otherwise identical items (Rathian Scale and Rathian Plate)
 * C: 1 Byte  determining the Color of the item (As determined by colors.json).
 * @author Landon
 *
 */
public class ItemMonsterMaterial extends RecolorableItem {
	private ItemMonsterMaterialType type;
	private static MonsterMaterialRegistry matreg = MonsterMaterialRegistry.getInstance();

	public ItemMonsterMaterial(ItemMonsterMaterialType type) {
		super();
		this.type = type;

		setMaxStackSize(64);
		setRegistryName(type.getUnlocalizedName());
		setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(MonsterHunter.tabMonsterDrop);

		FMLLog.log(Level.INFO, "[MonsterHunter] REG: Registered %s", this.getUnlocalizedName());
	}


	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		String name = String.format("%s_%d", this.getUnlocalizedName(), meta);
		return name;
	}

	public ItemMonsterMaterialType type() {
		return type;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for(Integer i : matreg.getMetadataForType(type)) {
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}


}
