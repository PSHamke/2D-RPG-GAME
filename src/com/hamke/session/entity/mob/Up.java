package com.hamke.session.entity.mob;

import java.util.Random;

import com.hamke.session.graphics.Screen;
import com.hamke.session.graphics.Sprite;
import com.hamke.session.level.tile.Tile;

public class Up extends Mob {
	private static final Random random = new Random();
	public Up (int x,int y) {
		this.x=x<<4;
		this.y=y<<4;
		sprite=Sprite.gold;
	}

	public void update() {
		if(level.getTile(this.x/16, this.y/16).solid()) {
			
		}
		if (Math.abs(Player.currentX-this.x)<16 && Math.abs(Player.currentX-this.x)>0
				&& Math.abs(Player.currentY- this.y)<32 && Math.abs(Player.currentY- this.y)>0 ) {
			Player.experience++;
			while(true) {
				this.x= random.nextInt(40)*16;
				this.y=random.nextInt(75)*16;
				if( level.getTile(this.x/16, this.y/16).name=="Grass") {
					System.out.println("Mob x " +this.x+ "Mob y "+this.y );
					System.out.println(level.getTile(this.x/16, this.y/16).name);
					break;
				}
			}
			
			
		}
	}


	public void render(Screen screen) {
		screen.renderTile(x, y, Tile.up);
	}
}
