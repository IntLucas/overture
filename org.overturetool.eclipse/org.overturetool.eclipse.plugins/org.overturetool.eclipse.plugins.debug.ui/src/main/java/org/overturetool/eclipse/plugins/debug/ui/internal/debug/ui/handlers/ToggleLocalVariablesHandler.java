package org.overturetool.eclipse.plugins.debug.ui.internal.debug.ui.handlers;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.debug.ui.handlers.AbstractToggleLocalVariableHandler;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.overturetool.eclipse.debug.internal.debug.OvertureDebugConstants;


/**
 * Toggles the display of overture local variables in the debug 'Variables'
 * view
 */
public class ToggleLocalVariablesHandler extends
		AbstractToggleLocalVariableHandler {

	/*
	 * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getModelId()
	 */
	protected String getModelId() {
		return OvertureDebugConstants.DEBUG_MODEL_ID;
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getPreferenceStore()
	 */
	protected IPreferenceStore getPreferenceStore() {
		return new PreferencesAdapter(DebugPlugin.getDefault()
				.getPluginPreferences());
	}
}
