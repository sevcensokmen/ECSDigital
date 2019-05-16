package e2e.runners;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
		features="src/test/resources/features/ecsdigitaltest.feature",
		glue="e2e.steps"
		
		)

public class ECSDigitalRunner {

	@Test
	public void runCukes() {
		
		new TestNGCucumberRunner(getClass()).runCukes();
		
	}

}
