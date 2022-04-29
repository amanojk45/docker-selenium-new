package com.avin.helper;

import com.avin.run.TestRunner;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Naveena U S
 */
public class UploadFirmwareHelper {

    @Test(dataProvider = "cam-data",dataProviderClass = TestRunner.class)
    public static void uploadFirmwareByAutoIt(String toFirmware) throws InterruptedException, IOException {
        Thread.sleep(1000);
        Runtime.getRuntime().exec("D:\\Autoit\\"+toFirmware+".exe");
    }

    /**
     * Call API to upload the firmware
      * @param firmwareUploadVerison
     */
    public static void uploadFirmwareByAPI(String firmwareUploadVerison){

    }
}
