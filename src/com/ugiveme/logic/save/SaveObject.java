package com.ugiveme.logic.save;

import java.util.ArrayList;

import com.ugiveme.logic.LogicElement;
import com.ugiveme.logic.component.link.Link;

public class SaveObject {
	
	private ArrayList<LogicElement> logicElements;
	private ArrayList<Link> links;
	
	public SaveObject(ArrayList<LogicElement> logicElements, ArrayList<Link> links) {
		this.logicElements = logicElements;
		this.links = links;
	}
	
	public ArrayList<LogicElement> getLogicElements() {
		return logicElements;
	}
	
	public ArrayList<Link> getLinks() {
		return links;
	}
}
