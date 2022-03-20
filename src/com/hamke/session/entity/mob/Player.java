package com.hamke.session.entity.mob;

import com.hamke.session.graphics.Screen;
import com.hamke.session.graphics.Sprite;
import com.hamke.session.input.Keyboard;

public class Player extends Mob {
	private Keyboard input; 	
	private Sprite sprite;
	private int animate=0;
	private boolean walking= false;
	public static int type;
	public static int currentX,currentY;
	public static int health;
	public static int gold;
	public static int experience;
	public static boolean alive= true;
	public Player(Keyboard input) {	
		this.input= input;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x=x;
		this.y=y;
		this.input = input;
		health=2400;
		gold=0;
		experience=0;
		
	}
	
	public void update() {
		health--;
		int xa=0, ya=0;
		if(animate <8000) animate++;
		else animate=0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		if (alive) {
			if(xa!=0 || ya!=0) {
				walking= true;
				move(xa,ya);
			}else {
				walking=false;
			}
		}
		
	}
	
	public void render(Screen screen) {
		int xx= x-16;
		int yy= y-16;
		int flip= 0;
		if (dir== 0) {
			sprite= Sprite.player_forward;
			if(walking) {
				if (animate%20 > 10) {
					sprite=Sprite.player_forward_1;
				}else {
					sprite=Sprite.player_forward_2;
				}
			}
		}
		if (dir== 1 ||dir == 3) {
			 {
					sprite= Sprite.player_side;
					if(walking) {
						if (animate%20 >10) {
							sprite=Sprite.player_side_1;
						}else {
							sprite=Sprite.player_side_2;
						}
					}
				}
		}
		if (dir== 2) {
			 {
					sprite= Sprite.player_back;
					if(walking) {
						if (animate%20 >10) {
							sprite=Sprite.player_back_1;
						}else {
							sprite=Sprite.player_back_2;
						}
					}
				}
		} 
		
		
		currentX=xx;
		currentY=yy;
		if (dir == 3 ) flip =1; // Flip when player goes to left
		screen.renderPlayer(xx, yy, sprite,flip);
	

	}



}
