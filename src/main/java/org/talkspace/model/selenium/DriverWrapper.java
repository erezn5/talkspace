package org.talkspace.model.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.talkspace.automation.conf.EnvConf;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverWrapper implements WebDriver {

    private static WebDriver driver;
    private WebDriverWait wait;
    private final static Logger Log = Logger.getLogger(DriverWrapper.class.getName());
    private static final long ELM_TIMEOUT = 15;

    private DriverWrapper(WebDriver driver){
        DriverWrapper.driver = driver;
    }

    public static DriverWrapper open(Browser browser) {
        Log.info(String.format("Starting new %s browser driver", browser));
        switch (browser) {
            case FIREFOX:
                return createFireFoxInst();
            case CHROME:
                return createChromeInst();
            default:
                throw new IllegalArgumentException("'" + browser + "'no such browser type");
        }
    }

    private static DriverWrapper createChromeInst(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(EnvConf.getAsBoolean("selenium.headless"));
        options.setAcceptInsecureCerts(true);
        options.addArguments("--lang=" + EnvConf.getProperty("selenium.locale"));

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.SEVERE);

        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        options.addArguments("--window-size=" + EnvConf.getProperty("selenium.window_size"));

        ChromeDriverService service = ChromeDriverService.createDefaultService();
        ChromeDriver driver = new ChromeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new DriverWrapper(driver);
    }

    private static DriverWrapper createFireFoxInst() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless((EnvConf.getAsBoolean("selenium.headless")));
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new DriverWrapper(driver);
    }

    public WebElement waitForElmClickable(Duration duration, By by) {
        wait = new WebDriverWait(driver, duration.getTimeUnit().toSeconds(duration.getValue()));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitElmClickAble(WebElement elm) {
        wait = new WebDriverWait(driver, ELM_TIMEOUT);
        wait.until(
                ExpectedConditions.or(ExpectedConditions.visibilityOf(elm))
        );
        return elm;

    }
    public WebElement waitForElmVisibility(By by) {
        return waitForElmVisibility(Duration.TEN_SECONDS, by);
    }

    public WebElement waitForElmVisibility(Duration duration, By by) {
        WebDriverWait wait = new WebDriverWait(driver, duration.getTimeUnit().toSeconds(duration.getValue()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void acceptPopUpWindow() {
        driver.switchTo().alert().accept();
    }

    @Override
    public void get(String s) {
        driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    public boolean clickIfVisible(WebElement elm) {
       return elm.isDisplayed();
    }
}
