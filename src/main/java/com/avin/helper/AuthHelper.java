package com.avin.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * @author Naveena U S
 * AuthHelper is a SingleTone class
 * helps in Autenticating the driver w.r.t userName, password and url
 */
public class AuthHelper {
    private static AuthHelper authHelper = null;
     // private constructor
    private AuthHelper(){
    }
    /**
     * checks for the instance if create
     * returns the AuthHelper else creats and returns AuthHelper
     * @return authHelper
     */
    public static AuthHelper getInstance(){
        if(authHelper == null)
            authHelper = new AuthHelper();
        return authHelper;
    }

    /**
     * creates the new instance of the  chromeDriver
     *   for the given userName, password and url
     * @param username
     * @param password
     * @param url
     * @return
     */
    public static WebDriver driver(String username, String password, String url){
        try{
            // headless run execution
            //ChromeOptions options = new ChromeOptions();
           // options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
            WebDriver driver = new ChromeDriver();
            Thread.sleep(5000);
            driver.manage().window().maximize();
            driver.get("http://"+username+":"+ password+"@"+url);
            driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(30000));
            return driver;
        } catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
