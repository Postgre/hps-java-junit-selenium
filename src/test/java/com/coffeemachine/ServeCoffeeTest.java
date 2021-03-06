package com.coffeemachine;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ServeCoffeeTest extends TestCase {
    // Tags: sprint:1 JIRA:CMM-1
    public Actionwords actionwords;
    public WebDriver driver;
    private CBTHelper cbt;
    public String score = "fail";
    public String featureName = "Serve coffee";

    protected void setUp() throws Exception {
        super.setUp();
        actionwords = new Actionwords();
    }

    protected void scenarioSetup(String testName)  throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, testName);
        if (System.getenv("USE_CBT") != null) {
            cbt = new CBTHelper();
            cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        }
        actionwords.setDriver(driver);

    }

    protected void tearDown() throws Exception {
        if (System.getenv("USE_CBT") != null) {
            cbt.setScore(score);
        }
        driver.quit();
    }

    // Well, sometimes, you just get a coffee.
    // Tags: priority:0
    public void testSimpleUse() throws Exception {
        scenarioSetup("Simple use");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I take a coffee
        actionwords.iTakeACoffee();
        // Then coffee should be served
        actionwords.coffeeShouldBeServed();
        score = "pass";
    }
}