package edu.jsu.mcis;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

public interface BarGraphEventListener extends EventListener {
	public void barPressed(BarGraphEvent event);
}