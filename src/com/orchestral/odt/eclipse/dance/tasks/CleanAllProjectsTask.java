package com.orchestral.odt.eclipse.dance.tasks;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

public class CleanAllProjectsTask implements Runnable {

	@Override
	public void run() {
		try {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IProgressMonitor progressMonitor = (IProgressMonitor) workbench.getService(IProgressMonitor.class);
			
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			for (IProject project : projects) {
				project.build(IncrementalProjectBuilder.CLEAN_BUILD, progressMonitor);
			} 
		}
		catch (CoreException e) {
			StatusManager.getManager().handle(e.getStatus());				
		}
	}
}
