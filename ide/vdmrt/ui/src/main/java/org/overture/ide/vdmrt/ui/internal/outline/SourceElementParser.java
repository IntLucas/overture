package org.overture.ide.vdmrt.ui.internal.outline;


import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.overture.ide.vdmrt.core.VdmRtProjectNature;

public class SourceElementParser extends AbstractSourceElementParser
{
	

	@Override
	protected String getNatureId()
	{
		return VdmRtProjectNature.VDM_RT_NATURE;
	}

	@Override
	protected SourceElementRequestVisitor createVisitor()
	{
		return new VdmRtSourceElementRequestor(getRequestor());
	}
	
	

}
