package com.gpiskun.rambo.tasks;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public class CleanAllProjectsTask implements Runnable {

	@Override
	public void run() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IProgressMonitor progressMonitor = (IProgressMonitor) workbench.getService(IProgressMonitor.class);
		
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (IProject project : projects) {
			try {
				project.build(IncrementalProjectBuilder.CLEAN_BUILD, progressMonitor);
			} catch (CoreException e) {
				throw new RuntimeException(e);				
			}
		}
	}
}
