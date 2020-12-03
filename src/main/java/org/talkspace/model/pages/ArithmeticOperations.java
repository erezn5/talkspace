package org.talkspace.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.talkspace.model.PageElement;
import org.talkspace.model.selenium.DriverWrapper;

public class ArithmeticOperations extends PageElement {
    @FindBy(css = "div[id='calccontainer'] button[id='BtnPlus']")
    private WebElement plusOpElm;
    @FindBy(css = "div[id='calccontainer'] button[id='BtnMinus']")
    private WebElement subOpElm;
    @FindBy(css = "div[id='calccontainer'] button[id='BtnSin']")
    private WebElement sinOpElm;
    @FindBy(css = "div[id='calccontainer'] button[id='BtnClear']")
    private WebElement clearOpElm;
    @FindBy(css = "div[id='calccontainer'] button[id='BtnCalc']")
    private WebElement calcResOpElm;

    private static final By resultOpBy = By.cssSelector("div[id='histframe'] p[title]");

    HistoryDropDownHandler historyDropDownHandler;
    public ArithmeticOperations(DriverWrapper driver) {
        super(driver);
        historyDropDownHandler = new HistoryDropDownHandler(driver);
    }


    public void clickAddBth(){
        clickButton(plusOpElm);
    }

    public void clickSubBth(){
        clickButton(subOpElm);
    }

    public void clickSinBth(){
        clickButton(sinOpElm);
    }

    public void clickClearBth(){
        clickButton(clearOpElm);
    }

    public void clickResBth(){
        clickButton(calcResOpElm);
    }

    public String getResult(){
        historyDropDownHandler.clickDropDownMenuButton();
        String result =  driver.findElement(resultOpBy).getAttribute("title");
        historyDropDownHandler.closeDropDownHistoryMenuButton();
        setClearOpBy();
        return result;
    }


    public void setClearOpBy(){
        clickButton(clearOpElm);
    }



}
