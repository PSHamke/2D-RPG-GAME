package com.hamke.session;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hamke.session.entity.mob.Dummy;
import com.hamke.session.entity.mob.Health;
import com.hamke.session.entity.mob.Player;
import com.hamke.session.entity.mob.Up;
import com.hamke.session.graphics.Screen;
import com.hamke.session.input.Keyboard;
import com.hamke.session.input.Mouse;
import com.hamke.session.level.Level;
import com.hamke.session.level.RandomLevel;
import com.hamke.session.level.SpawnLevel;
import com.hamke.session.level.TileCoordinate;
import java.awt.BasicStroke;
import java.awt.Stroke;
public class Game extends Canvas  implements Runnable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	// RESOLUTIONS
	public static int width = 300;
	public static int height = width / 16 *9;
	public static int scale =3;
	
	// CREATE NEW THREAD OBJECT
	private Thread thread;
	private boolean running= false;
	private JFrame frame;
	private Level level;
	private Keyboard key;
	private Mouse mouse;
	private Screen screen;
	private Player player;;
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); // Accessing image
	private String playerName;
	private long startTime;
	private long timeSurvived;
	private Dummy[] golds;
	private Health[] healths;
	private Up[] exps;
	private int goldsAmount;
	private int healthsAmount;
	private int expsAmount;
	private int pLevel=1;
	private static final Random random = new Random();
	//CONSTRUCTOR
	public Game() {
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		screen= new Screen(width,height);
		frame =new JFrame();
		key= new Keyboard();
		level=Level.spawn;
		startTime= System.currentTimeMillis();
		TileCoordinate playerSpawn= new TileCoordinate(19,62);  //set initial coord
		player= new Player(playerSpawn.x(),playerSpawn.y(),key);
		player.init(level);
		goldsAmount = 10;
		healthsAmount = 3;
		expsAmount = 5;
		//initialize ornaments
		golds=new Dummy[goldsAmount];
		healths=new Health[healthsAmount];
		exps= new Up[expsAmount];
		for (int i = 0; i<goldsAmount; i++) {
			int rx=0;
			int ry =0;
			while(level.getTile(rx/16, ry/16).name!="Wooden") {
				rx= random.nextInt(40)*16;
				ry=random.nextInt(75)*16;
				
				continue;
			}
			golds[i]= new Dummy(rx>>4,ry>>4);
			golds[i].init(level);
		}
		
		
		for (int i = 0; i<healthsAmount; i++) {
			int rx=0;
			int ry =0;
			while(level.getTile(rx/16, ry/16).name!="Water") {
				rx= random.nextInt(40)*16;
				ry=random.nextInt(75)*16;
				
				continue;
			}
			healths[i]= new Health(rx>>4,ry>>4);
			healths[i].init(level);
		}
		for (int i = 0; i<expsAmount; i++) {
			int rx=0;
			int ry =0;
			while(level.getTile(rx/16, ry/16).name=="Lava"||level.getTile(rx/16, ry/16).name=="Rock") { // Randomly generate but need to check if any ornamant already placed that tile 
				rx= random.nextInt(40)*16;
				ry= random.nextInt(75)*16;
				continue;
			}
			
			exps[i]= new Up(rx>>4,ry>>4);
			exps[i].init(level);
		}
		
		frame.addKeyListener(key);
		mouse= new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
	}
	
	
	// synchronized method to make sure memory consistency
	public synchronized void start() {
		running = true;
		thread = new Thread(this,"Display"); // new Game()
		thread.start();
	}
	// STOP the game
	public synchronized void stop() {
		running =false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public void init(Game game) {
		game.frame.setResizable(false);
		game.frame.setTitle(MyFrame.playerName);
		game.frame.add(game); // add component to the game
		game.frame.pack(); //
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null); //set window place on the screen
		game.frame.setVisible(true);
		game.start();
	}
	// RUN METHOD
	public void run () {
		long lastTime = System.nanoTime();
		long timer= System.currentTimeMillis();
		final double nanoseconds=1000000000.0 / 60.0; //60 UPS
		double delta=0;
		int frames=0;
		int updates=0;
		frame.requestFocus();
		long now1;
		while(running) {
			long now= System.nanoTime();
			delta+= (now-lastTime) / nanoseconds;
			lastTime=now;
			while(delta>=1) { // Updating will be restricted
				
				update();
				delta=0;
				updates++;
			}
			
			render(); // Display wont be restricted
			frames++;
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				
				//System.out.println("Running.. FPS ["+frames+"] UPS ["+updates+"]");
				//System.out.println("Player x " +Player.currentX+ "Player y "+Player.currentY );
				updates=0;
				frames=0;
			}
			//System.out.println("Health "+ Player.health);
			if (Player.health==1000) {
				now1 = System.currentTimeMillis();
				timeSurvived = now1- startTime;
				Player.alive=false;
			}
	
		}
		stop();
	}
	
	public void update() {
		
		key.update();
		player.update();
		for (int i=0; i<goldsAmount; i++) {
			golds[i].update();
		}
		for (int i=0; i<healthsAmount; i++) {
			healths[i].update();
		}
		for (int i=0; i<expsAmount; i++) {
			exps[i].update();
		}
		
	}
	// to avoid graphical issues create a buffer in a render method 
	public void render() {
		
			BufferStrategy bs = getBufferStrategy();
			if ( bs == null) {
				createBufferStrategy(3); // triple buffering 
				return;
			}
			screen.clear();
			int xScroll= player.x - screen.width/2 ; // Initial Player position
			int yScroll= player.y - screen.height/2 ; // Initial Player position
			level.render(xScroll, yScroll, screen);
			player.render(screen);
			for (int i=0; i<goldsAmount; i++) {
				golds[i].render(screen);
			}
			for (int i=0; i<healthsAmount; i++) {
				healths[i].render(screen);
			}
			for (int i=0; i<expsAmount; i++) {
				exps[i].render(screen);
			}
			
			
		
			for (int i=0; i<pixels.length; i++) {
				pixels[i]=screen.pixels[i];
			}
			
			Graphics graphics = bs.getDrawGraphics();
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0,0,getWidth(),getHeight());
			graphics.drawImage(image,0,0,getWidth(),getHeight(),null);
			displayStats(graphics);
			if(!Player.alive) {
				displayGameOver(graphics);
			}
			graphics.dispose(); // dispose all graphics
			bs.show();
		
		
		
	}
	public void displayGameOver(Graphics graphics) {
		Stroke stroke1 = new BasicStroke(6f);	 
		graphics.setColor(Color.BLACK);
		((Graphics2D) graphics).setStroke(stroke1); 
		graphics.drawRect(((width/2)*scale)-200, (height/2)*scale-150, 400, 300);
		graphics.setColor(Color.RED);
        graphics.fillRect(((width/2)*scale)-200, (height/2)*scale-150, 400, 300);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Consolas",Font.PLAIN+Font.BOLD,25));
		graphics.drawString("GAME OVER!", ((width/2)*scale)-65, (height/2)*scale-100);
		graphics.drawString("TOTAL SCORES", ((width/2)*scale)-85, (height/2)*scale);
		graphics.setFont(new Font("Consolas",Font.PLAIN+Font.BOLD,20));
		graphics.drawString("Gold Obtained : "+String.valueOf(Player.gold),((width/2)*scale)-85, (height/2)*scale+90);
		graphics.drawString("Level : "+String.valueOf(pLevel),((width/2)*scale)-85, (height/2)*scale+60);
		graphics.drawString("Time survived : "+ String.valueOf(timeSurvived/1000),((width/2)*scale)-85, (height/2)*scale+120);
	}
	
	public void displayStats(Graphics graphics) {
		pLevel = getLevel((Player.experience),pLevel);
		String playerString="Lv."+pLevel+" "+MyFrame.playerName;
		int nameLength= playerString.length();
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Consolas",Font.PLAIN+Font.BOLD,12));
		graphics.drawString(playerString, ((width/2)*scale)-nameLength*2, (height/2)*scale-50);
		graphics.drawString("Gold : "+ String.valueOf(Player.gold), 10, 20);
		graphics.setColor(Color.WHITE);
		graphics.drawString("Health : "+String.valueOf(Player.health/1000), 10, 40);
		graphics.setColor(Color.WHITE);
		graphics.drawString("Exp : "+String.valueOf(Player.experience), 10, 60);
	}
	
	public int getLevel(int experience, int currentLevel) {
		if ( experience / currentLevel >= currentLevel) {
			return ++currentLevel;
		}else {
			return currentLevel;
		}
	}
	
	// ENTRY POINT
	public static void main(String[] args) {
		
		MyFrame startScreen = new MyFrame (); // Call main screen before game 
			
	}
	
}
