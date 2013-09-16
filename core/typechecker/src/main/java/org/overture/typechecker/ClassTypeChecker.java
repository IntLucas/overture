/*******************************************************************************
 *
 *	Copyright (c) 2008 Fujitsu Services Ltd.
 *
 *	Author: Nick Battle
 *
 *	This file is part of VDMJ.
 *
 *	VDMJ is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	VDMJ is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with VDMJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

package org.overture.typechecker;

import java.util.List;

import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.definitions.ASystemClassDefinition;
import org.overture.ast.definitions.SClassDefinition;
import org.overture.ast.typechecker.Pass;
import org.overture.typechecker.assistant.TypeCheckerAssistantFactory;
import org.overture.typechecker.assistant.definition.PDefinitionAssistantTC;
import org.overture.typechecker.assistant.definition.SClassDefinitionAssistantTC;
import org.overture.typechecker.visitor.TypeCheckVisitor;

/**
 * A class to coordinate all class type checking processing.
 */

public class ClassTypeChecker extends TypeChecker
{
	/** The list of classes to check. */
	private final List<SClassDefinition>  classes;

	/**
	 * Create a type checker with the list of classes passed.
	 *
	 * @param classes
	 */

	public ClassTypeChecker(List<SClassDefinition> classes)
	{
		super();
		this.classes = classes;
	}

	/**
	 * Perform type checking across all classes in the list.
	 * @throws AnalysisException 
	 */

	@Override
	public void typeCheck() 
	{
		boolean nothing = true;
		boolean hasSystem = false;

		for (SClassDefinition c1: classes)
		{
			for (SClassDefinition c2: classes)
			{
				if (c1 != c2 && c1.getName().equals(c2.getName()))
				{
					TypeChecker.report(3426, "Class " + c1.getName() + " duplicates " + c2.getName(), c1.getName().getLocation());
				}
			}

			if (!c1.getTypeChecked()) nothing = false;

			if (c1 instanceof ASystemClassDefinition)
			{
				if (hasSystem)
				{
					TypeChecker.report(3294, "Only one system class permitted", c1.getLocation());
				}
				else
				{
					hasSystem = true;
				}
			}
		}

		if (nothing)
		{
			return;
		}

		Environment allClasses = new PublicClassEnvironment(assistantFactory,classes,null);

		for (SClassDefinition c: classes)
		{
			if (!c.getTypeChecked())
			{
				SClassDefinitionAssistantTC.implicitDefinitions(c, allClasses);
			}
		}

    	for (SClassDefinition c: classes)
		{
			if (!c.getTypeChecked())
			{
    			try
    			{
    				Environment self = new PrivateClassEnvironment(assistantFactory,c, allClasses);
    				SClassDefinitionAssistantTC.typeResolve(c, null, new TypeCheckInfo(new TypeCheckerAssistantFactory(),self));
    			}
    			catch (TypeCheckException te)
    			{
    				report(3427, te.getMessage(), te.location);
    			}
    			catch (AnalysisException te)
				{
					report(3431, te.getMessage(), null);//FIXME: internal error
				}
			}
		}

		for (SClassDefinition c: classes)
		{
			if (!c.getTypeChecked())
			{
				SClassDefinitionAssistantTC.checkOver(c);
			}
		}
		TypeCheckVisitor tc = new TypeCheckVisitor();
	    for (Pass pass: Pass.values())
		{
        	for (SClassDefinition c: classes)
    		{
    			if (!c.getTypeChecked())
    			{
    				try
    				{
    					Environment self = new PrivateClassEnvironment(assistantFactory,c, allClasses);
    	         		SClassDefinitionAssistantTC.typeCheckPass(c,pass, self,tc);
    				}
    				catch (TypeCheckException te)
    				{
    					report(3428, te.getMessage(), te.location);
    				}
    				catch (AnalysisException te)
					{
						report(3431, te.getMessage(), null);//FIXME: internal error
					}
    			}
    		}
		}

    	for (SClassDefinition c: classes)
		{
			if (!c.getTypeChecked())
			{
				SClassDefinitionAssistantTC.initializedCheck(c);
				PDefinitionAssistantTC.unusedCheck(c);
			}
		}
	}
}
