package org.overture.interpreter.assistant.expression;

import org.overture.ast.expressions.AExistsExp;
import org.overture.ast.expressions.PExp;
import org.overture.ast.patterns.PMultipleBind;
import org.overture.interpreter.assistant.IInterpreterAssistantFactory;
import org.overture.interpreter.assistant.pattern.PMultipleBindAssistantInterpreter;
import org.overture.interpreter.runtime.ObjectContext;
import org.overture.interpreter.values.ValueList;
import org.overture.typechecker.assistant.expression.AExistsExpAssistantTC;

public class AExistsExpAssistantInterpreter extends AExistsExpAssistantTC
{
	protected static IInterpreterAssistantFactory af;

	@SuppressWarnings("static-access")
	public AExistsExpAssistantInterpreter(IInterpreterAssistantFactory af)
	{
		super(af);
		this.af = af;
	}

	public static ValueList getValues(AExistsExp exp, ObjectContext ctxt)
	{
		ValueList list = new ValueList();

		for (PMultipleBind mb : exp.getBindList())
		{
			list.addAll(PMultipleBindAssistantInterpreter.getValues(mb, ctxt));
		}

		list.addAll(PExpAssistantInterpreter.getValues(exp.getPredicate(), ctxt));
		return list;
	}

	public static PExp findExpression(AExistsExp exp, int lineno)
	{
		PExp found = PExpAssistantInterpreter.findExpressionBaseCase(exp, lineno);
		if (found != null)
			return found;

		return PExpAssistantInterpreter.findExpression(exp.getPredicate(), lineno);
	}

}
