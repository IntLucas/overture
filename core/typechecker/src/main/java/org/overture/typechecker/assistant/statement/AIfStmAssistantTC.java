package org.overture.typechecker.assistant.statement;

import org.overture.ast.statements.AElseIfStm;
import org.overture.ast.statements.AIfStm;
import org.overture.ast.util.PTypeSet;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;

public class AIfStmAssistantTC {
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public AIfStmAssistantTC(ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}
	public static PTypeSet exitCheck(AIfStm statement) {
		
		PTypeSet types = new PTypeSet();
		types.addAll(PStmAssistantTC.exitCheck(statement.getThenStm()));

		for (AElseIfStm stmt: statement.getElseIf())
		{
			types.addAll(AElseIfStmAssistantTC.exitCheck(stmt));
		}

		if (statement.getElseStm() != null)
		{
			types.addAll(PStmAssistantTC.exitCheck(statement.getElseStm()));
		}

		return types;
	}

}
