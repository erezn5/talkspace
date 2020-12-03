package org.talkspace;

import org.talkspace.automation.conf.EnvConf;
import org.talkspace.model.selenium.Browser;
import org.talkspace.model.selenium.DriverWrapper;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private static final Browser BROWSER = Browser.valueOf(EnvConf.getProperty("ui.browser.type"));
    protected static DriverWrapper driver;
    protected ITestContext context;

    @BeforeClass
    public final void baseSetup(ITestContext context){
        this.context = context;
        driver = DriverWrapper.open(BROWSER);
    }

    @AfterClass
    public final void baseTeardown() {
        if (driver != null) {
            driver.quit();
        }
    }



}
