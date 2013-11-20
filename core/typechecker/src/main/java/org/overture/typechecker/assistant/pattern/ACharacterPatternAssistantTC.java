package org.overture.typechecker.assistant.pattern;

import org.overture.ast.expressions.PExp;
import org.overture.ast.factory.AstFactory;
import org.overture.ast.intf.lex.ILexCharacterToken;
import org.overture.ast.lex.LexCharacterToken;
import org.overture.ast.patterns.ACharacterPattern;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;

public class ACharacterPatternAssistantTC
{
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public ACharacterPatternAssistantTC(ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}

//	public static PType getPossibleType(ACharacterPattern pattern)
//	{
//		return AstFactory.newACharBasicType(pattern.getLocation());
//	}

	public static PExp getMatchingExpression(ACharacterPattern chr)
	{
		ILexCharacterToken v = chr.getValue();
		return AstFactory.newACharLiteralExp((LexCharacterToken) v.clone());
	}

}
