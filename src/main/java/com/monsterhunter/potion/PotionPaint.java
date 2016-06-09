package com.monsterhunter.potion;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.entity.EntityPaint;
import com.monsterhunter.entity.EntityPaint.PaintDuration;
import com.monsterhunter.init.ModConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionPaint extends Potion {
	private final boolean isClearedByWater;
	private static final ResourceLocation EFFECT_ICON = new ResourceLocation(MonsterHunter.MODID, "/textures/effects/effect_paint.png");

	public PotionPaint(boolean isClearedByWater) {
		super(true, ModConfig.colorList.get(EnumDyeColor.PINK).rgb);
		this.isClearedByWater = isClearedByWater;
		this.setRegistryName("effectPaint");
		this.setPotionName("Painted");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		  mc.getTextureManager().bindTexture(EFFECT_ICON);
	      x += 6;
	      y += 7;

	      int width = 18;
	      int height = width;

	      float minU = 0F;
	      float maxU = 1F;
	      float minV = 0F;
	      float maxV = 1F;

	      Tessellator tessellator = Tessellator.getInstance();
	      VertexBuffer buf = tessellator.getBuffer();
	      buf.begin(7, DefaultVertexFormats.POSITION_TEX);
	      GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
	      buf.pos((double) x,          (double)(y + height), 0.0D).tex(minU, maxV).endVertex();
	      buf.pos((double)(x + width), (double)(y + height), 0.0D).tex(maxU, maxV).endVertex();
	      buf.pos((double)(x + width), (double) y,           0.0D).tex(maxU, minV).endVertex();
	      buf.pos((double) x,          (double) y,           0.0D).tex(minU, minV).endVertex();
	      tessellator.draw();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {

		 x += 3;
		 y += 3;

		 int width = 18;
		 int height = width;

		 float minU = 0F;
	      float maxU = 1F;
	      float minV = 0F;
	      float maxV = 1F;

		 Tessellator tessellator = Tessellator.getInstance();
		 mc.getTextureManager().bindTexture(EFFECT_ICON);

		 VertexBuffer buf = tessellator.getBuffer();
		 buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		 GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
		 buf.pos((double) x,          (double)(y + height), 0.0D).tex(minU, maxV).endVertex();
		 buf.pos((double)(x + width), (double)(y + height), 0.0D).tex(maxU, maxV).endVertex();
		 buf.pos((double)(x + width), (double) y,           0.0D).tex(maxU, minV).endVertex();
		 buf.pos((double) x,          (double) y,           0.0D).tex(minU, minV).endVertex();
		 tessellator.draw();
	}

	public boolean isClearedByWater() {
		return isClearedByWater;
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public boolean isReady(int remaining, int amplifier) {
		return remaining % 5 == 0;
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {

		if(isClearedByWater() && entityLivingBaseIn.isInWater())
			entityLivingBaseIn.addPotionEffect(new PotionEffect(this, 1, 127));


		super.performEffect(entityLivingBaseIn, amplifier);
		if(amplifier >= 0 && amplifier <= 16) {
			BlockPos pos = entityLivingBaseIn.getPosition();
			double rand = entityLivingBaseIn.worldObj.rand.nextDouble();
			EntityPaint paintCloud;
			PaintDuration duration;
			if(rand <= 0.01)
				duration = PaintDuration.LONG;
			else if (rand <= 0.1)
				duration = PaintDuration.MEDIUM;
			else
				duration = PaintDuration.SHORT;
			paintCloud = EntityPaint.getPaintEntity(entityLivingBaseIn.worldObj,
					duration,
					EnumDyeColor.byMetadata(amplifier),
					entityLivingBaseIn);


			entityLivingBaseIn.worldObj.spawnEntityInWorld(paintCloud);
		}
	}
}
