package com.hamke.session.level.tile;

import com.hamke.session.graphics.Screen;
import com.hamke.session.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
		
	}
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x<<4, y<<4, this);
	}
}
