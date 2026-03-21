package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		glue={"stepDefinitions"},
		plugin={"pretty","html:target/cucumber-html-report.html"}
)
public class TestRunner {
	//Using JUnit
}
