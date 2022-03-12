package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:features",
        glue="stepdefs",
        tags="@TC4",
        plugin = {"pretty",
                "html:target/html/cucumber-report.html",
                "json:target/json/file.json",
        },
        monochrome = true,
        publish = true,
        dryRun=false
)

public class TestRunner {
}
