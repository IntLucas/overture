package org.overture.typechecker.assistant.definition;

import java.util.List;
import java.util.Vector;

import org.overture.ast.definitions.AImportedDefinition;
import org.overture.ast.definitions.PDefinition;
import org.overture.ast.intf.lex.ILexNameToken;
import org.overture.ast.lex.LexNameList;
import org.overture.ast.typechecker.NameScope;
import org.overture.typechecker.assistant.ITypeCheckerAssistantFactory;

public class AImportedDefinitionAssistantTC {
	protected static ITypeCheckerAssistantFactory af;

	@SuppressWarnings("static-access")
	public AImportedDefinitionAssistantTC(ITypeCheckerAssistantFactory af)
	{
		this.af = af;
	}
	public static PDefinition findType( AImportedDefinition d,
			ILexNameToken sought, String fromModule) {
		// We can only find an import if it is being sought from the module that
		// imports it.
		
		if (fromModule != null && !d.getLocation().getModule().equals(fromModule))		
		{
			return null;	// Someone else's import
		}

		
		PDefinition def = PDefinitionAssistantTC.findType(d.getDef(), sought, fromModule);

		if (def != null)
		{
			PDefinitionAssistantTC.markUsed(d);
		}

		return def;
	}

	public static PDefinition findName( AImportedDefinition d,
			ILexNameToken sought, NameScope scope) {
		
		PDefinition def =  PDefinitionAssistantTC.findName(d.getDef(), sought, scope);

		if (def != null)
		{
			PDefinitionAssistantTC.markUsed(d);
		}

		return def;
	}

	public static void markUsed(AImportedDefinition d) {
		d.setUsed(true);
		PDefinitionAssistantTC.markUsed(d.getDef());
		
	}

	public static List<PDefinition> getDefinitions(AImportedDefinition d) {

		List<PDefinition> result = new Vector<PDefinition>();
		result.add(d.getDef());
		return result;
	}

	public static LexNameList getVariableNames( AImportedDefinition d) {
		return PDefinitionAssistantTC.getVariableNames(d.getDef());
	}

//	public static boolean isUsed(AImportedDefinition u) {
//		return PDefinitionAssistantTC.isUsed(u.getDef());
//		
//	}

}
