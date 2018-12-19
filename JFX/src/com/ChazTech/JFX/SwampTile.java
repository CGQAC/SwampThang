package com.ChazTech.JFX;

public class SwampTile {
	private int x; private int y; private boolean treasure;
	SwampTile(int x, int y, boolean treasure) {
		this.x = x;
		this.y = y;
		this.treasure = treasure;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isTreasure() {
		return treasure;
	}
	public void setTreasure(boolean treasure) {
		this.treasure = treasure;
	}
}