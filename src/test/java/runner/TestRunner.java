package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features",glue = {"blueEHR.qa.ehr.stefDefs"},plugin = {"pretty","html:target/cucumber-reports","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm","json:target/reports-json"},monochrome = true,publish = true)

public class TestRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
