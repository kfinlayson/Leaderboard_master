package edu.jsu.mcis;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

public class BarGraphEvent extends EventObject{
	public BarGraphEvent(Object source) {
		super(source);
	}
}