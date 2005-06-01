/*
 * Created on May 29, 2005
 */
package org.flexdock.perspective;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.flexdock.docking.Dockable;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.DockingPort;
import org.flexdock.docking.state.DockingState;
import org.flexdock.util.DockingUtility;

/**
 * @author Christopher Butler
 */
public class LayoutSequence implements Cloneable, Serializable {
	private ArrayList sequence;
	
	public LayoutSequence() {
		sequence = new ArrayList();
	}
	
	private LayoutSequence(ArrayList list) {
		sequence = list;
	}
	
	public void add(Dockable dockable) {
		add(dockable, null);
	}
	
	public void add(String dockable) {
		add(dockable, null);
	}
	
	public void add(Dockable dockable, Dockable relativeParent) {
		add(dockable, relativeParent, DockingPort.CENTER_REGION, -1.0f);
	}
	
	public void add(String dockable, String relativeParent) {
		add(dockable, relativeParent, DockingPort.CENTER_REGION, -1.0f);
	}
	
	public void add(Dockable dockable, Dockable relativeParent, String region, float ratio) {
		String dockableId = dockable==null? null: dockable.getPersistentId();
		String parentId = relativeParent==null? null: relativeParent.getPersistentId();
		add(dockableId, parentId, region, ratio);
	}
	
	public void add(String dockable, String relativeParent, String region, float ratio) {	
		if(dockable==null)
			return;
		
		if(relativeParent==null && sequence.size()>0)
			throw new IllegalStateException("All calls to add() after the first dockable has been added MUST specify a relative dockable parent.");

		DockingState info = new DockingState(dockable);
		info.setRelativeParent(relativeParent);
		info.setRegion(region);
		info.setSplitRatio(ratio);
		sequence.add(info);
	}
	
	
	public void apply(DockingPort port) {
		if(port==null)
			return;
		
		boolean listen = PerspectiveManager.isDockingStateListening(); 
		PerspectiveManager.setDockingStateListening(false);
		
		
		PerspectiveManager.clear(port);
		int len = sequence.size();
		Dockable[] dockables = new Dockable[len]; 
		for(int i=0; i<len; i++) {
			DockingState info = (DockingState)sequence.get(i);
			Dockable dockable = info.getDockable();
			dockables[i] = dockable;
			String region = info.getRegion();
			if(i==0) {
				DockingManager.dock(info.getDockable(), port, info.getRegion());
				continue;
			}
			
			Dockable parent = info.getRelativeParent();
			float ratio = info.getSplitRatio();
			DockingUtility.dockRelative(parent, dockable, region, ratio);
		}
		
		PerspectiveManager.setDockingStateListening(listen);
		PerspectiveManager.updateDockingStates(dockables);
	}
	
	public Object clone() {
		ArrayList list = new ArrayList(sequence.size());
		for(Iterator it=sequence.iterator(); it.hasNext();) {
			DockingState info = (DockingState)it.next();
			list.add(info.clone());
		}
		return new LayoutSequence(list);
	}

	

	
}