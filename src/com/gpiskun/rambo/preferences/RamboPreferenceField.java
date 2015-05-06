package com.gpiskun.rambo.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.Preferences;

import com.gpiskun.rambo.RamboActivator;

/**
 * Constant definitions for plug-in preferences
 */
public enum RamboPreferenceField {

	RELOAD_TARGET_PLATFORM("Reload Target Platform"),
	CLEAN_ALL_PROJECTS("Clean all projects"),
	REFRESH_PACKAGE_EXPLORER("Refresh Projects"),
	UPDATE_RUN_CONFIG("Update run configuration"),
	DELETE_WORK_DIR("Delete work directory");
	
	private final String label;
	private final Preferences preferences;
	
	private RamboPreferenceField(String label) {
		this.label = label;
		preferences = InstanceScope.INSTANCE.getNode(RamboActivator.PLUGIN_ID);
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public boolean enabled() {
		return preferences.getBoolean(name(), true);
	}
}
