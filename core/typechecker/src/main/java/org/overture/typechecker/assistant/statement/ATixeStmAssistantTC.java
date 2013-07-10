package org.overture.typechecker.assistant.statement;

import java.util.Collection;

import org.overture.ast.statements.ATixeStm;
import org.overture.ast.statements.ATixeStmtAlternative;
import org.overture.ast.types.PType;
import org.overture.ast.util.PTypeSet;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;

public class ATixeStmAssistantTC {
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public ATixeStmAssistantTC(ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}
	public static PTypeSet exitCheck(ATixeStm statement) {
		
		PTypeSet types = new PTypeSet();
		types.addAll(PStmAssistantTC.exitCheck(statement.getBody()));

		for (ATixeStmtAlternative tsa: statement.getTraps())
		{
			types.addAll(exitCheck(tsa));
		}

		return types;
	}

	private static Collection<? extends PType> exitCheck(
			ATixeStmtAlternative tsa) {
		return PStmAssistantTC.exitCheck(tsa.getStatement());
	}

}
