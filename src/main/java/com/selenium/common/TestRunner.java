    package com.selenium.common;

    import org.testng.annotations.BeforeTest;
    import org.testng.annotations.Parameters;

    import io.cucumber.testng.AbstractTestNGCucumberTests;
    import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = "src/test/java/com/selenium/features",
            plugin = {"pretty", "json:target/cucumber.json"},
            monochrome = true,
            glue = {"com.selenium.steps"}
    )

    public class TestRunner extends AbstractTestNGCucumberTests{

        @BeforeTest
        @Parameters({"browser","featurePath"})
        public void setUpEngine(String browser,String featurePath){
            System.setProperty("cucumber.features", featurePath);
            TestEngine.setupSuite(browser);
        }

        // @AfterTest
        // public void stopEngine(){
        //     TestEngine.clearBrowser();
        // }

        // @BeforeClass
        // public void getBaseUrl(ITestContext context){
        //     TestEngine.getUrl(context);
        // }
    }
