package com.foundation.core;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.apache.log4j.BasicConfigurator;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.foundation.stepDefinitions"},
        tags = {"~@Ignore"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun-reports/rerun.txt"
        })

public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;

    @Parameters({ "browser", "url"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String browser, String url) throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        WebDriver driver = DriverConfig.getDriver(browser);
        driver.get(url);
        driver.manage().window().maximize();
        long startTime =  System.nanoTime();
        BasicConfigurator.configure();

    }

    @Test(testName = "BrokenLinksOnPages", description = "Verifying Broken Links", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
        // return new Object[][];
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}

//public class TestRunner extends AbstractTestNGCucumberTests {
//
//    static Logger log = Logger.getLogger(TestRunner.class);
//
//    @Parameters({ "browser", "url","scenarios", "tags", })
//    @BeforeClass
//    public static void setUp(String browser, String url, String scenarios, String tags) {
//        WebDriver driver = DriverConfig.getDriver(browser);
//        driver.get(url);
//        driver.manage().window().maximize();
//        long startTime =  System.nanoTime();
//        BasicConfigurator.configure();
//        ArrayList<String> ae = new ArrayList<String>();
//        ae.add(url);
//        ae.add(browser);
//        ae.add(scenarios);
//        try {
//            Main.run(ae.toArray(new String[ae.size()]), Thread.currentThread().getContextClassLoader());
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        Utils.getCellValueInRow("Links","HomePage","URL");
//        log.info("Started Executing scenarios");
//    }
//
//    @AfterClass
//    public static void tearDown() {
//        driver.close();
//        driver.quit();
//        System.gc();
//    }
//
//    private static String[] getCucumberArgs(String ... args) {
//        List<String> cucumberArgs = new ArrayList<>();
//        cucumberArgs.add("--glue");
//        cucumberArgs.add("com.foundation");
//        System.getenv("scenarios");
//        System.getenv("browser");
//        System.getenv("url");
//        //   cucumberArgs.add("--plugin");
//    //    cucumberArgs.add("json:" + getReportsDir() + "report.json");
//    //    cucumberArgs.add("--plugin");
//     //   cucumberArgs.add("rerun:" + getReportsDir() + "rerun.txt");
//     //   cucumberArgs.add("--plugin");
//        cucumberArgs.add("pretty");
////        if (getTags() != null) {
////            cucumberArgs.add("--tags");
////            cucumberArgs.add(getTags());
////        }
////        if (getScenarios() != null) {
////            cucumberArgs.add("--name");
////            cucumberArgs.add(getScenarios());
////        }
////        if (isDryRun()) {
////            cucumberArgs.add("--dry-run");
////        }
////        cucumberArgs.add("--strict");
////        cucumberArgs.addAll(Arrays.asList(args));
////        if (getFeatures() != null) {
////            cucumberArgs.add(getFeatures());
////        }
////        log().info("Cucumber Options: " + cucumberArgs);
//
//        return cucumberArgs.toArray(new String[cucumberArgs.size()]);
//    }
//
//
//}
