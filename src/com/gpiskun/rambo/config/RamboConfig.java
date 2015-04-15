package com.gpiskun.rambo.config;

public class RamboConfig {
	
	private boolean reloadTargetPlatform;
	private boolean reloadPlugins;
	private boolean cleanAllProjects;
	private boolean refreshPackageExplorer;
	private boolean updateRunConfiguration;
	private boolean deleteWorkDirectory;
	
	public boolean isReloadTargetPlatform() {
		return reloadTargetPlatform;
	}
	
	public void setReloadTargetPlatform(boolean reloadTargetPlatform) {
		this.reloadTargetPlatform = reloadTargetPlatform;
	}
	
	public boolean isReloadPlugins() {
		return reloadPlugins;
	}
	
	public void setReloadPlugins(boolean reloadPlugins) {
		this.reloadPlugins = reloadPlugins;
	}
	
	public boolean isCleanAllProjects() {
		return cleanAllProjects;
	}
	
	public void setCleanAllProjects(boolean compileProjects) {
		this.cleanAllProjects = compileProjects;
	}
	
	public boolean isRefreshPackageExplorer() {
		return refreshPackageExplorer;
	}
	
	public void setRefreshPackageExplorer(boolean refreshPackageExplorer) {
		this.refreshPackageExplorer = refreshPackageExplorer;
	}
	
	public boolean isUpdateRunConfiguration() {
		return updateRunConfiguration;
	}
	
	public void setUpdateRunConfiguration(boolean updateRunConfiguration) {
		this.updateRunConfiguration = updateRunConfiguration;
	}

	public boolean isDeleteWorkDirectory() {
		return deleteWorkDirectory;
	}

	public void setDeleteWorkDirectory(boolean deleteWorkDirectory) {
		this.deleteWorkDirectory = deleteWorkDirectory;
	}
}
