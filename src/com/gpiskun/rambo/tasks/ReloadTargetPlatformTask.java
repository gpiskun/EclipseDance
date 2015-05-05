package com.gpiskun.rambo.tasks;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.LoadTargetDefinitionJob;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public class ReloadTargetPlatformTask implements Runnable {
		
	@Override
	public void run() {
		try {
			ITargetPlatformService service = (ITargetPlatformService) PlatformUI.getWorkbench().getService(ITargetPlatformService.class);
			ITargetDefinition targetDefinition = service.getWorkspaceTargetDefinition();
			
			IWorkbench workbench = PlatformUI.getWorkbench();
			IProgressMonitor progressMonitor = (IProgressMonitor) workbench.getService(IProgressMonitor.class);
			
			targetDefinition.resolve(progressMonitor);
			LoadTargetDefinitionJob.load(targetDefinition);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}	
	}
}
