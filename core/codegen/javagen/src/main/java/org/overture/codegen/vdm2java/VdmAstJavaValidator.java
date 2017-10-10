package org.overture.codegen.vdm2java;

import java.util.List;
import java.util.Set;

import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.analysis.DepthFirstAnalysisAdaptor;
import org.overture.ast.definitions.AClassClassDefinition;
import org.overture.ast.definitions.ARenamedDefinition;
import org.overture.ast.definitions.AStateDefinition;
import org.overture.ast.definitions.SClassDefinition;
import org.overture.ast.expressions.*;
import org.overture.ast.patterns.ATypeBind;
import org.overture.ast.patterns.ATypeMultipleBind;
import org.overture.ast.patterns.PMultipleBind;
import org.overture.codegen.ir.IRInfo;
import org.overture.codegen.ir.VdmNodeInfo;
import org.overture.typechecker.assistant.type.PTypeAssistantTC;

public class VdmAstJavaValidator extends DepthFirstAnalysisAdaptor
{
	private IRInfo info;
	
	public VdmAstJavaValidator(IRInfo info) {
		this.info = info;
	}

	@Override
	public void inAStateDefinition(AStateDefinition node)
			throws AnalysisException
	{
		if (node.getCanBeExecuted() != null && !node.getCanBeExecuted())
		{
			info.addUnsupportedNode(node, String.format("The state definition '%s' is not executable.\n"
					+ "Only an executable state definition can be code generated.", node.getName().getName()));
		}
	}

	@Override
	public void inAClassClassDefinition(AClassClassDefinition node) throws AnalysisException {
		if (node.getSupernames().size() > 1) {
			
			int nonFullyAbstract = 0;
			
			for(SClassDefinition s : node.getSuperDefs())
			{
				if(!info.getDeclAssistant().isFullyAbstract(s, info))
				{
					nonFullyAbstract++;
					if (nonFullyAbstract > 1) {
						info.addUnsupportedNode(node, "Multiple inheritance not supported.");
					}
				}
			}
		}
	}

	@Override
	public void caseACompBinaryExp(ACompBinaryExp node) throws AnalysisException {

		PTypeAssistantTC typeAssistant = info.getTcFactory().createPTypeAssistant();

		if(typeAssistant.isFunction(node.getLeft().getType()) || typeAssistant.isFunction(node.getRight().getType()))
		{
			info.addUnsupportedNode(node, "Function composition is not supported");
		}
	}

	@Override
	public void inAFuncInstatiationExp(AFuncInstatiationExp node)
			throws AnalysisException
	{
		if (node.getImpdef() != null)
		{
			info.addUnsupportedNode(node, "Implicit functions cannot be instantiated since they are not supported.");
		}
	}

	@Override
	public void inARenamedDefinition(ARenamedDefinition node)
			throws AnalysisException
	{
		info.addUnsupportedNode(node, "Renaming of imported definitions is not currently supported");
	}

	@Override
	public void caseAForAllExp(AForAllExp node) throws AnalysisException
	{
		validateQuantifiedExp(node, node.getBindList(), "forall expression");
	}

	@Override
	public void caseAExistsExp(AExistsExp node) throws AnalysisException
	{
		validateQuantifiedExp(node, node.getBindList(), "exists expression");
	}

	@Override
	public void caseAExists1Exp(AExists1Exp node) throws AnalysisException
	{
		if (inUnsupportedContext(node))
		{
			info.addUnsupportedNode(node, String.format("Generation of an %s is only supported within operations/functions", "exists1 expression"));
		}

		if (node.getBind() instanceof ATypeBind)
		{
			info.addUnsupportedNode(node, String.format("Generation of an %s is only supported for set binds or sequence binds", "exists1 expression"));
		}
	}

	private void validateQuantifiedExp(PExp node, List<PMultipleBind> bindings,
			String nodeStr) throws AnalysisException
	{
		if (inUnsupportedContext(node))
		{
			info.addUnsupportedNode(node, String.format("Generation of a %s is only supported within operations/functions", nodeStr));
		}

		for (PMultipleBind mb : bindings)
		{
			mb.apply(this);
		}
	}

	@Override
	public void caseALetDefExp(ALetDefExp node) throws AnalysisException
	{
		if (info.getExpAssistant().isAssigned(node))
		{
			info.addUnsupportedNode(node, "Generation of a let expression is not supported in assignments");
		}
	}

	@Override
	public void caseALetBeStExp(ALetBeStExp node) throws AnalysisException
	{
		if (inUnsupportedContext(node))
		{
			info.addUnsupportedNode(node, "Generation of a let be st expression is only supported within operations/functions");
		}

		node.getBind().apply(this);
	}

	@Override
	public void caseAMapCompMapExp(AMapCompMapExp node) throws AnalysisException
	{
		if (inUnsupportedContext(node))
		{
			info.addUnsupportedNode(node, "Generation of a map comprehension is only supported within operations/functions");
		}

		for (PMultipleBind mb : node.getBindings())
		{
			mb.apply(this);
		}
	}

	@Override
	public void caseASetCompSetExp(ASetCompSetExp node) throws AnalysisException
	{
		if (inUnsupportedContext(node))
		{
			info.addUnsupportedNode(node, "Generation of a set comprehension is only supported within operations/functions");
		}

		for (PMultipleBind mb : node.getBindings())
		{
			mb.apply(this);
		}
	}

	@Override
	public void caseASeqCompSeqExp(ASeqCompSeqExp node) throws AnalysisException
	{
		if (inUnsupportedContext(node))
		{
			info.addUnsupportedNode(node, "Generation of a sequence comprehension is only supported within operations/functions");
			return;
		}
	}

	@Override
	public void caseATimeExp(ATimeExp node) throws AnalysisException
	{
		info.addUnsupportedNode(node, "The 'time' expression is not supported");
	}

	/**
	 * Single type binds are supported for lambda expression, e.g. (lambda x : int &amp; x) so we cannot report all of
	 * the unsupported.
	 */

	@Override
	public void caseATypeMultipleBind(ATypeMultipleBind node)
			throws AnalysisException
	{
		info.addUnsupportedNode(node, "Type binds are not supported");
	}

	private boolean inUnsupportedContext(org.overture.ast.node.INode node)
	{
		return info.getExpAssistant().outsideImperativeContext(node)
				&& !info.getExpAssistant().appearsInModuleStateInv(node);
	}

	public boolean hasUnsupportedNodes()
	{
		return !info.getUnsupportedNodes().isEmpty();
	}

	public Set<VdmNodeInfo> getUnsupportedNodes()
	{
		return info.getUnsupportedNodes();
	}
}
