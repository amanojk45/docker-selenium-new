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

import java.io.IOException;

/**
 * @author Naveena U S
 */
public class UpgradeToFrom {

    @Test(dataProvider = "cam-data",dataProviderClass = TestRunner.class)
    public static void testUpgradeToFrom(String url,String username,String password,String cameraVersion,String fromFirmware,String toFirmware) throws InterruptedException, IOException {
        LocatorHelper xpathHelper = new LocatorHelper(ConstPath.upgradeToFromXpath + fromFirmware + ".properties");
        WebDriver driver = AuthHelper.getInstance().driver(username,password,url);

        //Expert Mode on
        //1.0.2 and 1.1.0 doesn't support Extertmode
        if (! fromFirmware.equals("1.0.2")&&! fromFirmware.equals("1.1.0")) {
            WebElement expertmode = driver.findElement(By.xpath(xpathHelper.getValue("Expertmode")));
            WebDriverWait wait = new WebDriverWait(driver, 2000);
            WebElement expertmodeLable = wait.until(ExpectedConditions.elementToBeClickable(expertmode.findElement(By.xpath(".."))));
            expertmodeLable.click();
        } else
            Reporter.log("Expert mode is not available in this firmware");

        //Device TAB- Navigating to Device tab
        driver.findElement(By.xpath(xpathHelper.getValue("Device"))).click();
        Reporter.log("Navigated to device tab");

        //DeviceInformation- adding new name
        WebElement cameraName= driver.findElement(By.xpath(xpathHelper.getValue("FriendlyName")));
        cameraName.clear();
        cameraName.sendKeys(cameraVersion);
        Reporter.log("Camera name is changed");

        //Enable EVO-12 ONVIF Emulation
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement emuMode = driver.findElement(By.xpath(xpathHelper.getValue("EmulationMode")));
        js.executeScript("arguments[0].scrollIntoView();", emuMode);
        emuMode.click();
        driver.findElement(By.xpath(xpathHelper.getValue("Save"))).click();
        Reporter.log("Emulation mode is enabled");

        //Date&Time- selecting new timezone
        driver.findElement(By.xpath(xpathHelper.getValue("DateAndTime"))).click();
        WebElement timeWebElement = driver.findElement(By.id(xpathHelper.getValue("Timezone")));
        timeWebElement.sendKeys("HST");
        Reporter.log("date/time is changed");

        //Date&Time- Selecting NTP
        driver.findElement(By.xpath(xpathHelper.getValue("NTP"))).click();
        Reporter.log("Changed Date and time - NTP");

        //save the changes
        WebElement saveDevice = driver.findElement(By.xpath(xpathHelper.getValue("Save")));
        js.executeScript("arguments[0].scrollIntoView();", saveDevice);
        saveDevice.click();

        //Network settings
        if (! fromFirmware.equals("1.0.2")) {
            driver.findElement(By.xpath(xpathHelper.getValue("Network"))).click();

//        //Network settings-DHCP Selecting
//        driver.findElement(By.xpath(xpathHelper.getValue("DHCP"))).click();
//        Reporter.log("Selected DHCP");
//
//        //Network settings-Adding static address
//        WebElement staticIp = driver.findElement(By.xpath(xpathHelper.getValue("Staticip")));
//        staticIp.click();
//        staticIp.clear();
//        staticIp.sendKeys(AuthHelper.staticip);
//        Reporter.log("Added new static ip address");
//
//        //save the changes
//        WebElement saveNetworksettings = driver.findElement(By.xpath(xpathHelper.getValue("Save")));
//        js.executeScript("arguments[0].scrollIntoView();", saveNetworksettings);
//        saveNetworksettings.click();
//        Thread.sleep(1000);
//
//        //Network settings
//        driver.findElement(By.xpath(xpathHelper.getValue("Network"))).click();
        //Network settings-Advanced Network Configuration
        driver.findElement(By.xpath(xpathHelper.getValue("Advancesettings"))).click();

        //Adding new HTTPS value
        WebElement addHttps = driver.findElement(By.xpath(xpathHelper.getValue("HTTP")));
        addHttps.click();
        addHttps.clear();
        addHttps.sendKeys("1443");

        //Adding new RTPS value
        WebElement addRtsp = driver.findElement(By.xpath(xpathHelper.getValue("RTSP")));
        addRtsp.click();
        addRtsp.clear();
        addRtsp.sendKeys("1554");

        //Save the changes
        WebElement saveSettings = driver.findElement(By.xpath(xpathHelper.getValue("Save")));
        js.executeScript("arguments[0].scrollIntoView();", saveSettings);
        saveSettings.click();}

        //Video/Audio Image Settings
        driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
        Reporter.log("Navigated to video/audio tab");
        driver.findElement(By.xpath(xpathHelper.getValue("ImageSettings"))).click();
        Reporter.log("Navigated to ImageSettings tab");
        driver.findElement(By.xpath(xpathHelper.getValue("PwdLineFreq"))).click();
        Reporter.log("Selected 50Hz ");
        WebElement hdrOff= driver.findElement(By.xpath(xpathHelper.getValue("TrueDetailHDR")));
        hdrOff.click();
        js.executeScript("arguments[0].scrollIntoView();", hdrOff);
        Reporter.log("HDR is off");

        //Advanced Light Management Technology setting
        driver.findElement(By.xpath(xpathHelper.getValue("FrameRate"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Balanced"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Motion"))).click();
        Reporter.log("Selected Motion");

        // Exposure Setting
        WebElement ExpComp= driver.findElement(By.xpath(xpathHelper.getValue("ExposureComp")));
        ExpComp.clear();
        ExpComp.sendKeys("7");
        Reporter.log("Selected Exposure Setting");

        //Brightness Setting
        WebElement Brightness= driver.findElement(By.xpath(xpathHelper.getValue("Brightness")));
        Brightness.clear();
        Brightness.sendKeys("50");
        Reporter.log("Selected brightness- 50");

        //Contrast Setting
        WebElement Contrast= driver.findElement(By.xpath(xpathHelper.getValue("Contrast")));
        Contrast.clear();
        Contrast.sendKeys("9");
        Reporter.log("Selected contrast- 9");

        //Sharpness Setting
        WebElement Sharpness= driver.findElement(By.xpath(xpathHelper.getValue("Sharpness")));
        Sharpness.clear();
        Sharpness.sendKeys("10");
        Reporter.log("Selected Sharpness- 10");

        //Saturation Setting
        WebElement Saturation= driver.findElement(By.xpath(xpathHelper.getValue("Saturation")));
        Saturation.clear();
        Saturation.sendKeys("8");
        Reporter.log("Selected saturation- 8");

        //Enable Wide Dynamic Range
        WebElement Wdr= driver.findElement(By.xpath(xpathHelper.getValue("WideDynamicRange")));
        Wdr.click();
        Reporter.log("Enable Wide Dynamic Range");
        js.executeScript("arguments[0].scrollIntoView();",Wdr);

        //WdrValue Setting
        WebElement WdrValue= driver.findElement(By.xpath(xpathHelper.getValue("Wdr")));
        WdrValue.clear();
        WdrValue.sendKeys("50");
        Reporter.log("WdrValue- 50");

        //Camera mount position setting
        WebElement mount = driver.findElement(By.xpath(xpathHelper.getValue("CameraMountPosition")));
        mount.click();
        driver.findElement(By.xpath(xpathHelper.getValue("CameraTable"))).click();
        Reporter.log("Camera mount position is set to Table");
        js.executeScript("arguments[0].scrollIntoView();", mount);
        Thread.sleep(2000);

        //Rotate camera setting
        WebElement Rotate=driver.findElement(By.xpath(xpathHelper.getValue("RotateCamera")));
        Rotate.click();
        Reporter.log("Rotate camera setting set to Invert");

        //IR Cut Filter setting
        WebElement Filter=driver.findElement(By.xpath(xpathHelper.getValue("IrCutFilter")));
        Filter.click();
        driver.findElement(By.xpath(xpathHelper.getValue("IrNightMode"))).click();
        Reporter.log("IR Cut Filter setting set to Night Mode");

        //colour space setting
        driver.findElement(By.xpath(xpathHelper.getValue("ColourSpace"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("ColourFS"))).click();
        Reporter.log("colour space setting set to BT709 FS");

        //Save the changes
        WebElement saveImg = driver.findElement(By.xpath(xpathHelper.getValue("SaveChanges")));
        saveImg.click();
        if (fromFirmware.equals("1.0.2")|| fromFirmware.equals("1.1.0")){
            Thread.sleep(70000);
        } else
            Reporter.log("Save and Reboot is not required for this firmware");

        // Select Multi mode
        //1.0.2 and 1.1.0 doesn't support Multimode
        if (! fromFirmware.equals("1.0.2")&&! fromFirmware.equals("1.1.0")) {
        driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
        Reporter.log("Navigated to video/audio tab");
        WebElement modeSelect= driver.findElement(By.xpath(xpathHelper.getValue("Multimode")));
        Reporter.log("Selected Multimode");
        modeSelect.click();
        driver.findElement(By.xpath(xpathHelper.getValue("Apply"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Confirm"))).click();
        //Adding delay here for mode change
        Thread.sleep(12000);
        driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();

        //MP1 settings
        driver.findElement(By.xpath(xpathHelper.getValue("MP1"))).click();
        Reporter.log("Editing MP1 Settings ");
        WebElement videoSettingMP1= driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
        js.executeScript("arguments[0].scrollIntoView();", videoSettingMP1);
        WebElement videoCompression= driver.findElement(By.xpath(xpathHelper.getValue("H264")));
        videoCompression.click();
        driver.findElement(By.xpath(xpathHelper.getValue("H265"))).click();
        Reporter.log("Selected Video Compression - H265 ");
        WebElement videoSize =driver.findElement(By.xpath(xpathHelper.getValue("Videosize")));
        js.executeScript("arguments[0].scrollIntoView();", videoSize);
        WebElement bitRateMode =driver.findElement(By.xpath(xpathHelper.getValue("Bitratemode")));
        bitRateMode.click();
        driver.findElement(By.xpath(xpathHelper.getValue("Constantbitrate"))).click();
        Reporter.log("Selected Constant Bit Rate");
        WebElement bitRate= driver.findElement(By.xpath(xpathHelper.getValue("Bitrate")));
        bitRate.click();
        bitRate.clear();
        bitRate.sendKeys("25000");
        Reporter.log("Added 25000 Bit Rate");
        WebElement govLength=driver.findElement(By.xpath(xpathHelper.getValue("Govlength")));
        js.executeScript("arguments[0].scrollIntoView();", govLength);
        driver.findElement(By.xpath(xpathHelper.getValue("Multicast"))).click();
        Reporter.log("Selected Video Multicast");
        WebElement saveMP1 = driver.findElement(By.xpath(xpathHelper.getValue("Savemodesettings")));
        js.executeScript("arguments[0].scrollIntoView();", saveMP1);
        saveMP1.click();
        Thread.sleep(1000);

        //MP2 settings
        driver.findElement(By.xpath(xpathHelper.getValue("MP2"))).click();
        Reporter.log("Editing MP2 Settings ");
        WebElement videosettingMP2= driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
        js.executeScript("arguments[0].scrollIntoView();", videosettingMP2);
        driver.findElement(By.xpath(xpathHelper.getValue("Fisheye"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Panoramic"))).click();
        Reporter.log("Selected Pano mode");
        driver.findElement(By.xpath(xpathHelper.getValue("Confirm"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Resolutionselect"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Resolution"))).click();
        Reporter.log("Selected 1920 x 768 resolution ");
        WebElement quality= driver.findElement(By.xpath(xpathHelper.getValue("Quality")));
        js.executeScript("arguments[0].scrollIntoView();", quality);
        WebElement gop= driver.findElement(By.xpath(xpathHelper.getValue("Gopselect")));
        gop.click();
        driver.findElement(By.xpath(xpathHelper.getValue("Gop"))).click();
        Reporter.log("Selected Gop to 100");
        WebElement govLengthMP2=driver.findElement(By.xpath(xpathHelper.getValue("Govlength")));
        js.executeScript("arguments[0].scrollIntoView();", govLengthMP2);
        driver.findElement(By.xpath(xpathHelper.getValue("Alwayson"))).click();
        Reporter.log("Selected Always On");
        WebElement saveMP2 = driver.findElement(By.xpath(xpathHelper.getValue("Savemodesettings")));
        js.executeScript("arguments[0].scrollIntoView();", saveMP2);
        saveMP2.click();
        Thread.sleep(1000);

        //MP3 settings
        WebElement selectMP3=driver.findElement(By.xpath(xpathHelper.getValue("SelectMP3")));
        js.executeScript("arguments[0].scrollIntoView();", selectMP3);
        driver.findElement(By.xpath(xpathHelper.getValue("MP3"))).click();
        Reporter.log("Editing MP3 Settings ");
        WebElement videoSettinggMP3= driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
        js.executeScript("arguments[0].scrollIntoView();", videoSettinggMP3);
        driver.findElement(By.xpath(xpathHelper.getValue("Fisheye"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Corridor"))).click();
        Reporter.log("Selected Corridor mode");
        driver.findElement(By.xpath(xpathHelper.getValue("Confirm"))).click();
        WebElement Quality=driver.findElement(By.xpath(xpathHelper.getValue("Quality")));
        js.executeScript("arguments[0].scrollIntoView();",Quality);
        WebElement saveMP3 = driver.findElement(By.xpath(xpathHelper.getValue("Savemodesettings")));
        js.executeScript("arguments[0].scrollIntoView();", saveMP3);
        saveMP3.click();

        //MP4 Settings
        WebElement selectMP4=driver.findElement(By.xpath(xpathHelper.getValue("SelectMP4")));
        js.executeScript("arguments[0].scrollIntoView();", selectMP4);
        driver.findElement(By.xpath(xpathHelper.getValue("MP4"))).click();
        Reporter.log("Editing MP4 Settings ");
        WebElement videoSettingMP4= driver.findElement(By.xpath(xpathHelper.getValue("Videosettings")));
        js.executeScript("arguments[0].scrollIntoView();", videoSettingMP4);
        driver.findElement(By.xpath(xpathHelper.getValue("Fisheye"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("VCam"))).click();
        Reporter.log("Selected VCam mode");
        driver.findElement(By.xpath(xpathHelper.getValue("Confirm"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("H264"))).click();
        WebElement videoComp=driver.findElement(By.xpath(xpathHelper.getValue("JPEG")));
        videoComp.click();
        js.executeScript("arguments[0].scrollIntoView();", videoComp);
        Reporter.log("Selected Video Compression -  JPEG  ");
        WebElement PTZ= driver.findElement(By.xpath(xpathHelper.getValue("PTZ")));
        js.executeScript("arguments[0].scrollIntoView();", PTZ);
        WebElement FrameRate=driver.findElement(By.xpath(xpathHelper.getValue("SelectFPS")));
        FrameRate.click();
        driver.findElement(By.xpath(xpathHelper.getValue("FPS"))).click();
        Reporter.log("Selected framesPerSecond - 8 ");
        WebElement qualityMP4 =driver.findElement(By.xpath(xpathHelper.getValue("Quality")));
        qualityMP4.click();
        qualityMP4.clear();
        qualityMP4.sendKeys("60");
        Reporter.log("Selected quality - 60 ");
        WebElement resolution= driver.findElement(By.xpath(xpathHelper.getValue("Videoresolution")));
        js.executeScript("arguments[0].scrollIntoView();", resolution);
        WebElement maxbitrateMP4= driver.findElement(By.xpath(xpathHelper.getValue("Bitrate")));
        maxbitrateMP4.click();
        maxbitrateMP4.clear();
        maxbitrateMP4.sendKeys("1100");
        Reporter.log("Selected Max Bit Rate to 1100");
        WebElement saveMP4 = driver.findElement(By.xpath(xpathHelper.getValue("Savemodesettings")));
        js.executeScript("arguments[0].scrollIntoView();", saveMP4);
        saveMP4.click();}
        else{
            Reporter.log("Firmware doesn't support Multi mode");
        }

        //Overlays Tab
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
        Reporter.log("Navigated to video/audio tab");
        driver.findElement(By.xpath(xpathHelper.getValue("Overlays"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Add"))).click();
        WebElement CaptionName= driver.findElement(By.xpath(xpathHelper.getValue("CaptionName")));
        CaptionName.click();
        CaptionName.sendKeys("Testing");
        Reporter.log("Added ScreeCaptionName");
        driver.findElement(By.xpath(xpathHelper.getValue("TimeRadiobtn"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("TimeFormat"))).click();
        if (! fromFirmware.equals("1.0.2")&&! fromFirmware.equals("1.1.0")) {
        driver.findElement(By.xpath(xpathHelper.getValue("SelectTimeFormat"))).click();
        Reporter.log("Added timeFormat");}
        driver.findElement(By.xpath(xpathHelper.getValue("DateDropdwnlist"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpathHelper.getValue("DateFormat"))).click();
        Reporter.log("Added dateFormat");
        Thread.sleep(2000);
        WebElement PositionDropdwnlist= driver.findElement(By.xpath(xpathHelper.getValue("PositionDropdwnlist")));
        js.executeScript("arguments[0].scrollIntoView();", PositionDropdwnlist);
        PositionDropdwnlist.click();
        WebElement Position= driver.findElement(By.xpath(xpathHelper.getValue("PositionType")));
        js.executeScript("arguments[0].scrollIntoView();", Position);
        Position.click();
        if (fromFirmware.equals("1.0.2")||fromFirmware.equals("1.1.0")){
        driver.findElement(By.xpath(xpathHelper.getValue("Centre"))).click();
        Reporter.log("Added Position to Centre");}
        WebElement FontSize=driver.findElement(By.xpath(xpathHelper.getValue("Size")));
        FontSize.clear();
        FontSize.sendKeys("24");
        Reporter.log("Added FontSize");
        driver.findElement(By.xpath(xpathHelper.getValue("TextColor"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("Yellow"))).click();
        Reporter.log("Added Text Colour");
        driver.findElement(By.xpath(xpathHelper.getValue("AddCaption"))).click();
        Reporter.log("Added new overlay");
        Thread.sleep(1000);

        //Global StreamLite Compression
        driver.findElement(By.xpath(xpathHelper.getValue("VideoAudio"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("GlobalStream"))).click();
        Reporter.log("Navigated to Global StreamLite Compression Settings tab");
        WebElement ColourMap = driver.findElement(By.xpath(xpathHelper.getValue("ColourMap")));
        Reporter.log("selected ColourMap");
        ColourMap.click();
        driver.findElement(By.xpath(xpathHelper.getValue("MotionDetection"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("TrackObject"))).click();
        Reporter.log("selected tracked objects");
        WebElement RegionSize= driver.findElement(By.xpath(xpathHelper.getValue("RectPercentage")));
        RegionSize.clear();
        RegionSize.sendKeys("50");
        Reporter.log("RectPercentage set to 50 ");
        WebElement RegionPersistance=driver.findElement(By.xpath(xpathHelper.getValue("RectPersistence")));
        RegionPersistance.clear();
        RegionPersistance.sendKeys("30");
        Reporter.log("RectPersistence set to 30 ");
        WebElement MergeCenterDistance = driver.findElement(By.xpath(xpathHelper.getValue("CenterDistance")));
        js.executeScript("arguments[0].scrollIntoView();", MergeCenterDistance);
        MergeCenterDistance.clear();
        MergeCenterDistance.sendKeys("15");
        Reporter.log("CenterDistance set to 15 ");
        driver.findElement(By.xpath(xpathHelper.getValue("MotionAlgorithm"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("MotionTrigger"))).click();
        WebElement ThresholdToMotion = driver.findElement(By.xpath(xpathHelper.getValue("ThresholdToMotion")));
        ThresholdToMotion.clear();
        ThresholdToMotion.sendKeys("15");
        Reporter.log("ThresholdToMotion set to 15 ");
        WebElement ThresholdToNoMotion = driver.findElement(By.xpath(xpathHelper.getValue("ThresholdToNoMotion")));
        ThresholdToNoMotion.clear();
        ThresholdToNoMotion.sendKeys("15");
        Reporter.log("ThresholdToNoMotion set to 15 ");
        WebElement Delay = driver.findElement(By.xpath(xpathHelper.getValue("Delay")));
        Delay.clear();
        Delay.sendKeys("15");
        Reporter.log("Delay set to 15 ");

        //Save the changes
        WebElement savevideo = driver.findElement(By.xpath(xpathHelper.getValue("Save")));
        js.executeScript("arguments[0].scrollIntoView();", savevideo);
        savevideo.click();

        //Motion/Analytics
        Thread.sleep(2000);
        WebElement motionanalytics= driver.findElement(By.xpath(xpathHelper.getValue("SearchmotionTab")));
        motionanalytics.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement Motionsettings= driver.findElement(By.xpath(xpathHelper.getValue("MotioAnalytics")));
        Motionsettings.sendKeys(Keys.ENTER);
        Reporter.log("Navigated to Global Settings tab");
        WebElement sensitivity = driver.findElement(By.xpath(xpathHelper.getValue("Sensitivity")));
        sensitivity.clear();
        sensitivity.sendKeys("15");
        Reporter.log("sensitivity set to 15 ");
        WebElement threshold = driver.findElement(By.xpath(xpathHelper.getValue("Threshold")));
        threshold.clear();
        threshold.sendKeys("15");
        Reporter.log("threshold set to 15 ");
        WebElement latency = driver.findElement(By.xpath(xpathHelper.getValue("Latency")));
        latency.clear();
        latency.sendKeys("400");
        Reporter.log("latency set to 15 ");
        WebElement persistance = driver.findElement(By.xpath(xpathHelper.getValue("Persistance")));
        persistance.clear();
        persistance.sendKeys("1000");
        Reporter.log("persistance set to 1000 ");
        WebElement height = driver.findElement(By.xpath(xpathHelper.getValue("Height")));
        height.clear();
        height.sendKeys("15");
        Reporter.log("height set to 15 ");

        //Save the changes
        WebElement save = driver.findElement(By.xpath(xpathHelper.getValue("SaveMotionSettings")));
        js.executeScript("arguments[0].scrollIntoView();", save);
        save.click();

        //Users	 Profile
        WebElement user= driver.findElement(By.xpath(xpathHelper.getValue("SearchUser")));
        user.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(xpathHelper.getValue("NewUser"))).click();
        WebElement un= driver.findElement(By.xpath(xpathHelper.getValue("UserName")));
        un.sendKeys("admin1");
        WebElement pwd= driver.findElement(By.xpath(xpathHelper.getValue("Password")));
        pwd.sendKeys("Admin@121");
        WebElement cnpwd= driver.findElement(By.xpath(xpathHelper.getValue("Confirmpassword")));
        cnpwd.click();
        cnpwd.sendKeys("Admin@121");
        WebElement accesslevel= driver.findElement(By.xpath(xpathHelper.getValue("Level")));
        js.executeScript("arguments[0].scrollIntoView();", accesslevel);
        accesslevel.click();
        driver.findElement(By.xpath(xpathHelper.getValue("User"))).click();
        driver.findElement(By.xpath(xpathHelper.getValue("AddNewUser"))).click();
        Reporter.log("New user is added");
        Thread.sleep(1000);

        //Firmware upgrade code//

        //Device TAB- Navigating to Device tab
        driver.findElement(By.xpath(xpathHelper.getValue("Device"))).click();
        Reporter.log("Navigatied to Device tab");
        //Firmware upgrade using Autoit tool
        driver.findElement(By.xpath(xpathHelper.getValue("FirmwareTab"))).click();
        Thread.sleep(1000);
        WebElement chooseFile=driver.findElement(By.xpath(xpathHelper.getValue("FirmwareUpgrade")));
        chooseFile.click();
        UploadFirmwareHelper.uploadFirmwareByAutoIt(toFirmware);
        Reporter.log("Firmware Upgrade is completed");
        Thread.sleep(655000);
        driver.close();

    }
}