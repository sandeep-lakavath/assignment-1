package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"} , plugin = {
		"pretty",
		"json:target/cucumber.json",
		"html:target/site/cucumber-pretty",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
        glue = "steps",
        tags = "")
 
public class TestRunner {
 
}