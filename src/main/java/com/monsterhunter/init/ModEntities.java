package com.monsterhunter.init;

import java.util.List;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.entity.BigMonster;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static List<BigMonster> bigMonsters;
	public static void registerEntities() {

	}

	private static int entityID = 0;

	/**
	 * Register an entity with the specified tracking values.
	 *
	 * @param entityClass          The entity's class
	 * @param entityName           The entity's unique name
	 * @param trackingRange        The range at which MC will send tracking updates
	 * @param updateFrequency      The frequency of tracking updates
	 * @param sendsVelocityUpdates Whether to send velocity information packets as well
	 */
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, entityName, entityID++, MonsterHunter.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
}
