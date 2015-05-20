package com.gpiskun.rambo.preferences;


public abstract class RamboPreferenceItemAdapter<T> implements IRamboPreferenceItem<T> {
	
	private final String name;
	private final T defaultValue;
	private final IRamboPreferenceItem<?> parent;
	
	public RamboPreferenceItemAdapter() {
		this(null, null);
	}
	
	public RamboPreferenceItemAdapter(String name, T defaultValue) {
		this(name, null, defaultValue);
	}
	
	public RamboPreferenceItemAdapter(String name, IRamboPreferenceItem<?> parent, T defaultValue) {
		this.name = name;
		this.parent = parent;
		this.defaultValue = defaultValue;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IRamboPreferenceItem<?> getParent() {
		return parent;
	}

	@Override
	public boolean isEnabled() {
		if (this.parent != null) {
			return this.parent.isEnabled() && getValue() != null;
		}
		return this.getValue() != null;
	}

	@Override
	public T getDefaultValue() {
		return defaultValue;
	}
}
