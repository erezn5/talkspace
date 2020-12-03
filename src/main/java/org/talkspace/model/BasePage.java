package org.talkspace.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.talkspace.automation.conf.EnvConf;
import org.talkspace.automation.utils.FileUtil;
import org.talkspace.model.selenium.DriverWrapper;

public abstract class BasePage extends PageElement{

    private final String url;
    private final static Logger Log = Logger.getLogger(BasePage.class);
    protected final static String URL_ADDRESS = EnvConf.getProperty("ui.calculator.url");
    private final static String log4jConfigFile = FileUtil.getFile(String.format("/automation_project/src/main/resources/%s", EnvConf.getProperty("conf.log4j")));

    public BasePage(DriverWrapper driver, String path) {
        super(driver);
        this.url = String.format("%s/%s", URL_ADDRESS, path);
        PropertyConfigurator.configure(log4jConfigFile);
    }

    private void navigate(){
        driver.get(url);
    }

    private void refresh(){
        Log.info(String.format("Navigate to url=[%s]", url));
        driver.navigate().refresh();
    }

    public void navigateAndVerify(){
        if(url.equals(driver.getCurrentUrl())){
            refresh();
        }else{
            navigate();
        }

        if(verifyElement()){
            Log.info("Navigation succeeded!!!");
        }
    }

    public abstract boolean verifyElement();

}
