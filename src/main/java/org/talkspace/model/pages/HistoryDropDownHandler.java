package org.talkspace.model.pages;

import org.awaitility.Duration;
import org.awaitility.core.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.talkspace.automation.utils.Waiter;
import org.talkspace.model.PageElement;
import org.talkspace.model.selenium.DriverWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryDropDownHandler extends PageElement {
    @FindBy(css = "button[class='btn dropdown-toggle pull-right']")
    private WebElement dropDownMenuButtonElm;
    @FindBy(css = "a[id='clearhistory']")
    private WebElement clearHistoryButtonElm;
    @FindBy(css = "button[class='close']")
    private WebElement closeDropDownMenuHistoryMenuElm;
    @FindBy(css = "div[class='history btn-group open']")
    private WebElement dropDownOpenElm;

    private static final By listContainerBy = By.cssSelector("div[id='histframe'] ul");

    public HistoryDropDownHandler(DriverWrapper driver) {
        super(driver);
    }

    public void clearCalculationsHistory(){
        clickButton(waitElmClickAble(dropDownMenuButtonElm));
        clickClearButton();
        closeDropDownHistoryMenuButton();
    }

    public void clickDropDownMenuButton() {
        Condition<Boolean> condition = ()-> {
            clickButton(dropDownMenuButtonElm);
            sleep();
            return verifyDropDownMenuIsOpen();
        };
        Waiter.waitCondition(Duration.FIVE_SECONDS, condition, Duration.ONE_SECOND);

    }

    private boolean verifyDropDownMenuIsOpen() {
        return waitElmClickAble(dropDownOpenElm)!=null;
    }


    protected void closeDropDownHistoryMenuButton(){
        clickIfVisible(closeDropDownMenuHistoryMenuElm);
    }

    private void clickClearButton(){
        clickButton(clearHistoryButtonElm);
        acceptPopUpWindow();
    }


    public List<String> getDropDownHistoryItems(){
        clickDropDownMenuButton();
        WebElement historyULElm = driver.waitForElmVisibility(listContainerBy);
        List<WebElement> historyList = getListElements(historyULElm);
        return historyList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
