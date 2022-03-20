package com.hamke.session.level.tile.SpawnLevel;

import com.hamke.session.graphics.Screen;
import com.hamke.session.graphics.Sprite;
import com.hamke.session.level.tile.Tile;

public class SpawnWaterTile extends Tile {
	
	public SpawnWaterTile(Sprite sprite) {
		super(sprite);
		name="Water";
	}
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x<<4, y<<4, this);
	}
}
