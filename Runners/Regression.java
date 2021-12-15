package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        dryRun =false,
        monochrome = false,
        tags = "@location",
        plugin = {"pretty","html:target/cucumber.html"}

)
public class Regression {

}
