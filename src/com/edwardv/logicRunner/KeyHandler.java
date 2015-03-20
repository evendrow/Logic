package com.ugiveme.logicRunner;

import java.awt.event.*;
import java.util.*;

public class KeyHandler implements KeyListener{
	
	public ArrayList<String> keys;
	
	public KeyHandler(Game game) {
		game.addKeyListener(this);
		
		keys = new ArrayList<String>();
	}
	
	public boolean isPressed(String key) {
		return keys.contains(key);
	}
	
	public void removeKey(String key) {
		keys.remove(key);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP && !keys.contains("up")) 
			keys.add("up");
		if (e.getKeyCode() == KeyEvent.VK_DOWN && !keys.contains("down")) 
			keys.add("down");
		if (e.getKeyCode() == KeyEvent.VK_LEFT && !keys.contains("left")) 
			keys.add("left");
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && !keys.contains("right")) 
			keys.add("right");
		if (e.getKeyCode() == KeyEvent.VK_W && !keys.contains("w"))
			keys.add("w");
		
		if (e.getKeyCode() == KeyEvent.VK_S && !keys.contains("s")) 
			keys.add("s");
		if (e.getKeyCode() == KeyEvent.VK_A && !keys.contains("a")) 
			keys.add("a");
		if (e.getKeyCode() == KeyEvent.VK_D && !keys.contains("d")) 
			keys.add("d");
		if (e.getKeyCode() == KeyEvent.VK_Q && !keys.contains("q")) 
			keys.add("q");
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !keys.contains("space")) 
			keys.add("space");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) 
			keys.remove("up");
		if (e.getKeyCode() == KeyEvent.VK_DOWN) 
			keys.remove("down");
		if (e.getKeyCode() == KeyEvent.VK_LEFT) 
			keys.remove("left");
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
			keys.remove("right");
		
		if (e.getKeyCode() == KeyEvent.VK_W) 
			keys.remove("w");
		if (e.getKeyCode() == KeyEvent.VK_S) 
			keys.remove("s");
		if (e.getKeyCode() == KeyEvent.VK_A) 
			keys.remove("a");
		if (e.getKeyCode() == KeyEvent.VK_D) 
			keys.remove("d");
		if (e.getKeyCode() == KeyEvent.VK_Q) 
			keys.remove("q");
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) 
			keys.remove("space");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
