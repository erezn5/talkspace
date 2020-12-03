package org.talkspace;

import org.apache.log4j.Logger;
import org.talkspace.automation.conf.EnvConf;
import org.talkspace.model.selenium.Browser;
import org.talkspace.model.selenium.DriverWrapper;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {

    private final Logger Log = Logger.getLogger(BaseTest.class);
    private static final Browser BROWSER = Browser.valueOf(EnvConf.getProperty("ui.browser.type"));
    protected static DriverWrapper driver;
    protected ITestContext context;

    @BeforeClass
    public final void baseSetup(ITestContext context) throws IOException {
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
