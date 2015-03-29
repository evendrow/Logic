package com.edwardv.logic.gui.component.gate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.edwardv.entity.draggable.DragHandler;
import com.edwardv.logic.Logic;
import com.edwardv.logic.component.gate.Gate;
import com.edwardv.logic.gui.InputRenderer;
import com.edwardv.logic.gui.LogicElementRenderer;
import com.edwardv.logic.gui.OutputRenderer;

public abstract class GateRenderer extends LogicElementRenderer{

	protected Gate gate;
	protected Image gateImage;
	
	public GateRenderer(DragHandler dragHandler, int x, int y, int inputs, String gateType) {
		super(gateType, dragHandler, x, y, 60, 60);
		
		gate = Logic.getGate(gateType, inputs);
		
		int numInputs = gate.getAcceptedDataWidth(); //Just in case it is a NOT gate
		int distanceBetweenInputs = (int) (height/(numInputs+1));
		input = new InputRenderer[inputs];
		for (int i=0;i<numInputs;i++) {
			input[i] = new InputRenderer(gate, i, this, -(IOSIZE/2), (distanceBetweenInputs*(i+1)) - (IOSIZE/2));
		}
		
		output = new OutputRenderer[] {
				new OutputRenderer(gate, 0, this, width - 2, ((height - IOSIZE)/2))
		};

		gateImage = null;
	}
	
	public GateRenderer(DragHandler dragHandler, int x, int y, int inputs, String gateType, String imageURL) {
		super(gateType, dragHandler, x, y, 60, 60);
		
		gate = Logic.getGate(gateType, inputs);
		
		int numInputs = gate.getAcceptedDataWidth(); //Just in case it is a NOT gate
		int distanceBetweenInputs = (int) (height/(numInputs+1));
		input = new InputRenderer[inputs];
		for (int i=0;i<numInputs;i++) {
			input[i] = new InputRenderer(gate, i, this, -(IOSIZE/2), (distanceBetweenInputs*(i+1)) - (IOSIZE/2));
		}
		
		output = new OutputRenderer[] {
				new OutputRenderer(gate, 0, this, width - 2, ((height - IOSIZE)/2))
		};

		try {
			gateImage = ImageIO.read(new File(imageURL));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		super.tick();
		
		gate.tick();
	}
	
	@Override
	public void render(Graphics g) {
		renderIO(g);
		
		for (int i=0;i<input.length;i++) {
            input[i].render(g);
	    }
	    
	    if (gateImage != null) {
	            g.drawImage(gateImage, (int) x, (int) y, null);
	    } else {
	    	g.setColor(Color.GRAY);
	    	g.fillRect((int) x, (int) y, width, height);
	    }
	    
	    if (gate.getBit(0)) {
	            g.setColor(Color.ORANGE);
	            g.fillOval((int) x + 50, (int) y + 26, 8, 8);
	    }
	    
	    g.setColor(Color.BLACK);
	    g.drawString(getLogicType(), (int) x + 15, (int) y + 33);
	}
}
