package com.avin.run;

import com.avin.automation.*;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;

/**
 * @author Naveena U S
 */

    public class TestRunner {
    static String url, username, password, cameraVersion, fromFirmware, toFirmware;
    @DataProvider(name ="cam-data")
    public static Object[][] dataprovider()
    {
        Object[][] obj =  new Object[1][6];
        obj[0][0]=url;
        obj[0][1]=username;
        obj[0][2]=password;
        obj[0][3]=cameraVersion;
        obj[0][4]=fromFirmware;
        obj[0][5]=toFirmware;
        return obj;
    }
            public static void main(String[] args) {
            System.out.println(" Enter the details in this order url, username, password,cameraVersion, fromFirmware, toFirmware");
             url = args[0];
             username = args[1];
             password = args[2];
             cameraVersion = args[3];
             fromFirmware = args[4];
             toFirmware = args[5];

            TestNG testng = new TestNG();
            testng.setTestClasses(new Class[]{CameraUpgrade.class, ApplyReset.class, AddCredentials.class, UpgradeToFrom.class, DowngradeFromTo.class});
            testng.run();

        }
    }



