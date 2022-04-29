package com.avin.automation;

import com.avin.ConstPath;
import com.avin.helper.AuthHelper;
import com.avin.helper.LocatorHelper;
import com.avin.run.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @author Naveena U S
 */
public class AddCredentials {

	@Test(dataProvider = "cam-data",dataProviderClass = TestRunner.class)
	public static void testAddCredentials(String url,String username,String password,String cameraVersion,String fromFirmware,String toFirmware) throws InterruptedException {
		LocatorHelper xpathHelper = new LocatorHelper(ConstPath.addCredentialsXpath+fromFirmware+".properties");
		WebDriver driver = AuthHelper.getInstance().driver(username,password,url);

		//Adding username
        WebElement userWebElement = driver.findElement(By.xpath(xpathHelper.getValue("Username")));
		userWebElement.sendKeys(username);
		Reporter.log("Added new username");

        //Adding password
        WebElement pswWebElement = driver.findElement(By.xpath(xpathHelper.getValue("Password")));
		pswWebElement.sendKeys(password);
		Reporter.log("Added new password");

        //Adding confirmpassword
		WebElement cnfrmPsw = driver.findElement(By.xpath(xpathHelper.getValue("Confirmpassword")));
		cnfrmPsw.sendKeys(password);
	    Reporter.log("Added new confirmpassword");

        //Selecting setpassword
		driver.findElement(By.xpath(xpathHelper.getValue("Setpassword"))).click();
	    Reporter.log("Password is set");

        //Selecting login
		driver.findElement(By.xpath(xpathHelper.getValue("Login"))).click();
		Reporter.log("New Credentials are added");
		driver.close();

}
}

