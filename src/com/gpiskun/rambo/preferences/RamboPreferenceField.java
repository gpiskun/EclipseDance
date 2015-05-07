package com.gpiskun.rambo.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.Preferences;

import com.gpiskun.rambo.RamboActivator;

/**
 * Constant definitions for plug-in preferences
 */
public enum RamboPreferenceField {

	RELOAD_TARGET_PLATFORM("Reload Target Platform", Boolean.class),
	REFRESH_PROJECTS("Refresh Projects", Boolean.class),
	CLEAN_ALL_PROJECTS("Clean all projects", Boolean.class),
	RUN_CONFIG("Run Configuration", String.class),
	RUN_CONFIG_ADD_ALL_BUNDLES("Add all bundles from target platform", Boolean.class),
	RUN_CONFIG_DELETE_WORK_DIR("Delete work directory", Boolean.class);
	
	private final String label;
	private final Class<?> type;
	private final Preferences preferences;
	
	private RamboPreferenceField(String label, Class<?> type) {
		this.label = label;
		this.type = type;
		preferences = InstanceScope.INSTANCE.getNode(RamboActivator.PLUGIN_ID);
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public boolean isBoolean() {
		return this.type.equals(Boolean.class);
	}
	
	public boolean isString() {
		return this.type.equals(String.class);
	}
	
	public boolean enabled() {
		return isBoolean() && this.preferences.getBoolean(this.name(), true);
	}
		
	public String getStringValue() {
		return this.preferences.get(this.name(), null);
	}
	
	public Class<?> getType() {
		return this.type;
	}
}
