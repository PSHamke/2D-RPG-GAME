package com.hamke.session.graphics;

import com.hamke.session.entity.mob.Player;

public class Sprite {
	public final int SIZE;
	private int x,y;
	public int [] pixels;
	
	private SpriteSheet sheet;
	
	public static Sprite health = new Sprite (16,0,0,SpriteSheet.tiles);
	public static Sprite lava = new Sprite (16,1,1,SpriteSheet.tiles);
	public static Sprite rock = new Sprite (16,0,2,SpriteSheet.tiles);
	public static Sprite tree = new Sprite (16,0,1,SpriteSheet.tiles);
	public static Sprite up = new Sprite (16,1,0,SpriteSheet.tiles);
	public static Sprite wooden = new Sprite (16,1,2,SpriteSheet.tiles);
	public static Sprite gold= new Sprite (16,0,3,SpriteSheet.tiles);
	public static Sprite voidSprite= new Sprite(16,0x1b87e0);
	
	//Spawn Level Sprites
	public static Sprite spawn_grass = new Sprite(16,0,0,SpriteSheet.spawnLevel);
	public static Sprite spawn_rock = new Sprite(16,1,0,SpriteSheet.spawnLevel);
	public static Sprite spawn_wooden = new Sprite(16,0,1,SpriteSheet.spawnLevel);
	public static Sprite spawn_water = new Sprite(16,2,0,SpriteSheet.spawnLevel);
	public static Sprite spawn_lava = new Sprite(16,1,1,SpriteSheet.spawnLevel);
	//Player Sprites
	public static Sprite player_forward= new Sprite(32,(Player.type*3)+3,0,SpriteSheet.tiles);
	public static Sprite player_forward_1= new Sprite (32,(Player.type*3)+3,1,SpriteSheet.tiles);
	public static Sprite player_forward_2= new Sprite (32,(Player.type*3)+3,2,SpriteSheet.tiles);
	
	public static Sprite player_back= new Sprite(32,(Player.type*3)+1,0,SpriteSheet.tiles);
	public static Sprite player_back_1= new Sprite (32,(Player.type*3)+1,1,SpriteSheet.tiles);
	public static Sprite player_back_2= new Sprite (32,(Player.type*3)+1,2,SpriteSheet.tiles);
	
	public static Sprite player_side= new Sprite(32,(Player.type*3)+2,0,SpriteSheet.tiles);
	public static Sprite player_side_1= new Sprite (32,(Player.type*3)+2,1,SpriteSheet.tiles);
	public static Sprite player_side_2= new Sprite (32,(Player.type*3)+2,2,SpriteSheet.tiles);
	
	public Sprite (int size,int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels= new int [SIZE*SIZE];
		this.x = x*size;
		this.y = y*size;
		this.sheet=sheet;
		
		load();
	}
	public Sprite(int size, int color) {
		SIZE= size;
		pixels= new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for ( int i =0; i< SIZE*SIZE; i++) {
			pixels[i]=color;
		}
	}
	private void load() { // Load sprite according to args shift pixel' num
		for (int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				pixels[x+y*SIZE]= sheet.pixels[(x+this.x) +(y+this.y) * sheet.SIZE];
			}
		}
	}

}
