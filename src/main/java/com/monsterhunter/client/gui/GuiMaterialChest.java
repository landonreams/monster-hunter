package com.monsterhunter.client.gui;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.container.ContainerMaterialChest;
import com.monsterhunter.tileentity.TileEntityMaterialChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiMaterialChest extends GuiContainer {

	private IInventory playerInv;
	private TileEntityMaterialChest te;

	private final ResourceLocation texture = new ResourceLocation(MonsterHunter.MODID, "/textures/gui/material_chest.png");


	public GuiMaterialChest(IInventory playerInv, TileEntityMaterialChest te) {
		super(new ContainerMaterialChest(playerInv, te));

		this.xSize = 176;
		this.ySize = 221;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.popMatrix();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		GlStateManager.pushMatrix();
	}

}
