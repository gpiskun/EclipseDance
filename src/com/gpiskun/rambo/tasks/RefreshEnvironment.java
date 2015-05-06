package com.gpiskun.rambo.tasks;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.gpiskun.rambo.preferences.RamboPreferenceField;

public class RefreshEnvironment implements Runnable {
	
	private final Map<RamboPreferenceField, Runnable> taskMap;
	
	public RefreshEnvironment() {
		taskMap = new EnumMap<RamboPreferenceField, Runnable>(RamboPreferenceField.class);
		taskMap.put(RamboPreferenceField.RELOAD_TARGET_PLATFORM, new ReloadTargetPlatformTask());
		taskMap.put(RamboPreferenceField.REFRESH_PACKAGE_EXPLORER, new RefreshPackageExplorerTask());
		taskMap.put(RamboPreferenceField.CLEAN_ALL_PROJECTS, new CleanAllProjectsTask());
		taskMap.put(RamboPreferenceField.UPDATE_RUN_CONFIG, new UpdateRunConfigurationTask());
		taskMap.put(RamboPreferenceField.DELETE_WORK_DIR, new DeleteWorkDirectoryTask());
	}
	
	@Override
	public void run() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		List<Callable<Object>> callableTasks = getEnabledTasks().stream().map(r -> Executors.callable(r)).collect(Collectors.toList());
		
		try {
			executorService.invokeAll(callableTasks);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
	
	private List<Runnable> getEnabledTasks() {
		List<Runnable> result = new ArrayList<>();
		for (RamboPreferenceField field : RamboPreferenceField.values()) {
			if (field.enabled()) {
				result.add(taskMap.get(field));
			}
		}
		return result;
	}	
}
