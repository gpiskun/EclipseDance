package com.gpiskun.rambo.tasks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;

import com.gpiskun.rambo.preferences.RamboPreferenceField;


public class UpdateRunConfigurationTask implements Runnable {
	
	private static final String START_LEVEL = "default";
	private static final String AUTO_START = "default";
	private static final String TARGET_BUNDLES_ATTR = "target_bundles";
	private static final String WORKSPACE_BUNDLES_ATTR = "workspace_bundles";
	
	@Override
	public void run() {
		try {
			ILaunchConfiguration launchConfiguration = getLaunchConfiguration();
			ILaunchConfigurationWorkingCopy workingCopy = launchConfiguration.getWorkingCopy();
			workingCopy.setAttribute(TARGET_BUNDLES_ATTR, getRunConfigBundles(PluginRegistry.getExternalModels()));
			workingCopy.setAttribute(WORKSPACE_BUNDLES_ATTR, getRunConfigBundles(PluginRegistry.getWorkspaceModels()));
			workingCopy.doSave();
		}
		catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ILaunchConfiguration getLaunchConfiguration() throws CoreException {
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		return launchManager.getLaunchConfiguration(RamboPreferenceField.RUN_CONFIG.getStringValue());
	}
	
	private String getRunConfigBundles(IPluginModelBase[] bundles) {
		List<String> bundleInfo = new ArrayList<String>();
		for (IPluginModelBase model : bundles) {
			bundleInfo.add(model.getBundleDescription().getName() + "@" + START_LEVEL + ":" + AUTO_START);
		}
		return String.join(",", bundleInfo);
	} 
}
