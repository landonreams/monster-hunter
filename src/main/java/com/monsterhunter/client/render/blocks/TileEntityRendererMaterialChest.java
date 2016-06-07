package com.monsterhunter.client.render.blocks;

import com.monsterhunter.block.BlockMaterialChest;
import com.monsterhunter.tileentity.TileEntityMaterialChest;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
/**
 * NOT IN USE YET
 * @author Landon
 *
 */
public class TileEntityRendererMaterialChest extends TileEntitySpecialRenderer<TileEntityMaterialChest> {

	private static final ResourceLocation texture = new ResourceLocation("monsterhunter:textures/entity/material_chest.png");
	private ModelChest chestModel = new ModelLargeChest();

	public void renderTileEntityAt(TileEntityMaterialChest te, double x, double y, double z, float partialTicks, int destroyStage){
		GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        ModelChest model = chestModel;

        if(destroyStage >= 0) {
        	this.bindTexture(DESTROY_STAGES[destroyStage]);
        	GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(8.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
        	this.bindTexture(texture);
        }

       EnumFacing facing = te.getWorld().getBlockState(te.getPos()).getValue(BlockMaterialChest.FACING);

       GlStateManager.pushMatrix();
       GlStateManager.enableRescaleNormal();
       GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
       GlStateManager.scale(1.0F, -1.0F, -1.0F);
       GlStateManager.translate(0.5F, 0.5F, 0.5F);

       int rotation;

       switch(facing) {
       case SOUTH: rotation = 0; break;
       case WEST:  rotation = 90; break;
       case NORTH: rotation = 180; break;
       default:    rotation = 270; break;
       }

       GlStateManager.rotate((float)rotation, 0.0F, 1.0F, 0.0F);
       GlStateManager.translate(-0.5F, -0.5F, -0.5F);
//       float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
//
//       f = 1.0F - f;
//       f = 1.0F - f * f * f;
//       model.chestLid.rotateAngleX = -(f * ((float)Math.PI / 2F));
       model.chestLid.rotateAngleX = 0;
       model.renderAll();
       GlStateManager.disableRescaleNormal();
       GlStateManager.popMatrix();
       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

       if (destroyStage >= 0)
       {
           GlStateManager.matrixMode(5890);
           GlStateManager.popMatrix();
           GlStateManager.matrixMode(5888);
       }

	}
}
