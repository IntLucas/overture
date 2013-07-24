package org.overture.typechecker.assistant.module;

import java.util.List;
import java.util.Vector;

import org.overture.ast.definitions.PDefinition;
import org.overture.ast.factory.AstFactory;
import org.overture.ast.modules.AModuleModules;
import org.overture.ast.modules.ATypeImport;
import org.overture.typechecker.TypeCheckerErrors;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;
import org.overture.typechecker.assistant.definition.PDefinitionListAssistantTC;

public class ATypeImportAssistantTC {
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public ATypeImportAssistantTC(ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}
	public static List<PDefinition> getDefinitions(ATypeImport imp,
			AModuleModules module) {
		
		List<PDefinition> list = new Vector<PDefinition>();
		imp.setFrom(module);
		PDefinition expdef = PDefinitionListAssistantTC.findType(imp.getFrom().getExportdefs(),imp.getName(), null);

		if (expdef == null)
		{
			TypeCheckerErrors.report(3191, "No export declared for import of type " + imp.getName() + " from " + imp.getFrom().getName(),imp.getLocation(),imp);
		}
		else
		{
			if (imp.getRenamed() != null)
			{
				expdef = AstFactory.newARenamedDefinition(imp.getRenamed(),expdef);
			}
			else
			{
				expdef = AstFactory.newAImportedDefinition(imp.getName().getLocation(),expdef);
			}

			list.add(expdef);
		}

		return list;
	}

}
