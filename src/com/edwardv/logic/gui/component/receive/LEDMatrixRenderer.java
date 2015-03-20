package com.ugiveme.logic.gui.component.receive;

import java.awt.Color;
import java.awt.Graphics;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.receive.LEDMatrix;
import com.ugiveme.logic.gui.InputRenderer;
import com.ugiveme.logic.gui.LogicElementRenderer;

public class LEDMatrixRenderer extends LogicElementRenderer{

	private static final int MATRIXLEDSIZE = 15;
	private static final int MATRIXLEDINPUTSIZE = 10;
	
	private LEDMatrix ledMatrix;
	
	public LEDMatrixRenderer(DragHandler dragHandler, int x, int y, int width, int height, boolean type) {
		super("LEDMatrix", dragHandler, x, y, width*MATRIXLEDSIZE, height*MATRIXLEDSIZE);
		
		ledMatrix = new LEDMatrix(width, height, type);
		
		boolean[] pins = new boolean[ledMatrix.getAcceptedDataWidth()];
		for (int i=0;i<pins.length;i++) {
			pins[i] = false;
		}
		ledMatrix.setData(pins);
		
		input = new InputRenderer[ledMatrix.getAcceptedDataWidth()];
		for (int i=0;i<width;i++) {
			input[i] = new InputRenderer(ledMatrix, i, this, i*MATRIXLEDSIZE + ((MATRIXLEDSIZE-IOSIZE)/2), -IOSIZE, MATRIXLEDINPUTSIZE, MATRIXLEDINPUTSIZE);
		}
		for (int i=0;i<height;i++) {
			input[width + i] = new InputRenderer(ledMatrix, i + width, this, -IOSIZE, i*MATRIXLEDSIZE + ((MATRIXLEDSIZE-IOSIZE)/2), MATRIXLEDINPUTSIZE, MATRIXLEDINPUTSIZE);
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		
		ledMatrix.tick();
	}
	
	public void render(Graphics g) {
		renderIO(g);
		
		g.setColor(new Color(200, 200, 200));
		g.fillRect((int) x, (int) y, width, height);
		
		boolean[][] matrix = ledMatrix.getMatrix();
		for (int i=0;i<matrix.length;i++) {
			for (int k=0;k<matrix[0].length;k++) {
				if (matrix[i][k]) {
					g.setColor(Color.GREEN);
					g.fillRect((int) x + i*MATRIXLEDSIZE, (int) y + k*MATRIXLEDSIZE, MATRIXLEDSIZE, MATRIXLEDSIZE);
				}
				g.setColor(Color.BLACK);
				g.drawRect((int) x + i*MATRIXLEDSIZE, (int) y + k*MATRIXLEDSIZE, MATRIXLEDSIZE, MATRIXLEDSIZE);
			}
		}
	}
}
