

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.threewaves.eris.engine.BatchConsole;
import org.threewaves.eris.engine.ConfigException;
import org.threewaves.eris.engine.FailedTestCaseException;

public class Test_JenkinsWrapper {	

	@Test
	public void test() throws ConfigException {	
		try {
			BatchConsole.run(2);
		} catch (FailedTestCaseException e) {
			assertTrue(e.getMessage(), false);
		}
	}

}