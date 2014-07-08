package org.overture.pog.tests.newtests;

import static org.junit.Assert.fail;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.node.INode;
import org.overture.core.tests.ParamStandardTest;
import org.overture.core.tests.PathsProvider;
import org.overture.pog.pub.IProofObligationList;
import org.overture.pog.pub.ProofObligationGenerator;

import com.google.gson.reflect.TypeToken;

@RunWith(Parameterized.class)
public class PogLegacyTest extends ParamStandardTest<PogTestResult>
{

	private final static String LEGACY_ADAPTED_ROOT = "src/test/resources/old/adapted";
	private final static String LEGACY_DIRECT_ROOT = "src/test/resources/old/direct";

	private static final String UPDATE_PROPERTY = "tests.update.pog.legacy";

	public PogLegacyTest(String nameParameter, String testParameter,
			String resultParameter)
	{
		super(nameParameter, testParameter, resultParameter);
		updateResult=true;
	}

	@Parameters(name = "{index} : {0}")
	public static Collection<Object[]> testData()
	{
		return PathsProvider.computePaths(LEGACY_ADAPTED_ROOT, LEGACY_DIRECT_ROOT);
	}

	@Override
	public PogTestResult processModel(List<INode> ast)
	{
		try
		{
			IProofObligationList ipol = ProofObligationGenerator.generateProofObligations(ast);
			return PogTestResult.convert(ipol);
		} catch (AnalysisException e)
		{
			fail("Could not process test file " + testName);

		}
		return null;
	}

	@Override
	protected String getUpdatePropertyString()
	{
		return UPDATE_PROPERTY;
	}

	@Override
	public void compareResults(PogTestResult actual, PogTestResult expected)
	{
		PogTestResult.compare(actual, expected);
	}

	@Override
	public Type getResultType()
	{
		Type resultType = new TypeToken<PogTestResult>()
		{
		}.getType();
		return resultType;
	}

}
