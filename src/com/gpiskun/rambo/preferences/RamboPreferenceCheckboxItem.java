package com.gpiskun.rambo.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.Preferences;

import com.gpiskun.rambo.RamboActivator;

public class RamboPreferenceCheckboxItem extends RamboPreferenceItemAdapter<Boolean> {
			
	public RamboPreferenceCheckboxItem(String name, IRamboPreferenceItem<?> parent, boolean defaultValue) {
		super(name, parent, defaultValue);
	}

	public RamboPreferenceCheckboxItem(String name, boolean defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public Boolean getValue() {
		Preferences preferences = InstanceScope.INSTANCE.getNode(RamboActivator.PLUGIN_ID);
		return preferences.getBoolean(getName(), this.getDefaultValue());
	}

	@Override
	public boolean isEnabled() {
		if (this.getParent() != null) {
			return this.getParent().isEnabled() && this.getValue();
		}
		return this.getValue();
	}
}
