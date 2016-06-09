package com.monsterhunter.entity;

import com.monsterhunter.init.ModConfig;
import com.monsterhunter.util.Color;
import com.monsterhunter.util.ColorList;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityParticleFXPaint extends EntityFX implements IEntityAdditionalSpawnData{

	private static ColorList colors = ModConfig.colorList;
	private static final int parMin = 160;
	private static final int parMax = 167;

	private Color color;

	public EntityParticleFXPaint(World worldIn, EnumDyeColor dyeColor, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn,
				worldIn.rand.nextFloat() * 0.25F,
				-1 * Math.abs(2.5F * worldIn.rand.nextFloat()),
				worldIn.rand.nextFloat() * 0.25F);
		setParticleTextureIndex( (int)Math.floor( worldIn.rand.nextDouble() * (parMax - parMin)) + parMin);
		setMaxAge((int)(1.5F * (8.0F / (worldIn.rand.nextFloat() * 1.8F + 0.2F))));
		particleScale = 2.0F;

		color = colors.get(dyeColor);
		float[] colFloat = color.getFloats();

		setRBGColorF(colFloat[0], colFloat[1], colFloat[2]);
		setAlphaF(0.6F);

	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(color.baseColor.getMetadata());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		color = colors.get(EnumDyeColor.byMetadata(additionalData.readInt()));
	}



}
