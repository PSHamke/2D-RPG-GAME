package com.hamke.session.entity;

import java.util.Random;

import com.hamke.session.graphics.Screen;
import com.hamke.session.level.Level;

public abstract class Entity {
	public int x,y;
	public boolean removed = false;
	protected Level level;
	protected final Random random= new Random();
	
	public void update() {	
	}
	
	public void render ( Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level=level;
	}
}
