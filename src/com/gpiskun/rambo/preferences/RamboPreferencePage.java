package com.gpiskun.rambo.preferences;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.gpiskun.rambo.RamboActivator;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class RamboPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private static final String OSGI_LAUNCH_CONFIG_ID = "org.eclipse.pde.ui.EquinoxLauncher";
	
	private ComboFieldEditor comboRunconfig;
	private BooleanFieldEditor boolAddAllPlugins;
	private BooleanFieldEditor boolDeleteWorkDirectory;
	
	public RamboPreferencePage() {
		super(GRID);
		setPreferenceStore(RamboActivator.getDefault().getPreferenceStore());
		setDescription("Rambo Plug-In Settings");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	public void createFieldEditors() {
		try {
			createBasicPluginSettings();
			createRunConfigurationSettings();
		}
		catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}
		
	private void createBasicPluginSettings() {
		addField(new BooleanFieldEditor(RamboPreferenceField.RELOAD_TARGET_PLATFORM.name(), RamboPreferenceField.RELOAD_TARGET_PLATFORM.getLabel(), getFieldEditorParent())); 
		addField(new BooleanFieldEditor(RamboPreferenceField.REFRESH_PROJECTS.name(), RamboPreferenceField.REFRESH_PROJECTS.getLabel(), getFieldEditorParent()));
		addField(new BooleanFieldEditor(RamboPreferenceField.CLEAN_ALL_PROJECTS.name(), RamboPreferenceField.CLEAN_ALL_PROJECTS.getLabel(), getFieldEditorParent()));
	}
	
	private void createRunConfigurationSettings() throws CoreException {
		createRunConfigComboEditor();
		createRunConfigActionSettings();
	}
	
	private void createRunConfigComboEditor() throws CoreException {
		String name = RamboPreferenceField.RUN_CONFIG.name();
		String label = RamboPreferenceField.RUN_CONFIG.getLabel();
				
		ILaunchConfiguration[] launchConfigurations = getOsgiLaunchConfigurations();
		
		String[][] entryNamesAndValues = new String[launchConfigurations.length][2];
		for (int i = 0; i < launchConfigurations.length; i++) {
			entryNamesAndValues[i][0] = launchConfigurations[i].getName();
			entryNamesAndValues[i][1] = launchConfigurations[i].getMemento();
		}
						
		comboRunconfig = new ComboFieldEditor(name, label, entryNamesAndValues, getFieldEditorParent());
		addField(comboRunconfig);
	}
	
	private void createRunConfigActionSettings() throws CoreException {
		createAddAllPluginsEditor();
		createDeleteWorkDirectoryEditor();
	}
	
	private void createDeleteWorkDirectoryEditor() throws CoreException {
		boolDeleteWorkDirectory = new BooleanFieldEditor(RamboPreferenceField.RUN_CONFIG_DELETE_WORK_DIR.name(), RamboPreferenceField.RUN_CONFIG_DELETE_WORK_DIR.getLabel(), getFieldEditorParent()); 
		addField(boolDeleteWorkDirectory);
		boolDeleteWorkDirectory.setEnabled(getOsgiLaunchConfigurations().length != 0, getFieldEditorParent());
	}
	
	private void createAddAllPluginsEditor() throws CoreException {
		boolAddAllPlugins = new BooleanFieldEditor(RamboPreferenceField.RUN_CONFIG_ADD_ALL_BUNDLES.name(), RamboPreferenceField.RUN_CONFIG_ADD_ALL_BUNDLES.getLabel(), getFieldEditorParent());
		addField(boolAddAllPlugins);
		boolAddAllPlugins.setEnabled(getOsgiLaunchConfigurations().length != 0, getFieldEditorParent());
	}
	
	private ILaunchConfiguration[] getOsgiLaunchConfigurations() throws CoreException {
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType launchConfigurationType = launchManager.getLaunchConfigurationType(OSGI_LAUNCH_CONFIG_ID);
		return launchManager.getLaunchConfigurations(launchConfigurationType);
	}
}