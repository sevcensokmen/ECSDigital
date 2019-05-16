package e2e.steps;

import com.aventstack.extentreports.Status;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import e2e.extentlisteners.ExtentManager;
import e2e.extentlisteners.ExtentTestManager;
import org.testng.Assert;

public class ECSDigitalTestSteps extends BaseSteps {

    private static int scenarioNumber = 0;

    @Before
    public synchronized void before(Scenario scenario) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scenarioNumber = scenarioNumber + 1;
        ExtentTestManager.startTest("Scenario No : " + scenarioNumber + " : " + scenario.getName());
        ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
        setUpFramework();
    }

    @After
    public void after(Scenario scenario) {

        if (scenario.isFailed()) {
            ExtentTestManager.logFail("Scenario Failed");
            ExtentTestManager.addScreenShotsOnFailure();
        } else {
            ExtentTestManager.scenarioPass();
        }
        ExtentManager.getReporter().flush();
        quit();
    }

    @Given("^launch browser '(.*?)'$")
    public void launch_browser(String browserName) {
        openBrowser(browserName);
        ExtentTestManager.logInfo("Browser Opened : " + browserName);
    }

    @And("^user navigates to the URL '(.*?)'$")
    public void user_navigates_to_the_URL(String URL) {
        ecsDigitalTestPage.open(URL);
        ecsDigitalTestPage.clickRenderChallenge();
    }

    @When("^system calculates the results, enters values to the challenge fields and submits answers button$")
    public void system_calculates_the_results_enters_values_to_the_challenge_fields_and_submits_answers_button() {
        ecsDigitalTestPage.setSubmitChallange();
    }

    @Then("^system should be give the message$")
    public void system_should_be_give_the_message() throws Throwable {
        Thread.sleep(5000);
        Assert.assertTrue(ecsDigitalTestPage.takeMessage());
    }

}
