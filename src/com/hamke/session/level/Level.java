package com.hamke.session.level;

import java.util.Random;

import com.hamke.session.graphics.Screen;
import com.hamke.session.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	public static Level spawn= new SpawnLevel ("/resources/textures/Spawn.png");
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt= new int [width*height];
		generateLevel();
	}
	
	public Level (String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void update() {
		
	}
	private void time() {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll); // Actual Location of player
		int x0= (xScroll >> 4) -1; 		// Render one more tile
		int x1= (xScroll + screen.width+16) >> 4;
		int y0= (yScroll>>4) -1;
		int y1= (yScroll + screen.height+16) >> 4;
		
		for (int y=y0;y<y1;y++) {
			for ( int x=x0; x<x1;x++) {
					getTile(x,y).render(x, y, screen);
				
				//System.out.println("Tile name= "+getTile(x,y).name);
				
			}
		}
	}

	
	public Tile getTile(int x, int y) {
		if(x<0 || y<0 || x>= width || y>= height) return Tile.voidTile;
		if (tiles[x+y*width]==Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles[x+y*width]==Tile.col_spawn_rock) return Tile.spawn_rock;
		if (tiles[x+y*width]==Tile.col_spawn_water) return Tile.spawn_water;
		if (tiles[x+y*width]==Tile.col_spawn_wooden) return Tile.spawn_wooden;
		if (tiles[x+y*width]==Tile.col_spawn_lava) return Tile.spawn_lava;
		return Tile.voidTile;
	}
	
}
