package org.talkspace;

import org.apache.log4j.Logger;
import org.talkspace.model.pages.WebCalculatorPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CalcAssignmentTest extends BaseTest{

    private final Logger Log = Logger.getLogger(CalcAssignmentTest.class);
    WebCalculatorPage webCalculatorPage;
    List<String> expectedFormulasList = Arrays.asList("sin(30)= 0.5", "10-2= 8", "2+3= 5");

    @BeforeClass
    public void setup(){
        webCalculatorPage = new WebCalculatorPage(driver);
    }

    @Test(priority=1)
    public void simpleAddTest(){
        webCalculatorPage.addNumbers(Collections.singletonList("2"), Collections.singletonList("3"));
        assertEquals("5", webCalculatorPage.getResult(), "Adding numbers failed");
        Log.info("Adding numbers operation is successful!");
    }

    @Test(priority=2)
    public void simpleSubtractTest(){
        webCalculatorPage.subtractNumbers(Arrays.asList("1", "0"), Collections.singletonList("2"));
        assertEquals("8", webCalculatorPage.getResult(), "subtraction operation failed");
        Log.info("Subtraction operation is successful!");
    }

    @Test(priority=3)
    public void simpleSinosTest(){
        webCalculatorPage.sinosOperation(Arrays.asList("3", "0"));
        assertEquals("0.5", webCalculatorPage.getResult(),  "sinos operation failed");
        Log.info("Sinos operation is successful");
    }

    @Test(priority=4)
    public void getHistoryListAndVerify(){
        assertTrue(list(webCalculatorPage.getDropDownHistoryItems()));
        Log.info("Verify history is successful!");
    }

    @BeforeMethod()
    public void navigateToCalcPage(){
        webCalculatorPage.navigateAndVerify();
        webCalculatorPage.clickAgreeOnPopUpMessage();

    }

    public boolean list(List<String>elements){
        List<String> results = new ArrayList<>();
        for(String str : elements){
            StringBuilder stringBuilder = new StringBuilder();
            String data = str.split("\n")[1].trim();
            String result = str.split("\n")[0].trim();
            results.add(stringBuilder.append(data).append(result).toString());
        }
        return results.equals(expectedFormulasList);
    }
}
