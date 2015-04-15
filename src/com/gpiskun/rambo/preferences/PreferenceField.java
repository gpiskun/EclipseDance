package com.gpiskun.rambo.preferences;

/**
 * Constant definitions for plug-in preferences
 */
public enum PreferenceField {

	RELOAD_TARGET_PLATFORM("Reload Target Platform"),
	RELOAD_PLUGINS("Reload Plug-Ins"),
	CLEAN_ALL_PROJECTS("Clean all projects"),
	REFRESH_PACKAGE_EXPLORER("Refresh Package Explorer"),
	UPDATE_RUN_CONFIG("Update run configuration"),
	DELETE_WORK_DIR("Delete work directory");
	
	private String label;
	
	private PreferenceField(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
