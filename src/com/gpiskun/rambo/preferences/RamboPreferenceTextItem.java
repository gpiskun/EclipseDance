package com.gpiskun.rambo.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.Preferences;

import com.gpiskun.rambo.RamboActivator;

public class RamboPreferenceTextItem extends RamboPreferenceItemAdapter<String> {

	public RamboPreferenceTextItem(String name, String defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public String getValue() {
		Preferences preferences = InstanceScope.INSTANCE.getNode(RamboActivator.PLUGIN_ID);
		return preferences.get(this.getName(), this.getDefaultValue());
	}

	@Override
	public boolean isEnabled() {
		return this.getValue() != null && !this.getValue().isEmpty();
	}
}
