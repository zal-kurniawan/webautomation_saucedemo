package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "stepdefenitions", "hook" }, plugin = {
        "pretty", // Pretty console output
        "html:target/cucumber-report.html", // HTML report
        "json:target/cucumber-report.json", // JSON report
        "junit:target/cucumber-report.xml" // JUnit XML report
})
public class TestRunnerCheckout extends AbstractTestNGCucumberTests {

}