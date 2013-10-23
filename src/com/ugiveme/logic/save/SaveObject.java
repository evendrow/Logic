package com.ugiveme.logic.save;

import java.util.ArrayList;

import com.ugiveme.logic.gui.LogicElementRenderer;
import com.ugiveme.logic.gui.component.LinkRenderer;

public class SaveObject {
	
	private ArrayList<LogicElementRenderer> logicElements;
	private ArrayList<LinkRenderer> links;
	
	public SaveObject(ArrayList<LogicElementRenderer> logicElements, ArrayList<LinkRenderer> links) {
		this.logicElements = logicElements;
		this.links = links;
	}
	
	public ArrayList<LogicElementRenderer> getLogicElements() {
		return logicElements;
	}
	
	public ArrayList<LinkRenderer> getLinks() {
		return links;
	}
}
