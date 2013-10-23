package com.ugiveme.logic;

import com.ugiveme.entity.draggable.DragHandler;
import com.ugiveme.logic.component.gate.*;
import com.ugiveme.logic.gui.LogicElementRenderer;
import com.ugiveme.logic.gui.component.gate.*;
import com.ugiveme.logic.gui.component.receive.LEDMatrixRenderer;
import com.ugiveme.logic.gui.component.receive.LightRenderer;
import com.ugiveme.logic.gui.component.supply.SwitchRenderer;
import com.ugiveme.logicRunner.Game;

public class Logic {
	
	public static int nextId = 1;

	public static int getNextId() {
		return nextId++;
	}
	
	public static LogicElementRenderer getLogicElementRenderer(String type, int x, int y, int[] args) {
		LogicElementRenderer newGate = null;
		DragHandler dragHandler = Game.dragHandler;
		try {
			if (type.equalsIgnoreCase("Or")) {
				newGate = new GateORRenderer(dragHandler, x, y, args[0]);
			} else if (type.equalsIgnoreCase("And")) {
				newGate = new GateANDRenderer(dragHandler, x, y, args[0]);
			} else if (type.equalsIgnoreCase("Xor")) {
				newGate = new GateXORRenderer(dragHandler, x, y, args[0]);
			} else if (type.equalsIgnoreCase("XNor")) {
				newGate = new GateXNORRenderer(dragHandler, x, y, args[0]);
			} else if (type.equalsIgnoreCase("Nor")) {
				newGate = new GateNORRenderer(dragHandler, x, y, args[0]);
			} else if (type.equalsIgnoreCase("Nand")) {
				newGate = new GateNANDRenderer(dragHandler, x, y, args[0]);
			} else if (type.equalsIgnoreCase("Switch")) {
				newGate = new SwitchRenderer(dragHandler, x, y);
			} else if (type.equalsIgnoreCase("Light")) {
				newGate = new LightRenderer(dragHandler, x, y);
			} else if (type.equalsIgnoreCase("Not")) {
				newGate = new GateNOTRenderer(dragHandler, x, y);
			} else if (type.equalsIgnoreCase("LEDMatrix")) {
				newGate = new LEDMatrixRenderer(dragHandler, x, y, args[0], args[1], args[2] == 0 ? false : true);
			}
		} catch(Exception e) {
			newGate = new LogicElementRenderer("Null", dragHandler, x, y, 100, 100);
		}
		
		return newGate;
	}
	
//	public static LogicElement getLogicElement(DragHandler dragHandler, String gateType, int x, int y) {
//		LogicElement newGate = null;
//		if (gateType.equalsIgnoreCase("AO")) {
//			newGate = new AO(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("Or")) {
//			newGate = new OrGate(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("And")) {
//			newGate = new AndGate(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("Xor")) {
//			newGate = new XOrGate(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("XNor")) {
//			newGate = new XNorGate(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("Nor")) {
//			newGate = new NorGate(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("Nand")) {
//			newGate = new NandGate(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("Switch")) {
//			newGate = new OnOffSwitch(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("Light")) {
//			newGate = new Light(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("Not")) {
//			newGate = new Not(nextId++, dragHandler, x, y);
//		} else if (gateType.equalsIgnoreCase("SSD")) {
//			newGate = new SSD(nextId++, dragHandler, x, y);
//		} else {
//			newGate = new LogicElement(nextId++, dragHandler, x, y, 100, 100, "null");
//		}
		
//		return newGate;
//	}
	
	public static Gate getGate(String gateType, int inputs) {
		Gate newGate;
		if (gateType.equalsIgnoreCase("Or")) {
			newGate = new GateOR(inputs);
		} else if (gateType.equalsIgnoreCase("Nor")) {
			newGate = new GateNOR(inputs);
		} else if (gateType.equalsIgnoreCase("And")) {
			newGate = new GateAND(inputs);
		} else if (gateType.equalsIgnoreCase("Nand")) {
			newGate = new GateNAND(inputs);
		} else if (gateType.equalsIgnoreCase("Xor")) {
			newGate = new GateXOR(inputs);
		} else if (gateType.equalsIgnoreCase("XNor")) {
			newGate = new GateXNOR(inputs);
		} else {
			newGate = new GateNOT();
		}
		
		return newGate;
	}
}
