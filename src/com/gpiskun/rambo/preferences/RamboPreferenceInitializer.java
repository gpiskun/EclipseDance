package com.gpiskun.rambo.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.gpiskun.rambo.RamboActivator;

/**
 * Class used to initialize default preference values.
 */
public class RamboPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RamboActivator.getDefault().getPreferenceStore();
		for (RamboPreferenceField constant : RamboPreferenceField.values()) {
			store.setDefault(constant.name(), true);
		}
	}

}
