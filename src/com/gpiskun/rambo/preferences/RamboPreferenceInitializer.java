package com.gpiskun.rambo.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.gpiskun.rambo.RamboActivator;

/**
 * Class used to initialize default preference values.
 */
public class RamboPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore preferenceStore = RamboActivator.getDefault().getPreferenceStore();
		for (IRamboPreferenceItem<?> item : RamboPreferenceItemRegistry.getInstance().getPreferences()) {
			Object defaultValue = item.getDefaultValue();
			if (defaultValue.getClass().equals(String.class)) {
				preferenceStore.setDefault(item.getName(), (String) defaultValue);
			}
			else if (defaultValue.getClass().equals(Boolean.class)) {
				preferenceStore.setDefault(item.getName(), (Boolean) defaultValue);
			}
		}
	}
}
