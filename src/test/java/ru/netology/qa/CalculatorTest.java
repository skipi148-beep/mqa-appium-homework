package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {

    private AppiumDriver driver;
    private MainScreen mainScreen;

    @BeforeEach
    public void createDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Android Emulator");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        mainScreen = new MainScreen(driver);
    }

    @Test
    public void testEmptyStringInput() {
        String initialText = mainScreen.textToBeChanged.getText();
        mainScreen.userInput.sendKeys("   ");
        mainScreen.buttonChange.click();
        Assertions.assertEquals(initialText, mainScreen.textToBeChanged.getText());
    }

    @Test
    public void testOpenTextInNewActivity() {
        String textToSet = "Netology Appium Test";
        mainScreen.userInput.sendKeys(textToSet);
        mainScreen.buttonActivity.click();
        Assertions.assertEquals(textToSet, mainScreen.activityText.getText());
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
