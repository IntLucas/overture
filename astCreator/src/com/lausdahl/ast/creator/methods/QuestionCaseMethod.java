package com.lausdahl.ast.creator.methods;

import com.lausdahl.ast.creator.Environment;
import com.lausdahl.ast.creator.IClassDefinition;
import com.lausdahl.ast.creator.InterfaceDefinition;

public class QuestionCaseMethod extends Method
{
	public QuestionCaseMethod()
	{
		super(null,null);
	}

	public QuestionCaseMethod(IClassDefinition c,Environment env)
	{
		super(c,env);
	}

	@Override
	protected void prepare()
	{
		IClassDefinition c = classDefinition;
		StringBuilder sb = new StringBuilder();
		sb.append("\t/**\n");
		sb.append("\t* Called by the {@link " + c.getName()
				+ "} node from {@link " + c.getName() + "#apply(Switch)}.\n");
		sb.append("\t* @param node the calling {@link " + c.getName()
				+ "} node\n");
		sb.append("\t* @param question the provided question\n");
		sb.append("\t*/");
		this.javaDoc = sb.toString();
		this.name = "case" + InterfaceDefinition.javaClassName(c.getName());
		this.arguments.add(new Argument(c.getName(), "node"));
		this.arguments.add(new Argument("Q", "question"));
		// this.annotation="@override";
		// this.body = "\t\treturn null;";
		// this.returnType="A";
	}
}
