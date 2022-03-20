package com.hamke.session.level.tile;

import com.hamke.session.graphics.Screen;
import com.hamke.session.graphics.Sprite;
import com.hamke.session.level.tile.SpawnLevel.SpawnGrassTile;
import com.hamke.session.level.tile.SpawnLevel.SpawnLavaTile;
import com.hamke.session.level.tile.SpawnLevel.SpawnRockTile;
import com.hamke.session.level.tile.SpawnLevel.SpawnWaterTile;
import com.hamke.session.level.tile.SpawnLevel.SpawnWoodenTile;

public class Tile {
	public int x,y;
	public Sprite sprite;
	public String name;
	
	public static Tile health= new HealthTile(Sprite.health); 
	public static Tile up= new UpTile(Sprite.up);  
	public static Tile voidTile= new VoidTile (Sprite.voidSprite);
	
	public static Tile spawn_grass= new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_rock= new SpawnRockTile(Sprite.spawn_rock);
	public static Tile spawn_water= new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wooden= new SpawnWoodenTile(Sprite.spawn_wooden);
	public static Tile spawn_lava= new SpawnLavaTile(Sprite.spawn_lava);
	public static Tile gold= new GoldTile(Sprite.gold);
	// color defines
	public final static int col_spawn_grass= 0xff27CE67;
	public final static int col_spawn_rock= 0xffC9BBC5;
	public final static int col_spawn_wooden= 0xffC93436;
	public final static int col_spawn_water= 0xff264ACC;
	public final static int col_spawn_lava= 0xffFF0000;
	
	
	public Tile(Sprite sprite) {
		this.sprite= sprite;
	}
	
	public void render (int x, int y, Screen screen) {
		
	}
	public void render (int x, int y, Screen screen,Tile tile) {
		
	}
	
	public boolean solid() {
		return false;
	}
}
