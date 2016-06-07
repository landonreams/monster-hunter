package com.monsterhunter.init;

import org.apache.logging.log4j.Level;

import com.monsterhunter.MonsterHunter;
import com.monsterhunter.client.gui.GuiMaterialChest;
import com.monsterhunter.container.ContainerMaterialChest;
import com.monsterhunter.tileentity.TileEntityDummyMaterialChest;
import com.monsterhunter.tileentity.TileEntityMaterialChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ModGuiHandler implements IGuiHandler {

	public static final int MATERIAL_CHEST_GUI = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == MATERIAL_CHEST_GUI) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			TileEntityMaterialChest primary = null;
			if(te instanceof TileEntityDummyMaterialChest) {
				pos = ((TileEntityDummyMaterialChest)te).getPrimaryPos();
			} else if (!(te instanceof TileEntityMaterialChest)){
				FMLLog.log(Level.ERROR, "[MonsterHunter] ERROR: Chest at pos %d,%d,%d does not have a proper tile entity!", x, y, z);
				return null;
			}
			primary = (TileEntityMaterialChest) world.getTileEntity(pos);
			return new ContainerMaterialChest(player.inventory, primary);

		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == MATERIAL_CHEST_GUI) {
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity te = world.getTileEntity(pos);
			TileEntityMaterialChest primary = null;
			if(te instanceof TileEntityDummyMaterialChest) {
				pos = ((TileEntityDummyMaterialChest)te).getPrimaryPos();
			} else if (!(te instanceof TileEntityMaterialChest)){
				FMLLog.log(Level.ERROR, "[MonsterHunter] ERROR: Chest at pos %d,%d,%d does not have a proper tile entity!", x, y, z);
				return null;
			}
			primary = (TileEntityMaterialChest) world.getTileEntity(pos);
			return new GuiMaterialChest(player.inventory, primary);
		}
		return null;
	}

}
