package Runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/test/resources/Feature/Ecomm_HealthCheck.feature"},           //Path of Feature folder which hold feature file
        glue={"StepDef","StepDefFile" }, // Path of StepDefinition file
        tags= " @e2esignupcheckout", 
        plugin = {"pretty",                      
            "html:target/html/htmlReport.html",
            "json:target/json/jsonReport.json",
            },
        monochrome=true,
        publish= true,
        dryRun=false
        
		)

public class TestRunner {
	
}	