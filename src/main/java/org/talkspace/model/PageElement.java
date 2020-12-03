package org.talkspace.model;

import org.apache.log4j.Logger;
import org.awaitility.Duration;
import org.awaitility.core.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.talkspace.automation.utils.Waiter;
import org.talkspace.model.selenium.DriverWrapper;

import java.util.List;

public class PageElement {
    protected DriverWrapper driver;
    private final static Logger Log = Logger.getLogger(PageElement.class);

    protected PageElement(DriverWrapper driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void clickButton(WebElement elm){
        elm.click();
        printElm(String.format("click on '%s' button", elm));
    }

    public void clickIfVisible(WebElement elm) {
        Condition condition = ()-> {
            if(driver.clickIfVisible(elm)){
                clickButton(elm);
            }
            return null;
        };
        Waiter.waitCondition(Duration.FIVE_SECONDS, condition, Duration.ONE_SECOND);
    }

    public WebElement waitElmClickAble(WebElement elm){
        return driver.waitElmClickAble(elm);
    }

    public void acceptPopUpWindow(){
        driver.acceptPopUpWindow();
    }

    private void printElm(String message){
        Log.info(message);
    }

    protected void clickButtonBy(By bthBy){
        WebElement bthElem = waitForClickableElm(bthBy);
        clickButton(bthElem);
    }

    public List<WebElement> getListElements(WebElement historyULElm) {
        return historyULElm.findElements(By.tagName("li"));
    }

    private WebElement waitForClickableElm(By by){
        WebElement element = driver.waitForElmClickable(Duration.TEN_SECONDS, by);
        printClickableElm(by);
        return element;
    }

    private void printClickableElm(By by){
        Log.info(String.format("locator=[%s] is clickable" , by.toString()));
    }

    protected static void sleep(){
        try {
            Thread.sleep(Duration.ONE_SECOND.getTimeUnit().toMillis(Duration.ONE_SECOND.getValue()));
        } catch (InterruptedException e) {
            Log.error(e.getMessage(), e);
            Log.error(String.format("error occur while sleep, timeout=[%s]" , Duration.ONE_SECOND.toString()));
            throw new RuntimeException(e);
        }
    }

}
