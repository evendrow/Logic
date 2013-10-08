package com.ugiveme.logic.component.gate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.Input;
import com.ugiveme.logic.component.Output;

public class Gate extends LogicElement{

	public static final int GATESIZE = 60;
	
	private String gateType;
	
	private Image gateImage;
	
	public Gate(DragHandler dragHandler, int x, int y, String gateType) {
		super(dragHandler, x, y, Gate.GATESIZE, Gate.GATESIZE, gateType);
		
		this.gateType = gateType;
		
		this.input = new Input[] {
			new Input(-(IOSIZE/2), 10, IOSIZE, IOSIZE, this),
			new Input(-(IOSIZE/2), GATESIZE - IOSIZE - 10, IOSIZE, IOSIZE, this)
		};
		this.output = new Output((int) width - (IOSIZE/2), (int) ((height - IOSIZE)/2), IOSIZE, IOSIZE, this);
	}
	
	public Gate(DragHandler dragHandler, int x, int y, String gateType, String gateImageURL) {
		super(dragHandler, x, y, Gate.GATESIZE, Gate.GATESIZE, gateType);
		
		this.gateType = gateType;
		
		this.input = new Input[] {
			new Input(-(IOSIZE/2), 10, IOSIZE, IOSIZE, this),
			new Input(-(IOSIZE/2), GATESIZE - IOSIZE - 10, IOSIZE, IOSIZE, this)
		};
		this.output = new Output((int) width - (IOSIZE/2), (int) ((height - IOSIZE)/2), IOSIZE, IOSIZE, this);
		
		try {
			this.gateImage = ImageIO.read(new File(gateImageURL));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		super.tick();
		
		for (int i=0;i<input.length;i++) {
			input[i].tick();
		}
		output.tick();
	}
	
	public void render(Graphics g) {
		g.drawString("Dragging:" + draggingEntity, 300, 40);
		if (gateImage != null) {
			renderWithPicture(g);
		} else {
			g.setColor(new Color(200, 200, 200));
			g.fillRect((int) x, (int) y, width, height);
			
			if (isPowered()) {
				g.setColor(Color.ORANGE);
			} else {
				g.setColor(Color.GRAY);
			}
			g.fillOval((int) x + 10, (int) y + 10, width - 20, height - 20);
			
			g.setColor(Color.BLACK);
			g.drawRect((int) x, (int) y, width, height);
			g.drawString(gateType, (int) x + 20, (int) y + 20);
			
			for (int i=0;i<input.length;i++) {
				input[i].render(g);
			}
			output.render(g);
		}
	}
	
	
	public void renderWithPicture(Graphics g) {
		for (int i=0;i<input.length;i++) {
			input[i].render(g);
		}
		output.render(g);
		
		if (gateImage != null) {
			g.drawImage(gateImage, (int) x, (int) y, null);
		}
		
		if (isPowered()) {
			g.setColor(Color.ORANGE);
			g.fillOval((int) x + 5, (int) y + 15, width - 20, height - 30);
		}
		
		g.setColor(Color.BLACK);
		g.drawString(gateType, (int) x + 10, (int) y + 33);
	}
//	public boolean inputContainsPoint(Point p) {
//		for (int i=0;i<input.length;i++) {
//			if (input[i].contains(p.x - x, p.y - y)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
//	public boolean inputNumContainsPoint(Point p, int inputNum) {
//		if (input[inputNum].contains(p)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
//	public boolean outputContainsPoint(Point p) {
//		return output.contains(p);
//	}
}
