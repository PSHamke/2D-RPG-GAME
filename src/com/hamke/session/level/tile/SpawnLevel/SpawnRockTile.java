package com.hamke.session.level.tile.SpawnLevel;

import com.hamke.session.graphics.Screen;
import com.hamke.session.graphics.Sprite;
import com.hamke.session.level.tile.Tile;

public class SpawnRockTile extends Tile {

	
	public SpawnRockTile(Sprite sprite) {
		super(sprite);
		name="Rock";
		
	}
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x<<4, y<<4, this);
	}
	
	public boolean solid() {
		return true;
	}

	
}
