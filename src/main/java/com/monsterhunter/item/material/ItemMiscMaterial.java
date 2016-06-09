package com.monsterhunter.item.material;

public class ItemMiscMaterial<ItemMiscMaterialType> extends RecolorableItem {

	public static enum ItemMiscMaterialType implements ItemMaterialType {
		MYSTERY(0, "mystery"),
		PLANT(1, "plant"),
		MUSHROOM(2, "mushroom"),
		SMOKE(3, "smoke"),
		MAP(4, "map"),
		BOOK(5, "book"),
		HORN(6, "horn"),
		TRAP(7, "trap");
		private static final String PREFIX = "item_";
		private final String unlocalizedName;
		public final int metadata;
		private ItemMiscMaterialType(int metadata, String un) {
			this.unlocalizedName = PREFIX + un;
			this.metadata = metadata;
		}
		@Override
		public String getUnlocalizedName() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public int getMetadata() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public String getShortName() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	private ItemMiscMaterialType type;

	public ItemMiscMaterial(ItemMiscMaterialType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemMiscMaterialType type() {
		return type;
	}

}
