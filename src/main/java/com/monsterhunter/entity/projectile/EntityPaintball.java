package com.monsterhunter.entity.projectile;

import com.monsterhunter.init.ModConfig;
import com.monsterhunter.potion.PotionPaint;
import com.monsterhunter.util.Color;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityPaintball extends EntityThrowable implements IEntityAdditionalSpawnData {

	public Color color;

	public EntityPaintball(World worldIn) {
		super(worldIn);
		color = ModConfig.colorList.get(EnumDyeColor.BLACK);
	}

	public EntityPaintball(World worldIn, EntityLivingBase throwerIn, EnumDyeColor colorType) {
		super(worldIn, throwerIn);
		color = ModConfig.colorList.get(colorType);
	}

	public EntityPaintball(World worldIn, EnumDyeColor colorType, double x, double y, double z) {
		super(worldIn, x, y, z);
		color = ModConfig.colorList.get(colorType);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entityHit != null && result.entityHit instanceof EntityLiving) {
			EntityLiving resultLiving = (EntityLiving)result.entityHit;
			PotionEffect pe = new PotionEffect(new PotionPaint(true), 1200 , color.baseColor.getMetadata(), false, false);

			resultLiving.addPotionEffect(pe);
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(color.baseColor.getMetadata());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		color = ModConfig.colorList.get(EnumDyeColor.byMetadata(additionalData.readInt()));
	}

}
