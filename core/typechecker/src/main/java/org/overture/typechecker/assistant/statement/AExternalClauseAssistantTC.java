package org.overture.typechecker.assistant.statement;

import org.overture.ast.analysis.QuestionAnswerAdaptor;
import org.overture.ast.statements.AExternalClause;
import org.overture.ast.types.PType;
import org.overture.typechecker.TypeCheckInfo;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;
import org.overture.typechecker.assistant.type.PTypeAssistantTC;

public class AExternalClauseAssistantTC
{
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public AExternalClauseAssistantTC(ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}

	public static void typeResolve(AExternalClause clause,
			QuestionAnswerAdaptor<TypeCheckInfo, PType> rootVisitor,
			TypeCheckInfo question)
	{
		clause.setType(PTypeAssistantTC.typeResolve(clause.getType(), null, rootVisitor, question));

	}

}
