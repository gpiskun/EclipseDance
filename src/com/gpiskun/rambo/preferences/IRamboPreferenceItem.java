package com.gpiskun.rambo.preferences;


public interface IRamboPreferenceItem<T> {
	
	String getName();
	
	T getValue();
	
	boolean isEnabled();
	
	IRamboPreferenceItem<?> getParent();
	
	T getDefaultValue();
}
