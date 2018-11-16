package com.ChazTech.SwampThang;

public class Player extends SwampTile {
	int playerHealth = 20;
	
	Player(int x, int y, boolean treasure) {
		super(x, y, treasure);
	}
	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}
}
