package com.avin.automation;

import com.avin.ConstPath;
import com.avin.helper.AuthHelper;
import com.avin.helper.LocatorHelper;
import com.avin.helper.UploadFirmwareHelper;
import com.avin.run.TestRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

/**
 * @author Naveena U S
 */
public class DowngradeFromTo {
    @Test(dataProvider = "cam-data",dataProviderClass = TestRunner.class)
    public static void testDowngradeFromTo(String url,String username,String password,String cameraVersion,String fromFirmware,String toFirmware)throws InterruptedException, IOException {
        LocatorHelper xpathHelper = new LocatorHelper(ConstPath.downgradeFromToXpath + toFirmware + ".properties");
        WebDriver driver = AuthHelper.getInstance().driver(username,password,url);

        //Expert Mode on
        WebElement expertMode = driver.findElement(By.xpath(xpathHelper.getValue("Expertmode")));
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        WebElement expert = wait.until(ExpectedConditions.elementToBeClickable(expertMode.findElement(By.xpath(".."))));
        expert.click();

        //Device TAB- Navigating to Device tab
        driver.findElement(By.xpath(xpathHelper.getValue("Device"))).click();
        Reporter.log("Navigated to device tab");

        //DeviceInformation- adding new name
        String accfriendlyname = driver.findElement(By.xpath(xpathHelper.getValue("FriendlyName"))).getAttribute("value");
        String expecfriendlyname = "Camera12";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expecfriendlyname, accfriendlyname);
        if (!expecfriendlyname.equalsIgnoreCase(accfriendlyname)) {
            Reporter.log("The Camera name is not same : " + expecfriendlyname.equalsIgnoreCase(accfriendlyname));
        } else {
            Reporter.log("The Camera name is same : " + expecfriendlyname.equalsIgnoreCase(accfriendlyname));
        }
        Thread.sleep(2000);

        //Enable EVO-12 ONVIF Emulation
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement checkemumode = driver.findElement(By.xpath(xpathHelper.getValue("EmulationMode")));
        checkemumode.findElement(By.xpath(".."));
        js.executeScript("arguments[0].scrollIntoView();", checkemumode);
        if (checkemumode.isEnabled()) {
            Reporter.log("Emulation mode is not enabled: " + (checkemumode.getAttribute("aria-checked")));
        } else {
            Reporter.log("Emulation mode is enabled: " + (checkemumode.getAttribute("aria-checked")));
        }

        //Date&Time- selecting new timezone
        driver.findElement(By.xpath(xpathHelper.getValue("DateAndTime"))).click();
        String actualtimezone = driver.findElement(By.xpath(xpathHelper.getValue("Timezone"))).getText();
        String expectimezone = "HST";
        softAssert.assertEquals(expectimezone, actualtimezone);
        if (!expectimezone.equals(actualtimezone)) {
            Reporter.log("Timezone is not same : " + expectimezone.equals(actualtimezone));
        } else {
            Reporter.log("Timezone is same : " + expectimezone.equals(actualtimezone));
        }

        //Date&Time- Selecting NTP
        WebElement actualmode = driver.findElement(By.xpath(xpathHelper.getValue("NTP")));
        boolean mode = actualmode.isSelected();
        if (mode == false) {
            Reporter.log("NTP mode is not selected : false");
        } else {
            Reporter.log("NTP mode is selected: true ");
        }

       // if(!firmwareVersion.equals("1.0.2")) {
            //Network settings
            driver.findElement(By.xpath(xpathHelper.getValue("Network"))).click();

            //Network settings-DHCP Selecting
            WebElement DHCP = driver.findElement(By.xpath(xpathHelper.getValue("DHCP")));
            boolean dhcp = DHCP.isSelected();
            if (dhcp == false) {
                Reporter.log("DHCP mode is disabled : true ");
            }

            //Network settings-Adding static address
            WebElement staticIp = driver.findElement(By.xpath(xpathHelper.getValue("Staticip")));
            if (staticIp.isEnabled()) {
                Reporter.log("static ipaddress is selected : " + (staticIp.getAttribute("value")));
            }

            //Network settings-Advanced Network Configuration
            driver.findElement(By.xpath(xpathHelper.getValue("Advancesettings"))).click();

            //Adding new HTTPS value
            String actualHttps = driver.findElement(By.xpath(xpathHelper.getValue("HTTP"))).getAttribute("value");
            String expectHttps = "1443";
            softAssert.assertEquals(expectHttps, actualHttps);
            if (!expectHttps.equalsIgnoreCase(actualHttps)) {
                Reporter.log("HTTPS port is not same : " + expectHttps.equalsIgnoreCase(actualHttps));
            } else {
                Reporter.log("HTTPS port is same : " + expectHttps.equalsIgnoreCase(actualHttps));
            }

            //Adding new RTPS value
            String actualRtsp = driver.findElement(By.xpath(xpathHelper.getValue("RTSP"))).getAttribute("value");
            String expectRtsp = "1554";
            softAssert.assertEquals(expectRtsp, actualRtsp);
            if (!expectRtsp.equals(actualRtsp)) {
                Reporter.log("RTSP port is not same : " + expectRtsp.equals(actualRtsp));
            } else {
                Reporter.log("RTSP port is same : " + expectRtsp.equals(actualRtsp));
            }

       //Video/Audio Image Settings
       driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
       driver.findElement(By.xpath(xpathHelper.getValue("ImageSettings"))).click();

       //Checking the the frequency value
       WebElement actualfrequency = driver.findElement(By.xpath(xpathHelper.getValue("PwdLineFreq")));
       boolean freq = actualfrequency.isSelected();
       if (freq == false) {
           Reporter.log("frequency 50hz is not selected : false");
       } else {
          Reporter.log("frequency 50hz is selected: true ");
       }

       //Checking the HDR -on/off
       WebElement actualhrddOff = driver.findElement(By.xpath(xpathHelper.getValue("TrueDetailHDR")));
       boolean expecthrdOff = actualhrddOff.isSelected();
       if (expecthrdOff == false) {
           Reporter.log("HDR is not Off : false");
       } else {
           Reporter.log("HRD is Off: true ");
       }

            //Checking the exposureprioprity
            WebElement actualFrameRate = driver.findElement(By.xpath(xpathHelper.getValue("FrameRate")));
            boolean expectFrameRate = actualFrameRate.isSelected();
            if (expectFrameRate == false) {
                Reporter.log("FrameRate is not selected : false ");
            } else {
                Reporter.log("FrameRate is selected: true ");
            }

            //Checking the dynamicExposure
            String actaulimageprefrence = driver.findElement(By.xpath(xpathHelper.getValue("Motion"))).getText();
            String expctimageprefrence = "Motion";
            softAssert.assertEquals(expctimageprefrence, actaulimageprefrence);
            if (!expctimageprefrence.equalsIgnoreCase(actaulimageprefrence)) {
                Reporter.log("imageprefrence- motion is not selected : " + expctimageprefrence.equalsIgnoreCase(actaulimageprefrence));
            } else {
                Reporter.log("imageprefrence- motion is selected : " + expctimageprefrence.equalsIgnoreCase(actaulimageprefrence));
            }

            //Checking the exposure value
            String actualexpcomp = driver.findElement(By.xpath(xpathHelper.getValue("ExposureComp"))).getAttribute("value");
            String expectexpcomp = "7";
            softAssert.assertEquals(expectexpcomp, actualexpcomp);
            if (!expectexpcomp.equalsIgnoreCase(actualexpcomp)) {
                Reporter.log("exposure is not same : " + expectexpcomp.equalsIgnoreCase(actualexpcomp));
            } else {
                Reporter.log("exposure is same : " + expectexpcomp.equalsIgnoreCase(actualexpcomp));
            }

            //Checking the brightness value
            String actualbrightness = driver.findElement(By.xpath(xpathHelper.getValue("Brightness"))).getAttribute("value");
            String expctbrightness = "50";
            softAssert.assertEquals(expctbrightness, actualbrightness);
            if (!expctbrightness.equalsIgnoreCase(actualbrightness)) {
                Reporter.log("brightness is not same : " + expctbrightness.equalsIgnoreCase(actualbrightness));
            } else {
                Reporter.log("brightness is same : " + expctbrightness.equalsIgnoreCase(actualbrightness));
            }

            //Checking the contrast	value
            String actualcontrast = driver.findElement(By.xpath(xpathHelper.getValue("Contrast"))).getAttribute("value");
            String expctcontrast = "9";
            softAssert.assertEquals(expctcontrast, actualcontrast);
            if (!expctcontrast.equalsIgnoreCase(actualcontrast)) {
                Reporter.log("contrast is not same : " + expctcontrast.equalsIgnoreCase(actualcontrast));
            } else {
                Reporter.log("contrast is same : " + expctcontrast.equalsIgnoreCase(actualcontrast));
            }

            //Checking the sharpness value
            String actualsharpness = driver.findElement(By.xpath(xpathHelper.getValue("Sharpness"))).getAttribute("value");
            String expctsharpness = "10";
            softAssert.assertEquals(expctsharpness, actualsharpness);
            if (!expctsharpness.equalsIgnoreCase(actualsharpness)) {
                Reporter.log("sharpness is not same : " + expctsharpness.equalsIgnoreCase(actualsharpness));
            } else {
                Reporter.log("sharpness is same : " + expctsharpness.equalsIgnoreCase(actualsharpness));
            }

            //Checking the saturation value
            String actualsaturation = driver.findElement(By.xpath(xpathHelper.getValue("Saturation"))).getAttribute("value");
            String expctsaturation = "8";
            softAssert.assertEquals(expctsaturation, actualsaturation);
            if (!expctsaturation.equalsIgnoreCase(actualsaturation)) {
                Reporter.log("saturation is not same : " + expctsaturation.equalsIgnoreCase(actualsaturation));
            } else {
                Reporter.log("saturation is same : " + expctsaturation.equalsIgnoreCase(actualsaturation));
            }

            //Checking the Wdr enabled /disabled
            WebElement Wdr = driver.findElement(By.xpath(xpathHelper.getValue("WideDynamicRange")));
            js.executeScript("arguments[0].scrollIntoView();", Wdr);
            if (Wdr.isEnabled()) {
                Reporter.log("WideDynamicRange is enabled: " + (Wdr.getAttribute("aria-checked")));
            }

            //Checking the Wdr value
            String actualwdrValue = driver.findElement(By.xpath(xpathHelper.getValue("Wdr"))).getAttribute("value");
            String expctwdrValue = "50";
            softAssert.assertEquals(expctwdrValue, actualwdrValue);
            if (!expctwdrValue.equalsIgnoreCase(actualwdrValue)) {
                Reporter.log("wdr is not same : " + expctwdrValue.equalsIgnoreCase(actualwdrValue));
            } else {
                Reporter.log("wdr is same : " + expctwdrValue.equalsIgnoreCase(actualwdrValue));
            }

            //Checking the camera mount type
            String actualmount = driver.findElement(By.xpath(xpathHelper.getValue("CameraTable"))).getText();
            String expectmount = "Table";
            softAssert.assertEquals(expectmount, actualmount);
            if (!expectmount.equals(actualmount)) {
                Reporter.log("Cameramount Table is not selected : " + expectmount.equals(actualmount));
            } else {
                Reporter.log("Cameramount Table is selected : " + expectmount.equals(actualmount));
            }

            //Checking the camera invert mode
            WebElement actualrotate = driver.findElement(By.xpath(xpathHelper.getValue("RotateCamera")));
            boolean expectrotate = actualrotate.isSelected();
            if (expectrotate == false) {
                Reporter.log("Invert mode is not selected : false");
            } else {
                Reporter.log("Invert mode is selected: true ");
            }

            //Checking the camera Night Mode
            String actualfilter = driver.findElement(By.xpath(xpathHelper.getValue("IrNightMode"))).getText();
            String expectfilter = "Night Mode";
            softAssert.assertEquals(expectfilter, actualfilter);
            if (!expectfilter.equals(actualfilter)) {
                Reporter.log("Night mode is not selected : " + expectfilter.equals(actualfilter));
            } else {
                Reporter.log("Night Mode is selected : " + expectfilter.equals(actualfilter));
            }

            //Checking the camera colourspace
            String actualcolourspace = driver.findElement(By.xpath(xpathHelper.getValue("ColourFS"))).getText();
            String expectcolourspace = "BT709 FS";
            softAssert.assertEquals(expectcolourspace, actualcolourspace);
            if (!expectcolourspace.equals(actualcolourspace)) {
                Reporter.log("Colour Sapce Mode is not selected : " + expectcolourspace.equals(actualcolourspace));
            } else {
                Reporter.log("Colour Sapce Mode is selected : " + expectcolourspace.equals(actualcolourspace));
            }

            //Multimode settings
            driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
            Reporter.log("Navigated to video/audio tab");
            String actualMultimode = driver.findElement(By.xpath(xpathHelper.getValue("Multimode"))).getText();
            String expectMultimode = "Multi-Mode";
            softAssert.assertEquals(expectMultimode, actualMultimode);
            if (!expectMultimode.equals(actualMultimode)) {
                Reporter.log("Multimode is not selected : " + expectMultimode.equals(actualMultimode));
            } else {
                Reporter.log("Multimode is selected : " + expectMultimode.equals(actualMultimode));
            }

            //MP1 settings
            driver.findElement(By.xpath(xpathHelper.getValue("MP1"))).click();
            WebElement videoSettingMP1 = driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
            js.executeScript("arguments[0].scrollIntoView();", videoSettingMP1);
            String actualVideocomp = driver.findElement(By.xpath(xpathHelper.getValue("H265"))).getText();
            String expectVideocomp = "H265";
            softAssert.assertEquals(expectVideocomp, actualVideocomp);
            if (!expectVideocomp.equals(actualVideocomp)) {
                Reporter.log("MP1 Video Compression is not same : " + expectVideocomp.equals(actualVideocomp));
            } else {
                Reporter.log("MP1 Video Compression is same : " + expectVideocomp.equals(actualVideocomp));
            }

            WebElement size = driver.findElement(By.xpath(xpathHelper.getValue("Videosize")));
            js.executeScript("arguments[0].scrollIntoView();", size);

            String actualbitrate = driver.findElement(By.xpath(xpathHelper.getValue("Bitratemode"))).getText();
            String expectbitrate = "Constant Bit Rate";
            softAssert.assertEquals(expectbitrate, actualbitrate);
            if (!expectbitrate.equals(actualbitrate)) {
                Reporter.log("MP1 Bit Rate Control is not same : " + expectbitrate.equals(actualbitrate));
            } else {
                Reporter.log("MP1 Bit Rate Control is same : " + expectbitrate.equals(actualbitrate));
            }

            String actualRate = driver.findElement(By.xpath(xpathHelper.getValue("Bitrate"))).getAttribute("value");
            String expectRate = "25000";
            softAssert.assertEquals(expectRate, actualRate);
            if (!expectRate.equals(actualRate)) {
                Reporter.log("MP1 Bit Rate is not same : " + expectRate.equals(actualRate));
            } else {
                Reporter.log("MP1 Bit Rate is same : " + expectRate.equals(actualRate));
            }

            WebElement govLength = driver.findElement(By.xpath(xpathHelper.getValue("Govlength")));
            js.executeScript("arguments[0].scrollIntoView();", govLength);
            WebElement actualMulticast = driver.findElement(By.xpath(xpathHelper.getValue("Multicast")));
            if (!actualMulticast.isEnabled()) {
                Reporter.log("MP1 Video Multicast is not selected :" + (actualMulticast.getAttribute("aria-checked")));
            } else {
                Reporter.log("MP1 Video Multicast is selected :" + (actualMulticast.getAttribute("aria-checked")));
            }
            WebElement CancelMP1 = driver.findElement(By.xpath(xpathHelper.getValue("Cancel")));
            js.executeScript("arguments[0].scrollIntoView();", CancelMP1);
            CancelMP1.click();

            //MP2 settings
            driver.findElement(By.xpath(xpathHelper.getValue("MP2"))).click();
            WebElement videoSettingMP2 = driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
            js.executeScript("arguments[0].scrollIntoView();", videoSettingMP2);
            String actualView = driver.findElement(By.xpath(xpathHelper.getValue("Panoramic"))).getText();
            String expectView = "Panoramic+ (180 View)";
            softAssert.assertEquals(expectView, actualView);
            if (!expectView.equals(actualView)) {
                Reporter.log("MP2 Panoramic+ (180 View) is not selected : " + expectView.equals(actualView));
            } else {
                Reporter.log("MP2 Panoramic+ (180 View) is selected : " + expectView.equals(actualView));
            }

            String actualSize = driver.findElement(By.xpath(xpathHelper.getValue("Resolution"))).getText();
            String expectSize = "1920 x 768";
            softAssert.assertEquals(expectSize, actualSize);
            if (!expectSize.equals(actualSize)) {
                Reporter.log("MP2 videoSize is not same: " + expectSize.equals(actualSize));
            } else {
                Reporter.log("MP2 videoSize is same : " + expectSize.equals(actualSize));
            }

            WebElement quality = driver.findElement(By.xpath(xpathHelper.getValue("Quality")));
            js.executeScript("arguments[0].scrollIntoView();", quality);
            String actuaLength = driver.findElement(By.xpath(xpathHelper.getValue("Gop"))).getText();
            String expectLength = "100";
            softAssert.assertEquals(expectLength, actuaLength);
            if (!expectLength.equals(actuaLength)) {
                Reporter.log("MP2 govLength is not same: " + expectLength.equals(actuaLength));
            } else {
                Reporter.log("MP2 govLength is same : " + expectLength.equals(actuaLength));
            }

            WebElement govLengthSelect = driver.findElement(By.xpath(xpathHelper.getValue("Gop")));
            js.executeScript("arguments[0].scrollIntoView();", govLengthSelect);
            WebElement actualAlwayson = driver.findElement(By.xpath(xpathHelper.getValue("Alwayson")));
            if (!actualAlwayson.isEnabled()) {
                Reporter.log("MP2 Always On is not selected :" + (actualAlwayson.getAttribute("aria-checked")));
            } else {
                Reporter.log("MP2 Always On  is selected :" + (actualAlwayson.getAttribute("aria-checked")));
            }
            WebElement CancelMP2 = driver.findElement(By.xpath(xpathHelper.getValue("Cancel")));
            js.executeScript("arguments[0].scrollIntoView();", CancelMP2);
            CancelMP2.click();

            //MP3 settings
            WebElement selectMP3 = driver.findElement(By.xpath(xpathHelper.getValue("SelectMP3")));
            js.executeScript("arguments[0].scrollIntoView();", selectMP3);
            driver.findElement(By.xpath(xpathHelper.getValue("MP3"))).click();
            WebElement videoSettingsMP3 = driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
            js.executeScript("arguments[0].scrollIntoView();", videoSettingsMP3);

            String actualViewmp3 = driver.findElement(By.xpath(xpathHelper.getValue("Corridor"))).getText();
            String expectViewmp3 = "Corridor+ (Split Ceiling Corridor View)";
            softAssert.assertEquals(expectViewmp3, actualViewmp3);
            if (!expectViewmp3.equals(actualViewmp3)) {
                Reporter.log("MP3 Corridor+ (Split Ceiling Corridor View) is not selected : " + expectViewmp3.equals(actualViewmp3));
            } else {
                Reporter.log("MP3 Corridor+ (Split Ceiling Corridor View) is selected : " + expectViewmp3.equals(actualViewmp3));
            }

            WebElement CancelMP3 = driver.findElement(By.xpath(xpathHelper.getValue("Cancel")));
            js.executeScript("arguments[0].scrollIntoView();", CancelMP3);
            CancelMP3.click();

            //MP4 Settings
            WebElement selectMP4 = driver.findElement(By.xpath(xpathHelper.getValue("SelectMP4")));
            js.executeScript("arguments[0].scrollIntoView();", selectMP4);
            driver.findElement(By.xpath(xpathHelper.getValue("MP4"))).click();
            WebElement videoSettingMP4 = driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
            js.executeScript("arguments[0].scrollIntoView();", videoSettingMP4);


            String actualViewMP4 = driver.findElement(By.xpath(xpathHelper.getValue("VCam"))).getText();
            String expectViewMP4 = "VCam";
            softAssert.assertEquals(expectViewMP4, actualViewMP4);
            if (!expectViewMP4.equals(expectViewMP4)) {
                Reporter.log("MP4 VCam is not selected : " + expectViewMP4.equals(actualViewMP4));
            } else {
                Reporter.log("MP4 VCam is selected : " + expectViewMP4.equals(actualViewMP4));
            }

            String actualVideoMP4 = driver.findElement(By.xpath(xpathHelper.getValue("JPEG"))).getText();
            String expectVideoMP4 = "JPEG";
            softAssert.assertEquals(expectVideoMP4, actualVideoMP4);
            if (!expectVideoMP4.equals(actualVideoMP4)) {
                Reporter.log("MP4 Video Compression is not same : " + expectVideoMP4.equals(actualVideoMP4));
            } else {
                Reporter.log("MP4 Video Compression is same : " + expectVideoMP4.equals(actualVideoMP4));
            }

            String actualFps = driver.findElement(By.xpath(xpathHelper.getValue("SelectFPS"))).getText();
            String expectFps = "8";
            softAssert.assertEquals(expectFps, actualFps);
            if (!expectFps.equals(actualFps)) {
                Reporter.log("MP4 Fps is not same : " + expectFps.equals(actualFps));
            } else {
                Reporter.log("MP4 Fps is same : " + expectFps.equals(actualFps));
            }

            String actualQuality = driver.findElement(By.xpath(xpathHelper.getValue("Quality"))).getAttribute("value");
            String expectQuality = "60";
            softAssert.assertEquals(expectQuality, actualQuality);
            if (!expectQuality.equals(actualQuality)) {
                Reporter.log("MP4 Quality is not same : " + expectQuality.equals(actualQuality));
            } else {
                Reporter.log("MP4 Quality is same : " + expectQuality.equals(actualQuality));
            }

            WebElement resolution = driver.findElement(By.xpath(xpathHelper.getValue("Videoresolution")));
            js.executeScript("arguments[0].scrollIntoView();", resolution);
            String actualRateMP4 = driver.findElement(By.xpath(xpathHelper.getValue("Bitrate"))).getAttribute("value");
            String expectRateMP4 = "1100";
            softAssert.assertEquals(expectRateMP4, actualRateMP4);
            if (!expectRateMP4.equals(actualRateMP4)) {
                Reporter.log("MP4 Bit Rate is not same : " + expectRateMP4.equals(actualRateMP4));
            } else {
                Reporter.log("MP4 Bit Rate is same : " + expectRateMP4.equals(actualRateMP4));
            }

            WebElement CancelMP4 = driver.findElement(By.xpath(xpathHelper.getValue("Cancel")));
            js.executeScript("arguments[0].scrollIntoView();", CancelMP4);
            CancelMP4.click();

            //Navigated to video/audio tab
            driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
            //Navigated to Overlays tab
            driver.findElement(By.xpath(xpathHelper.getValue("Overlays"))).click();


            //Checking the caption name
            String actualname = driver.findElement(By.xpath(xpathHelper.getValue("CaptionName"))).getText();
            String expectname = "Testing";
            softAssert.assertEquals(expectname, actualname);
            if (!expectname.equals(actualname)) {
                Reporter.log("Caption name is not same : " + expectname.equals(actualname));
            } else {
                Reporter.log("Caption name is same : " + expectname.equals(actualname));
            }

            //Checking the naming position
            String actualposition = driver.findElement(By.xpath(xpathHelper.getValue("PositionType"))).getText();
            String expectposition = "Center";
            softAssert.assertEquals(expectposition, actualposition);
            if (!expectposition.equals(actualposition)) {
                Reporter.log("Position is not same : " + expectposition.equals(actualposition));
            } else {
                Reporter.log("Position is same : " + expectposition.equals(actualposition));
            }

            //Checking the naming size
            String actualnamesize = driver.findElement(By.xpath(xpathHelper.getValue("Size"))).getText();
            String expectnamesize = "24";
            softAssert.assertEquals(expectnamesize, actualnamesize);
            if (!expectnamesize.equals(actualnamesize)) {
                Reporter.log("Size is not same : " + expectnamesize.equals(actualnamesize));
            } else {
                Reporter.log("Size is same : " + expectnamesize.equals(actualnamesize));
            }

            //Navigated to video/audio
            driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
            //Navigated to Global StreamLite Compression
            driver.findElement(By.xpath(xpathHelper.getValue("GlobalStream"))).click();

            //Checking the Tracked Objects is selected
            String actualmotiondetection = driver.findElement(By.xpath(xpathHelper.getValue("MotionDetection"))).getText();
            String expectmotiondetection = "Tracked Objects";
            softAssert.assertEquals(expectmotiondetection, actualmotiondetection);
            if (!expectmotiondetection.equals(actualmotiondetection)) {
                Reporter.log("Tracked Objects is not selected : " + expectmotiondetection.equals(actualmotiondetection));
            } else {
                Reporter.log("Tracked Objects is selected : " + expectmotiondetection.equals(actualmotiondetection));
            }

            //Checking the Region size
            String actualregionsize = driver.findElement(By.xpath(xpathHelper.getValue("RectPercentage"))).getAttribute("value");
            String expctregionsize = "50";
            softAssert.assertEquals(expctregionsize, actualregionsize);
            if (!expctregionsize.equalsIgnoreCase(actualregionsize)) {
                Reporter.log("Region size is not same : " + expctregionsize.equalsIgnoreCase(actualregionsize));
            } else {
                Reporter.log("Region size is same : " + expctregionsize.equalsIgnoreCase(actualregionsize));
            }

            //Checking the Region persistance
            String actualpersistancesize = driver.findElement(By.xpath(xpathHelper.getValue("RectPersistence"))).getAttribute("value");
            WebElement persistance = driver.findElement(By.xpath(xpathHelper.getValue("RectPersistence")));
            js.executeScript("arguments[0].scrollIntoView();", persistance);
            String expctpersistancesize = "30";
            softAssert.assertEquals(expctpersistancesize, actualpersistancesize);
            if (!expctpersistancesize.equalsIgnoreCase(actualpersistancesize)) {
                Reporter.log("Region persistance is not same : " + expctpersistancesize.equalsIgnoreCase(actualpersistancesize));
            } else {
                Reporter.log("Region persistance is same : " + expctpersistancesize.equalsIgnoreCase(actualpersistancesize));
            }

            //Checking the ThresholdToMotion
            String actualthresholdToMotion = driver.findElement(By.xpath(xpathHelper.getValue("ThresholdToMotion"))).getAttribute("value");
            WebElement Threshold = driver.findElement(By.xpath(xpathHelper.getValue("ThresholdToMotion")));
            js.executeScript("arguments[0].scrollIntoView();", Threshold);
            String expectthresholdToMotion = "15";
            softAssert.assertEquals(expectthresholdToMotion, actualthresholdToMotion);
            if (!expectthresholdToMotion.equals(actualthresholdToMotion)) {
                Reporter.log("ThresholdToMotion is not same : " + expectthresholdToMotion.equals(actualthresholdToMotion));
            } else {
                Reporter.log("ThresholdToMotion is same : " + expectthresholdToMotion.equals(actualthresholdToMotion));
            }

            //Checking the ThresholdToNoMotion
            String actualthresholdToNoMotion = driver.findElement(By.xpath(xpathHelper.getValue("ThresholdToNoMotion"))).getAttribute("value");
            String expectthresholdToNoMotion = "15";
            softAssert.assertEquals(expectthresholdToNoMotion, actualthresholdToNoMotion);
            if (!expectthresholdToNoMotion.equals(actualthresholdToNoMotion)) {
                Reporter.log("ThresholdToNoMotion is not same : " + expectthresholdToNoMotion.equals(actualthresholdToNoMotion));
            } else {
                Reporter.log("ThresholdToNoMotion is same : " + expectthresholdToNoMotion.equals(actualthresholdToNoMotion));
            }

            //Checking the Delay
            String actualDelay = driver.findElement(By.xpath(xpathHelper.getValue("Delay"))).getAttribute("value");
            WebElement delay = driver.findElement(By.xpath(xpathHelper.getValue("Delay")));
            js.executeScript("arguments[0].scrollIntoView();", delay);
            String expectDelay = "15";
            softAssert.assertEquals(expectDelay, actualDelay);
            if (!expectDelay.equals(actualDelay)) {
                Reporter.log("Delay is not same : " + expectDelay.equals(actualDelay));
            } else {
                Reporter.log("Delay is same : " + expectDelay.equals(actualDelay));
            }

            //Navigated to motion/analytics	tab
            WebElement motionanalytics = driver.findElement(By.xpath(xpathHelper.getValue("SearchmotionTab")));
            motionanalytics.sendKeys(Keys.ENTER);

            WebElement Motionsettings = driver.findElement(By.xpath(xpathHelper.getValue("MotioAnalytics")));
            Motionsettings.sendKeys(Keys.ENTER);

            //Checking the sensitivity value
            String actualsensitivity = driver.findElement(By.xpath(xpathHelper.getValue("Sensitivity"))).getAttribute("value");
            String expectsensitivity = "15";
            softAssert.assertEquals(expectsensitivity, actualsensitivity);
            if (!expectsensitivity.equals(actualsensitivity)) {
                Reporter.log("sensitivity is not same : " + expectsensitivity.equals(actualsensitivity));
            } else {
                Reporter.log("sensitivity is same : " + expectsensitivity.equals(actualsensitivity));
            }

            //Checking the threshold value
            String actualthreshold = driver.findElement(By.xpath(xpathHelper.getValue("Threshold"))).getAttribute("value");
            String expectthreshold = "15";
            softAssert.assertEquals(expectthreshold, actualthreshold);
            if (!expectthreshold.equals(actualthreshold)) {
                Reporter.log("threshold is not same : " + expectthreshold.equals(actualthreshold));
            } else {
                Reporter.log("threshold is same : " + expectthreshold.equals(actualthreshold));
            }

            //Checking the latency value
            String actualatency = driver.findElement(By.xpath(xpathHelper.getValue("Latency"))).getAttribute("value");
            WebElement latency = driver.findElement(By.xpath("//input[@id='latency']"));
            js.executeScript("arguments[0].scrollIntoView();", latency);
            String expectlatency = "400";
            softAssert.assertEquals(expectlatency, actualatency);
            if (!expectlatency.equals(actualatency)) {
                Reporter.log("latency is not same : " + expectlatency.equals(actualatency));
            } else {
                Reporter.log("latency is same : " + expectlatency.equals(actualatency));
            }

            //Checking the persistance value
            String actualpersistance = driver.findElement(By.xpath(xpathHelper.getValue("Persistance"))).getAttribute("value");
            String expectpersistance = "1000";
            softAssert.assertEquals(expectpersistance, actualpersistance);
            if (!expectpersistance.equals(actualpersistance)) {
                Reporter.log("persistance is not same : " + expectpersistance.equals(actualpersistance));
            } else {
                Reporter.log("persistance is same : " + expectpersistance.equals(actualpersistance));
            }

            //Checking the height value
            String actualheight = driver.findElement(By.xpath(xpathHelper.getValue("Height"))).getAttribute("value");
            WebElement height = driver.findElement(By.xpath("//input[@id='height']"));
            js.executeScript("arguments[0].scrollIntoView();", height);
            String expectheight = "15.0";
            softAssert.assertEquals(expectheight, actualheight);
            if (!expectheight.equals(actualheight)) {
                Reporter.log("height is not same : " + expectheight.equals(actualheight));
            } else {
                Reporter.log("height is same : " + expectheight.equals(actualheight));
            }

            //Navigated to user
            WebElement user = driver.findElement(By.xpath(xpathHelper.getValue("SearchUser")));
            user.sendKeys(Keys.ENTER);

            //Check the new user
            String actualuser2 = driver.findElement(By.xpath(xpathHelper.getValue("NewUser"))).getText();
            String expectuser2 = "admin1";
            softAssert.assertEquals(expectuser2, actualuser2);
            if (!expectuser2.equals(actualuser2)) {
                Reporter.log("New Username is not created : " + expectuser2.equals(actualuser2));
            } else {
                Reporter.log("New Username- admin1 is created : " + expectuser2.equals(actualuser2));
            }

//            Firmware downgrade code

        //Running underAutoit
            // Device TAB- Navigating to Device tab
            driver.findElement(By.xpath(xpathHelper.getValue("Device"))).click();
            Reporter.log("Navigatied to Device tab");
            //Firmware upgrade using Autoit tool
            driver.findElement(By.xpath(xpathHelper.getValue("FirmwareTab"))).click();
            Thread.sleep(2000);
            WebElement chooseFile = driver.findElement(By.xpath(xpathHelper.getValue("FirmwareUpgrade")));
            chooseFile.click();
            UploadFirmwareHelper.uploadFirmwareByAutoIt(fromFirmware);
            Reporter.log("Firmware Upgrade is completed");
            Thread.sleep(655000);
            driver.close();

    }
}