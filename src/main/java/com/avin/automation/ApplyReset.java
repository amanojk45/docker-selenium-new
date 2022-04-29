package com.avin.automation;

import com.avin.ConstPath;
import com.avin.run.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.avin.helper.AuthHelper;
import com.avin.helper.LocatorHelper;

/**
 * @author Naveena U S
 */

public class ApplyReset  {

    @Test(dataProvider = "cam-data",dataProviderClass = TestRunner.class)
    public static void testApplyRest(String url,String username,String password,String cameraVersion,String fromFirmware,String toFirmware) throws InterruptedException {
        LocatorHelper xpathHelper = new LocatorHelper(ConstPath.applyResSetXpath+fromFirmware+".properties");
        WebDriver driver = AuthHelper.getInstance().driver(username,password,url);

        //Device TAB- Navigating to Device tab
        driver.findElement(By.xpath(xpathHelper.getValue("Device"))).click();
        Reporter.log("Navigated to device tab");

        //Maintains&Support- to select rest
        driver.findElement(By.xpath(xpathHelper.getValue("Maintenance_Support"))).click();
        Reporter.log("Navigated to Maintenance/Support tab");

        //Reset radio button is selected
        driver.findElement(By.xpath(xpathHelper.getValue("Settings"))).click();;

        //Select apply button
        driver.findElement(By.xpath(xpathHelper.getValue("Apply"))).click();

        //Confirm popup- Apply
        driver.findElement(By.xpath(xpathHelper.getValue("confirm"))).click();
        //Reset delay time
        Thread.sleep(70000);
        Reporter.log("Camera reset is completed");
        driver.close();
    }

}






