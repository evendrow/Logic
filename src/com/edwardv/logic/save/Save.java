package com.edwardv.logic.save;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.edwardv.logic.Logic;
import com.edwardv.logic.LogicHandler;
import com.edwardv.logic.component.Link;
import com.edwardv.logic.gui.LogicElementRenderer;

public class Save {
	
	public static String exportSave(ArrayList<LogicElementRenderer> logicElements, ArrayList<Link> links) {
		String exported = "";
		
		for (int i=0;i<logicElements.size();i++) {
			exported += logicElements.get(i).getId();
			exported += ":";
			exported += logicElements.get(i).getLogicType();
			exported += ":";
			exported += (int) logicElements.get(i).x;
			exported += ":";
			exported += (int) logicElements.get(i).y;
			exported += ";";
		}
		if (links.size() > 0) {
			exported += "&";
//			for (int i=0;i<links.size();i++) {
//				exported += links.get(i).getOutput().getLogicElement().getId();
//				exported += ":";
//				exported += links.get(i).getInput().getLogicElement().getId();
//				exported += ":";
//				exported += links.get(i).getInput().getIndex();
//				exported += ";";
//			}
		}
		
		JOptionPane.showInputDialog("Save this text:", exported);
		if (!exported.equals("")) {
			System.out.println(exported);
		}
		
		return exported;
	}
	
	public static SaveObject importSave(String save) {
		ArrayList<LogicElementRenderer> logicElements = new ArrayList<LogicElementRenderer>();
		ArrayList<Link> links = new ArrayList<Link>();
		
		try {
			String[] components = save.split("&");
		
			String[] logicInfo = components[0].split(";");
			for (int i=0;i<logicInfo.length;i++) {
				String[] elementInfo = logicInfo[i].split(":");
				//logicElements.add(Logic.getLogicElementRenderer(elementInfo[0], Integer.parseInt(elementInfo[1]), Integer.parseInt(elementInfo[2]), int[] elementInfo));
			}
			
			if (components.length == 2) {
				String[] allLinkInfo = components[1].split(";");
				for (int i=0;i<allLinkInfo.length;i++) {
					String[] linkInfo = allLinkInfo[i].split(":");
					//Output l1 = null;
					//Input l2 = null;
					for (int k=0;k<logicElements.size();k++) {
						if (logicElements.get(k).getId() == Integer.parseInt(linkInfo[0])) {
							//l1 = logicElements.get(k).getOutput();
						} else if (logicElements.get(k).getId() == Integer.parseInt(linkInfo[1])) {
							//l2 = logicElements.get(k).getInput()[Integer.parseInt(linkInfo[2])];
						}
					}
					//if (l1 != null && l2 != null) {
						//links.add(new Link(l1, l2));
					//}
				}
			} 
		} catch (Exception e) {}
		
		return null;//new SaveObject(logicElements, links);
	}

}
