package com.gpiskun.rambo.config;

public class RamboConfig {
	
	private boolean reloadTargetPlatform;
	private boolean reloadPlugins;
	private boolean compileProjects;
	private boolean refreshPackageExplorer;
	private boolean updateRunConfiguration;
	
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
	
	public boolean isCompileProjects() {
		return compileProjects;
	}
	
	public void setCompileProjects(boolean compileProjects) {
		this.compileProjects = compileProjects;
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
}
