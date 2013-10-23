package com.ugiveme.logic.gui.component.gate;

import com.ugiveme.entity.draggable.DragHandler;

public class GateNOTRenderer extends GateRenderer{
	
	public GateNOTRenderer(DragHandler dragHandler, int x, int y) {
		super(dragHandler, x, y, 1, "NOT", "res/NotGate.png");
	}
	
//	@Override
//	public void render(Graphics g) {
//		renderIO(g);
//		
//		g.setColor(new Color(200, 200, 200));
//		g.fillPolygon(new int[] {(int) x, (int) x + width, (int) x}, new int[] {(int) y, (int) y + (height/2), (int) y + height}, 3);
//		g.setColor(Color.BLACK);
//		g.drawPolygon(new int[] {(int) x, (int) x + width, (int) x}, new int[] {(int) y, (int) y + (height/2), (int) y + height}, 3);
//		g.drawString(getLogicType(), (int) x + 5, (int) y + 30);
//	}
}
