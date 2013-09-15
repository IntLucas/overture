package org.overture.typechecker.assistant.definition;

import java.util.List;
import java.util.Vector;

import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.analysis.QuestionAnswerAdaptor;
import org.overture.ast.assistant.pattern.PTypeList;
import org.overture.ast.definitions.AExplicitFunctionDefinition;
import org.overture.ast.definitions.AStateDefinition;
import org.overture.ast.definitions.PDefinition;
import org.overture.ast.expressions.PExp;
import org.overture.ast.factory.AstFactory;
import org.overture.ast.intf.lex.ILexLocation;
import org.overture.ast.patterns.PPattern;
import org.overture.ast.typechecker.NameScope;
import org.overture.ast.types.AFieldField;
import org.overture.ast.types.AFunctionType;
import org.overture.ast.types.PType;
import org.overture.typechecker.Environment;
import org.overture.typechecker.TypeCheckException;
import org.overture.typechecker.TypeCheckInfo;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;
import org.overture.typechecker.assistant.type.AFieldFieldAssistantTC;
import org.overture.typechecker.assistant.type.PTypeAssistantTC;

public class AStateDefinitionAssistantTC
{
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public AStateDefinitionAssistantTC(ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}

	public static void unusedCheck(AStateDefinition d)
	{

		PDefinitionListAssistantTC.unusedCheck(d.getStateDefs());
	}

	public static List<PDefinition> getDefinitions(AStateDefinition d)
	{
		return d.getStateDefs();
	}
	public static void typeResolve(AStateDefinition d,
			QuestionAnswerAdaptor<TypeCheckInfo, PType> rootVisitor,
			TypeCheckInfo question) throws AnalysisException
	{

		for (AFieldField f : d.getFields())
		{
			try
			{
				AFieldFieldAssistantTC.typeResolve(f, null, rootVisitor, question);
			} catch (TypeCheckException e)
			{
				AFieldFieldAssistantTC.unResolve(f);
				throw e;
			}
		}

		d.setRecordType(PTypeAssistantTC.typeResolve(d.getRecordType(), null, rootVisitor, question));

		if (d.getInvPattern() != null)
		{
			PDefinitionAssistantTC.typeResolve(d.getInvdef(), rootVisitor, question);
		}

		if (d.getInitPattern() != null)
		{
			PDefinitionAssistantTC.typeResolve(d.getInitdef(), rootVisitor, question);
		}

	}

	public static void implicitDefinitions(AStateDefinition d, Environment env)
	{
		if (d.getInvPattern() != null)
		{
			d.setInvdef(getInvDefinition(d));
		}

		if (d.getInitPattern() != null)
		{
			d.setInitdef(getInitDefinition(d));
		}

	}

	private static AExplicitFunctionDefinition getInitDefinition(
			AStateDefinition d)
	{
		ILexLocation loc = d.getInitPattern().getLocation();
		List<PPattern> params = new Vector<PPattern>();
		params.add(d.getInitPattern().clone());

		List<List<PPattern>> parameters = new Vector<List<PPattern>>();
		parameters.add(params);

		PTypeList ptypes = new PTypeList();
		ptypes.add(AstFactory.newAUnresolvedType(d.getName()));
		AFunctionType ftype = AstFactory.newAFunctionType(loc, false, ptypes, AstFactory.newABooleanBasicType(loc));

		PExp body = AstFactory.newAStateInitExp(d);

		AExplicitFunctionDefinition def = AstFactory.newAExplicitFunctionDefinition(d.getName().getInitName(loc), NameScope.GLOBAL, null, ftype, parameters, body, null, null, false, null);

		return def;
	}

	private static AExplicitFunctionDefinition getInvDefinition(
			AStateDefinition d)
	{

		ILexLocation loc = d.getInvPattern().getLocation();
		List<PPattern> params = new Vector<PPattern>();
		params.add(d.getInvPattern().clone());

		List<List<PPattern>> parameters = new Vector<List<PPattern>>();
		parameters.add(params);

		PTypeList ptypes = new PTypeList();
		ptypes.add(AstFactory.newAUnresolvedType(d.getName()));
		AFunctionType ftype = AstFactory.newAFunctionType(loc, false, ptypes, AstFactory.newABooleanBasicType(loc));

		return AstFactory.newAExplicitFunctionDefinition(d.getName().getInvName(loc), NameScope.GLOBAL, null, ftype, parameters, d.getInvExpression(), null, null, true, null);
	}

}
