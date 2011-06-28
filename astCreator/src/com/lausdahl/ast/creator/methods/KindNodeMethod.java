package com.lausdahl.ast.creator.methods;

import com.lausdahl.ast.creator.CommonTreeClassDefinition;
import com.lausdahl.ast.creator.Environment;

public class KindNodeMethod extends Method
{
	public KindNodeMethod(Environment env)
	{
		super(null,env);
	}
	CommonTreeClassDefinition c;
	public KindNodeMethod(CommonTreeClassDefinition c,Environment env)
	{
		super(c,env);
		this.c =c;
	}

	@Override
	protected void prepare()
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append("\t/**\n");
		sb.append("\t * Returns the {@link NodeEnum} corresponding to the\n");
		sb.append("\t * type of this {@link Node} node.\n");
		sb.append("\t * @return the {@link NodeEnum} for this node\n");
		sb.append("\t */");
		this.javaDoc = sb.toString();
		name = "kindNode";
		annotation = "@Override";
		returnType="NodeEnum";
		body = "\t\treturn NodeEnum."+c.getEnumName()+";";

		
		// @Override public NodeEnum kindNode() {
		// return NodeEnum._BINOP;
		// }
	}
	
	@Override
	protected void prepareVdm()
	{
		skip = true;
	}
}
