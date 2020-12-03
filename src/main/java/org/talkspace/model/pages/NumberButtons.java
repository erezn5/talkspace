package org.talkspace.model.pages;

import org.openqa.selenium.By;
import org.talkspace.model.PageElement;
import org.talkspace.model.selenium.DriverWrapper;

public class NumberButtons extends PageElement {

    private static final String btnStr = "button[id='Btn%s']";

    public NumberButtons(DriverWrapper driver) {
        super(driver);
    }

    public void clickNumberButton(String number){
        By bthButtonBy = By.cssSelector(String.format(btnStr, number));
        clickButtonBy(bthButtonBy);
    }
}
