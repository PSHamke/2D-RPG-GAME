package com.hamke.session.level.tile.SpawnLevel;

import com.hamke.session.graphics.Screen;
import com.hamke.session.graphics.Sprite;
import com.hamke.session.level.tile.Tile;

public class SpawnGrassTile extends Tile {

	public SpawnGrassTile(Sprite sprite) {
		super(sprite);
		name="Grass";
	}
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x<<4, y<<4, this);
	}
	public void render (int x, int y, Screen screen,Tile tile2) {
		screen.renderTile(x<<4, y<<4, this,tile2);
	}

	public boolean solid() {
		return false;
	}
}
