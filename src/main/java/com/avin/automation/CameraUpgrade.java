package com.avin.automation;

import com.avin.ConstPath;
import com.avin.run.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.avin.helper.AuthHelper;
import com.avin.helper.LocatorHelper;
import com.avin.helper.UploadFirmwareHelper;

import java.io.IOException;

/**
 * @author Naveena U S
 */
public class CameraUpgrade {
    @Test(dataProvider = "cam-data",dataProviderClass = TestRunner.class)
    public static void testCameraUpgrade(String url,String username,String password,String cameraVersion,String fromFirmware,String toFirmware) throws InterruptedException, IOException {
        LocatorHelper xpathHelper = new LocatorHelper(ConstPath.cameraUpgradeXpath + fromFirmware + ".properties");
        WebDriver driver = AuthHelper.getInstance().driver(username,password,url);

//    if(!toFirmware.equals("2.1.4")){
//        //Adding username
//        WebElement userWebElement = driver.findElement(By.xpath(xpathHelper.getValue("Username")));
//        userWebElement.sendKeys(System.getenv("camera_user_name"));
//        Reporter.log("Added new username");
//
//        //Adding password
//        WebElement pswWebElement = driver.findElement(By.xpath(xpathHelper.getValue("Password")));
//        pswWebElement.sendKeys(System.getenv("camera_password"));
//        Reporter.log("Added new password");
//
//        //Adding confirmpassword
//        WebElement cnfrmPsw = driver.findElement(By.xpath(xpathHelper.getValue("Confirmpassword")));
//        cnfrmPsw.sendKeys(System.getenv("camera_password"));
//        Reporter.log("Added new confirmpassword");
//
//        //Selecting setpassword
//        driver.findElement(By.xpath(xpathHelper.getValue("Setpassword"))).click();
//        Reporter.log("Password is set");
//
//        //Selecting login
//        driver.findElement(By.xpath(xpathHelper.getValue("Login"))).click();
//        Reporter.log("New Credentials are added");


//Running under Autoit
         //Device TAB- Navigating to Device tab
         driver.findElement(By.xpath(xpathHelper.getValue("Device"))).click();
         Reporter.log("Navigatied to Device tab");
         //Firmware upgrade using Autoit tool
         driver.findElement(By.xpath(xpathHelper.getValue("FirmwareTab"))).click();
         Thread.sleep(1000);
         WebElement chooseFile=driver.findElement(By.xpath(xpathHelper.getValue("FirmwareUpgrade")));
         chooseFile.click();
         UploadFirmwareHelper.uploadFirmwareByAutoIt(fromFirmware);
         Reporter.log("Firmware Upgrade is completed");
         Thread.sleep(555000);
         driver.close();


    }}



