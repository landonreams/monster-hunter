package com.monsterhunter.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPaintEntity extends RenderLiving {

	public RenderPaintEntity(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBase() {

		}, 0);

	}

	/** Dummy method - paint entities do not render. */
	@Override
	protected void preRenderCallback(net.minecraft.entity.EntityLivingBase entitylivingbaseIn, float partialTickTime) {

		GlStateManager.color(0, 0, 0, 0);
	};

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
