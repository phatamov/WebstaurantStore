package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@SearchTest", features = "src/test/resources/features", glue = "stepDefinitions", dryRun = false, plugin = {
        "pretty", "html:target/built-in-html-report.html", "json:target/cucumber.json",},
        stepNotifications = true, snippets = CucumberOptions.SnippetType.CAMELCASE)
public class CucumberRunner {
}
