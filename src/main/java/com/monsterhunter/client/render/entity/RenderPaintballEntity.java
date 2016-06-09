package com.monsterhunter.client.render.entity;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.entity.projectile.EntityPaintball;
import com.monsterhunter.init.ModConfig;
import com.monsterhunter.init.ModItems;
import com.monsterhunter.util.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;

public class RenderPaintballEntity<T extends EntityPaintball> extends Render<T> {

	private static final ResourceLocation resourceLocation = new ResourceLocation(MonsterHunter.MODID, "/textures/items/monster_gem.png");
	private final RenderItem renderItem;
	private Color color;

	public RenderPaintballEntity(RenderManager renderManager) {
		super(renderManager);
		this.renderItem = Minecraft.getMinecraft().getRenderItem();
	}

	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();

        float[] colors = entity.color.getFloats();

        GlStateManager.color(colors[0], colors[1], colors[2], 1.0F);

        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(resourceLocation);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        renderItem.renderItem(getPaintball(entity.color.baseColor.getMetadata()), ItemCameraTransforms.TransformType.GROUND);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return resourceLocation;
	}

	public ItemStack getPaintball(int metadata) {
		return new ItemStack(ModItems.paintball, 1, metadata);
	}

}

