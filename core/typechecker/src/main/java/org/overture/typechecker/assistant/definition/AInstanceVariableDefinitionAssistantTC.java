package org.overture.typechecker.assistant.definition;

import org.overture.ast.analysis.QuestionAnswerAdaptor;
import org.overture.ast.definitions.AInstanceVariableDefinition;
import org.overture.ast.definitions.PDefinition;
import org.overture.ast.intf.lex.ILexNameToken;
import org.overture.ast.lex.LexNameList;
import org.overture.ast.typechecker.NameScope;
import org.overture.ast.types.PType;
import org.overture.typechecker.TypeCheckException;
import org.overture.typechecker.TypeCheckInfo;
import org.overture.typechecker.TypeCheckerErrors;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;
import org.overture.typechecker.assistant.type.PTypeAssistantTC;

public class AInstanceVariableDefinitionAssistantTC
{
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public AInstanceVariableDefinitionAssistantTC(
			ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}

	public static PDefinition findName(AInstanceVariableDefinition d,
			ILexNameToken sought, NameScope scope)
	{

		PDefinition found = PDefinitionAssistantTC.findNameBaseCase(d, sought, scope);
		if (found != null)
			return found;
		return scope.matches(NameScope.OLDSTATE)
				&& d.getOldname().equals(sought) ? d : null;
	}

	public static LexNameList getVariableNames(AInstanceVariableDefinition d)
	{
		return new LexNameList(d.getName());
	}

	public static void typeResolve(AInstanceVariableDefinition d,
			QuestionAnswerAdaptor<TypeCheckInfo, PType> rootVisitor,
			TypeCheckInfo question)
	{

		try
		{
			d.setType(PTypeAssistantTC.typeResolve(d.getType(), null, rootVisitor, question));
		} catch (TypeCheckException e)
		{
			PTypeAssistantTC.unResolve(d.getType());
			throw e;
		}

	}

	public static void initializedCheck(AInstanceVariableDefinition ivd)
	{
		if (!ivd.getInitialized()
				&& !PAccessSpecifierAssistantTC.isStatic(ivd.getAccess()))
		{
			TypeCheckerErrors.warning(5001, "Instance variable '"
					+ ivd.getName() + "' is not initialized", ivd.getLocation(), ivd);
		}

	}

}
