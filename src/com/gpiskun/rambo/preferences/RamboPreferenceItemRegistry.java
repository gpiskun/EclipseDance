package com.gpiskun.rambo.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.statushandlers.StatusManager;

public final class RamboPreferenceItemRegistry {
	
	private static final RamboPreferenceItemRegistry INSTANCE = new RamboPreferenceItemRegistry();
	
	private final List<IRamboPreferenceItem<?>> preferences;
	
	private RamboPreferenceItemRegistry() {
		preferences = new ArrayList<>();
		preferences.add(new RamboPreferenceCheckboxItem(RamboPreference.RELOAD_TARGET_PLATFORM.name(), true));
		preferences.add(new RamboPreferenceCheckboxItem(RamboPreference.REFRESH_PROJECTS.name(), true));
		preferences.add(new RamboPreferenceCheckboxItem(RamboPreference.CLEAN_ALL_PROJECTS.name(), true));
		
		IRamboPreferenceItem<String> runConfig = new RamboPreferenceTextItem(RamboPreference.RUN_CONFIG.name(), "");
		preferences.add(runConfig);
		
		try {
			preferences.add(new RamboPreferenceCheckboxItem(RamboPreference.RUN_CONFIG_ADD_ALL_BUNDLES.name(), runConfig, RamboPreferenceUtils.hasOsgiLaunchConfigurations()));
			preferences.add(new RamboPreferenceCheckboxItem(RamboPreference.RUN_CONFIG_DELETE_WORK_DIR.name(), runConfig, RamboPreferenceUtils.hasOsgiLaunchConfigurations()));
		} catch (CoreException e) {
			StatusManager.getManager().handle(e.getStatus());
		}
		
	}
	
	public static RamboPreferenceItemRegistry getInstance() {
		return INSTANCE;
	}
	
	public List<IRamboPreferenceItem<?>> getPreferences() {
		return this.preferences;
	}
	
	@SuppressWarnings("unchecked")
	public <T> IRamboPreferenceItem<T> getPreference(String name, Class<T> type) {
		return (IRamboPreferenceItem<T>) preferences.stream().filter(p -> p.getName().equals(name)).findFirst().get();
	}
}
