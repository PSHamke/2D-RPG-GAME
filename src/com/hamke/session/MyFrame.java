package com.hamke.session;

import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hamke.session.entity.mob.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;
import java.awt.GradientPaint;

public class MyFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JButton button;
	private JTextField textField;
	private JButton mPlayer;
	private JButton fPlayer;
	public static String playerName="";
	MyFrame(){
		this.setUndecorated(true);
		this.setLayout(new FlowLayout());
		JPanel panel = new SplashPanel();
		this.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		int pWidth =panel.getWidth();
		int pHeight= panel.getHeight();
		button = new JButton("START");  // Create buttons
	    button.setBounds((pWidth/2)-40,pHeight-60,80,50);
		button.addActionListener(this);
		button.setEnabled(false);
		mPlayer=new JButton(new ImageIcon(this.getClass().getResource("/resources/textures/maleButton.png")));    
		mPlayer.setBounds((pWidth/2)-75,225,50,70);
		mPlayer.addActionListener(this);
		fPlayer=new JButton(new ImageIcon(this.getClass().getResource("/resources/textures/femaleButton.png")));    
		fPlayer.setBounds((pWidth/2)+25,225,50,70);
		fPlayer.addActionListener(this);
		textField = new JTextField();  // Create player name text field
		textField.setPreferredSize(new Dimension(250,40));
		textField.setFont(new Font("Consolas",Font.PLAIN,30));
		textField.setForeground(new Color(0x00FF00));
		textField.setBackground(Color.BLACK);
		textField.setCaretColor(Color.WHITE);
		textField.setText("Player Name");
		textField.setBounds((pWidth/2)-125,pHeight-125,250,40);
		panel.add(textField);
		panel.add(button);
		panel.add(fPlayer);
		panel.add(mPlayer);
		this.pack();
		this.setLocationRelativeTo(null); // Set location to the center of screen
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {  
		if(e.getSource()==mPlayer) {  // if male button clicked
			Player.type=0;
			button.setEnabled(true);
			mPlayer.setEnabled(false);
			fPlayer.setEnabled(true);

		}
		if(e.getSource()==fPlayer) { // if female button clicked
			Player.type=1;
			button.setEnabled(true);
			fPlayer.setEnabled(false);
			mPlayer.setEnabled(true);
		}
		if(e.getSource()==button) { // if start button clicked
			playerName= textField.getText(); // read textfield then initialize player name
			Game game= new Game(); // call game constructor
			game.init(game); // init the game
			this.dispose(); // dispose this screen so switch to game screen
		}
		
	}
	
}

class SplashPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private int pWidth;
	private int pHeight;
	public int ang =0;
	public SplashPanel() {
		pWidth=Game.width*Game.scale;
		pHeight=Game.height*Game.scale;
		setPreferredSize(new Dimension(pWidth,pHeight));
		setBackground(new Color(0xDFFF00));
		Thread thread= new Thread (this);
		thread.start();
	}
	
	public int getWidth() {
		return pWidth;
	}
	public int getHeight() {
		return pHeight;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Crate animation of splash screen with rotated squares in a both side
		Rectangle2D rect = new Rectangle2D.Double(100,100,400,400);
		AffineTransform tx = new AffineTransform();
		tx.scale(0.1,0.1);
		tx.rotate(Math.toRadians(ang),100+200,100+200);
		Shape shape = tx.createTransformedShape(rect);
		Rectangle2D rect2 = new Rectangle2D.Double((Game.width*Game.scale)*10-500,100,400,400);
		AffineTransform tx2 = new AffineTransform();
		tx2.scale(0.1,0.1);
		tx2.rotate(Math.toRadians(ang),rect2.getX()+200, rect2.getY()+200);
		Shape shape2 = tx2.createTransformedShape(rect2);
		g2.setColor(Color.RED);
		g2.fill(shape);
		g2.fill(shape2);
		// Substract ellipse from rectangle 
		int w = this.getWidth();
		int h = this.getHeight();
		int r = Math.min(w, h) - 100;
		//System.out.println(w+" - "+h+" - "+r);
		Area a = new Area(new Rectangle(50,100,w-100, h-10));
		a.subtract(new Area(new Ellipse2D.Double(260,120, r, r/2)));
		GradientPaint paint = new GradientPaint(0,0, Color.RED, 0, r/2,
		Color.CYAN, true); 
		g2.setPaint(paint);
		g2.fill(a);
		g2.setColor(Color.black);
		g2.draw(a);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Slab Serif", Font.BOLD, 20));
		g2.drawString("CHOSE YOUR CHARACTER",320,200); // Draw releated strings
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Monospaced", Font.BOLD+Font.ITALIC, 50));
		g2.drawString("GENIUS ADVENTURE",220,50); // Draw releated strings
	}

	
	public void run() { // Thread to rotate squares in a first screen
		while (true) {
			ang+=45%360;
			repaint();
			try {
				Thread.sleep(50);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
}
