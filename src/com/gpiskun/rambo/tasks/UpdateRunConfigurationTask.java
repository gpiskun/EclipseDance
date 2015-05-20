package com.gpiskun.rambo.tasks;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.ui.statushandlers.StatusManager;

import com.gpiskun.rambo.preferences.RamboPreferenceUtils;


public class UpdateRunConfigurationTask implements Runnable {
	
	private static final String TARGET_BUNDLES_ATTR = "target_bundles";
	private static final String WORKSPACE_BUNDLES_ATTR = "workspace_bundles";
	
	@Override
	public void run() {
		try {
			ILaunchConfiguration launchConfiguration = RamboPreferenceUtils.getOsgiLaunchConfiguration();
			
			ILaunchConfigurationWorkingCopy workingCopy = launchConfiguration.getWorkingCopy();
			workingCopy.setAttribute(TARGET_BUNDLES_ATTR, RamboPreferenceUtils.getBundleNames(PluginRegistry.getExternalModels()));
			workingCopy.setAttribute(WORKSPACE_BUNDLES_ATTR, RamboPreferenceUtils.getBundleNames(PluginRegistry.getWorkspaceModels()));
			workingCopy.doSave();
		}
		catch (CoreException e) {
			StatusManager.getManager().handle(e.getStatus());
		}
	}
}
