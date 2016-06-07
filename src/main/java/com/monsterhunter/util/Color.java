package com.monsterhunter.util;

public class Color {
	public final int metaval;
	public final int rgb;
	private final String name;
	public Color(int metaval, int rgb, String name) {
		this.metaval = metaval;
		this.rgb   = rgb;
		this.name  = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("%7s (%02d): [%06x]", name, metaval, rgb);
	}
}
