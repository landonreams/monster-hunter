package com.monsterhunter.entity;

import java.util.List;

import com.monsterhunter.item.material.RecolorableItem;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class EntityBigMonster extends Entity  {
	/**
	 * Monster ID ranging from 0..127 inclusive.
	 */
	private final int monsterID;

	public List<RecolorableItem> lootLow, lootHigh, lootG;

	public EntityBigMonster(World worldIn, int ID) {
		super(worldIn);
		this.monsterID = ID;
	}

	public int getID() { return monsterID; }

}
