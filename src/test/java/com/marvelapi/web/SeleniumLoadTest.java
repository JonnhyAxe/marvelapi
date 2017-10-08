
package com.marvelapi.web;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
//--
import org.openqa.selenium.By;
//--
import org.openqa.selenium.WebDriver;
//--
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


public class SeleniumLoadTest {

  private WebDriver driver;


    @Test()
    public void getBenParkerPower_withSelenium() {

        // driver = new FirefoxDriver();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\dev\\tools\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final String urlOverHttps = "http://marvel.com/universe/Parker%2C_Ben_%28Uncle_Ben%29?utm_campaign=apiRef&utm_source=746d7e48cbc8d37cf4dd63c53296c3c1";

        driver.get(urlOverHttps);

        WebElement search_button = driver.findElement(By.id("char-powers-content"));

        String text = search_button.getText();

        driver.quit();

    }

}

