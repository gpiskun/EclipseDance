package com.gpiskun.rambo.preferences;


/**
 * Constant definitions for plug-in preferences
 */
public enum RamboPreference {

	RELOAD_TARGET_PLATFORM("Reload Target Platform", Boolean.class),
	REFRESH_PROJECTS("Refresh Projects", Boolean.class),
	CLEAN_ALL_PROJECTS("Clean all projects", Boolean.class),
	RUN_CONFIG("Run Configuration", String.class),
	RUN_CONFIG_ADD_ALL_BUNDLES("Add all bundles from target platform", Boolean.class),
	RUN_CONFIG_DELETE_WORK_DIR("Delete work directory", Boolean.class);
	
	private final String label;
	
	private RamboPreference(String label, Class<?> type) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
