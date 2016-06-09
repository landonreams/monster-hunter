package com.monsterhunter.init;

import java.util.List;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.client.render.entity.RenderPaintEntity;
import com.monsterhunter.client.render.entity.RenderPaintballEntity;
import com.monsterhunter.entity.EntityBigMonster;
import com.monsterhunter.entity.EntityPaint;
import com.monsterhunter.entity.projectile.EntityPaintball;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static List<EntityBigMonster> entityBigMonsters;
	public static EntityPaint entityPaint;

	public static void registerEntities() {
		registerEntity(EntityPaint.class, "PaintEntity", 250, 1, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityPaint.class,
				m -> new RenderPaintEntity(m));

		registerEntity(EntityPaintball.class, "PaintballEntity", 250, 1, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityPaintball.class,
				renderManager -> new RenderPaintballEntity(renderManager));
	}

	private static int entityID = 0;

	/**
	 * Register an entity with the specified tracking values.
	 *
	 * @param entityClass
	 *            The entity's class
	 * @param entityName
	 *            The entity's unique name
	 * @param trackingRange
	 *            The range at which MC will send tracking updates
	 * @param updateFrequency
	 *            The frequency of tracking updates
	 * @param sendsVelocityUpdates
	 *            Whether to send velocity information packets as well
	 */
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange,
			int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, entityName, entityID++, MonsterHunter.instance, trackingRange,
				updateFrequency, sendsVelocityUpdates);
	}

	private static <T extends RenderEntity> void registerEntityAndRenderer(Class<? extends Entity> entityClass, T renderer, ModelBase base,  String entityName,
			int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, entityName, entityID++, MonsterHunter.instance, trackingRange,
				updateFrequency, sendsVelocityUpdates);
	}
}
