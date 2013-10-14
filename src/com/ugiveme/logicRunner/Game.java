package com.ugiveme.logicRunner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ugiveme.entity.Entity;
import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.LogicHandler;

public class Game extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static final String title = "Logic FTW!!!!";
	public static final Dimension size = new Dimension(800, 600);
	
	public static Thread gameLoop;
	public static Game game;
	public static JFrame frame;
	
	public boolean running;
	
	public KeyHandler keyHandler;
	public DragHandler dragHandler; 
	public LogicHandler logicHandler;
	
	private List<Entity> entities;
	
	private int ticks = 0;
	private int fps = 0;
	
	public Game() {
		game = this;
		
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setSize(size);
		
		frame = new JFrame();
		
		frame.setTitle(title);
		frame.setSize(size);
		frame.setMinimumSize(size);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
		
		entities = new ArrayList<Entity>();
		
		keyHandler = new KeyHandler(this);
		dragHandler = new DragHandler(this);
		
		logicHandler = new LogicHandler(dragHandler);

	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public synchronized List<Entity> getEntities() {
		return entities;
	}
	
	public synchronized void start() {
		running = true;
		
		gameLoop = new Thread(this, "GameLoop");
		gameLoop.start();
	}
	
	public synchronized void stop() {
		running = false;
		
		try {
			gameLoop.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void tick() {
		for (Entity e : getEntities()) {
			e.tick();
		}
		
		logicHandler.tick();
	}
	
	public synchronized void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (getEntities() != null) {
			for (Entity e : getEntities()) {
				e.render(g);
			}
		}
		
		if (logicHandler != null) {
			logicHandler.render(g);
		}
		
		g.setColor(Color.BLACK);
		g.drawString("fps: " + fps, 50, 25);
	}
	
	public void run() {
		
		int ticksPerSecond = 50;
		int nanoSecondsPerTick = 1000000000/ticksPerSecond;
		
		long timePassed = 0;
		long lastTime = System.nanoTime();
		long now;
		
		long tickTimer = System.currentTimeMillis();
		
		while (running) {
			now = System.nanoTime();
			timePassed += now - lastTime;
			lastTime = now;
			boolean ticked = false;
			
			while (timePassed >= nanoSecondsPerTick) {
				timePassed -= nanoSecondsPerTick;
				ticked = true;
			}
			
			if (ticked) {
				ticks++;
				tick();
				repaint();
			}
			
			if (System.currentTimeMillis() - tickTimer >= 1000) {
				tickTimer += 1000;
				fps = ticks;
				ticks = 0;
			}
			
			try {
				Thread.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
