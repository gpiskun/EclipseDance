package com.gpiskun.rambo.tasks;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.gpiskun.rambo.RamboActivator;
import com.gpiskun.rambo.preferences.IRamboPreferenceItem;
import com.gpiskun.rambo.preferences.RamboPreferenceCheckboxItem;
import com.gpiskun.rambo.preferences.RamboPreferenceItemRegistry;
import com.gpiskun.rambo.preferences.RamboPreference;

public class RefreshEnvironmentTask implements Runnable {
	
	private final Map<RamboPreference, Runnable> taskMap;
	
	public RefreshEnvironmentTask() {
		taskMap = new EnumMap<RamboPreference, Runnable>(RamboPreference.class);
		taskMap.put(RamboPreference.RELOAD_TARGET_PLATFORM, new ReloadTargetPlatformTask());
		taskMap.put(RamboPreference.REFRESH_PROJECTS, new RefreshProjectsTask());
		taskMap.put(RamboPreference.CLEAN_ALL_PROJECTS, new CleanAllProjectsTask());
		taskMap.put(RamboPreference.RUN_CONFIG_ADD_ALL_BUNDLES, new UpdateRunConfigurationTask());
		taskMap.put(RamboPreference.RUN_CONFIG_DELETE_WORK_DIR, new DeleteWorkDirectoryTask());
	}
	
	@Override
	public void run() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		List<Callable<Object>> callableTasks = getEnabledTasks().stream().map(r -> Executors.callable(r)).collect(Collectors.toList());
		
		try {
			executorService.invokeAll(callableTasks);
		} catch (InterruptedException e) {
			IStatus status = new Status(IStatus.ERROR, RamboActivator.PLUGIN_ID, "Environment reloading failed", e);
			StatusManager.getManager().handle(status);
		}
	}
	
	private List<Runnable> getEnabledTasks() {
		List<Runnable> result = new ArrayList<>();
		RamboPreferenceItemRegistry preferenceRegistry = RamboPreferenceItemRegistry.getInstance();
		for (IRamboPreferenceItem<?> preferenceItem : preferenceRegistry.getPreferences()) {
			if (preferenceItem instanceof RamboPreferenceCheckboxItem && preferenceItem.isEnabled()) {
				result.add(taskMap.get(RamboPreference.valueOf(preferenceItem.getName())));
			}
		}
		return result;
	}	
}
