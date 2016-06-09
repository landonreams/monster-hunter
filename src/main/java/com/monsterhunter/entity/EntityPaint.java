package com.monsterhunter.entity;

import com.monsterhunter.MonsterHunter;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityPaint extends EntityLiving  implements IEntityAdditionalSpawnData{

	/** Interval at which a particle is spawned. */
	private int maxLife;

	/** Default max life is 2 minutes.*/
	public final static int ABSOLUTE_MAX = 1200;

	public EnumDyeColor color;

	private Entity victim;

	public static enum PaintDuration {
		SHORT(60), MEDIUM(600), LONG(2400);
		public final int pticks;
		private PaintDuration(int ticks) {
			this.pticks = ticks;
		}
	}

	public EntityPaint(World worldIn) {
		this(worldIn, PaintDuration.SHORT.pticks, EnumDyeColor.BLACK, null);
	}

	public EntityPaint(World worldIn, int maxLife, EnumDyeColor color, Entity entity) {
		super(worldIn);
		this.maxLife = maxLife;
		this.setEntityInvulnerable(true);

		this.victim = entity;

		BlockPos entityPos;
		float x, y, z;
		if(victim == null) {
			entityPos = new BlockPos(0, 0, 0);
			x = 0;
			y = 0;
			z = 0;
		} else {
			entityPos =  victim.getPosition();
			x = entityPos.getX() + victim.width / 2;
			y = entityPos.getY() + victim.height / 2;
			z = entityPos.getZ() + victim.width / 2;
		}
		this.setPosition(x, y, z);

		this.color = color;

	}

	public static EntityPaint getPaintEntity(World worldIn, PaintDuration duration, EnumDyeColor color, Entity entity) {
		return new EntityPaint(worldIn, duration.pticks, color, entity);
	}


	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
	}

	@Override
	public void onUpdate() {
		if(this.ticksExisted > 0 && this.ticksExisted % 5 == 0) {
			MonsterHunter.proxy.generatePaintParticle(this, color);
		}

		if(this.ticksExisted > maxLife || this.ticksExisted > ABSOLUTE_MAX ) {
			this.setDead();
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(color.getMetadata());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		color = EnumDyeColor.byMetadata(additionalData.readInt());
	}
}
