package org.talkspace.model.pages;

import org.awaitility.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.talkspace.model.BasePage;
import org.talkspace.model.selenium.DriverWrapper;

import java.util.List;

public class WebCalculatorPage extends BasePage {

    @FindBy(css = "div[class='container'] button[id='loginbtn']")
    private WebElement loginbtnElm;
    @FindBy(css = "div[id='display']")
    private WebElement displayOpElm;
    @FindBy(css = "button[mode='primary']")
    private WebElement agreeBthElm;

    private final ArithmeticOperations arithmeticOperations;
    private final NumberButtons numberButtons;
    private final HistoryDropDownHandler historyDropDownHandler;

    public WebCalculatorPage(DriverWrapper driver){
        super(driver, "");
        arithmeticOperations = new ArithmeticOperations(driver);
        numberButtons = new NumberButtons(driver);
        historyDropDownHandler = new HistoryDropDownHandler(driver);
    }

    public void clickLoginButton() {
        clickButton(loginbtnElm);
    }

    private void quringStringList(List<String>buttonList){
        for(String num : buttonList){
            numberButtons.clickNumberButton(num);
        }
    }
    //todo - I would create an object in order to handle this popup window since it only for the assignment I did not add it
    public void clickAgreeOnPopUpMessage(){
        clickIfVisible(agreeBthElm);
    }

    public void addNumbers(List<String> numList1, List<String> numList2){
        quringStringList(numList1);
        arithmeticOperations.clickAddBth();

        quringStringList(numList2);
        arithmeticOperations.clickResBth();
        sleep();
    }

    public void subtractNumbers(List<String> numList1, List<String> numList2) {
        quringStringList(numList1);
        arithmeticOperations.clickSubBth();

        quringStringList(numList2);
        arithmeticOperations.clickResBth();
        sleep();
    }

    public void sinosOperation(List<String> list) {
        quringStringList(list);
        arithmeticOperations.clickSinBth();
        arithmeticOperations.clickResBth();
        sleep();
    }

    public void clearCalculationsHistory() {
        historyDropDownHandler.clearCalculationsHistory();
    }

    public List<String> getDropDownHistoryItems() {
        return historyDropDownHandler.getDropDownHistoryItems();
    }

    public String getResult() {
        return arithmeticOperations.getResult();
    }


    @Override
    public boolean verifyElement() {
        return waitElmClickAble(loginbtnElm)!=null;
    }

}
