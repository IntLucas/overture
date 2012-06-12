package org.overture.ast.assistant.pattern;

import org.overture.ast.lex.LexNameList;
import org.overture.ast.patterns.ARecordPattern;
import org.overture.ast.patterns.PPattern;

public class ARecordPatternAssistant {

	

	public static LexNameList getAllVariableNames(ARecordPattern pattern) {
		LexNameList list = new LexNameList();

		for (PPattern p: pattern.getPlist())
		{
			list.addAll(PPatternAssistant.getAllVariableNames(p));
		}

		return list;
		
	}

	
	
}
