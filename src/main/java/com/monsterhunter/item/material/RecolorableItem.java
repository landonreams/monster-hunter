package com.monsterhunter.item.material;

import java.util.List;

import com.monsterhunter.util.Color;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * All recolorable items have the same standard:
 * The last byte in the Metadata determines the color
 * as per colors.json
 * @author Landon
 *
 */
public abstract class RecolorableItem extends Item {

	public RecolorableItem() {
		super();
		this.setHasSubtypes(true);
	}
}
