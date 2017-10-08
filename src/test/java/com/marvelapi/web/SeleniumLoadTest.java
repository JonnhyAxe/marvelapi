
package com.marvelapi.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
//--
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//--
import org.openqa.selenium.WebDriver;
//--
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumLoadTest {

  private WebDriver driver;


    @Test()
    public void getBenParkerPower_withSeleniumWithoutWebBrowser() {

        String expetectedPowers = "None";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\dev\\marvelapi\\src\\test\\resources\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final String urlOverHttps = "http://marvel.com/universe/Parker%2C_Ben_%28Uncle_Ben%29?utm_campaign=apiRef&utm_source=746d7e48cbc8d37cf4dd63c53296c3c1";

        driver.get(urlOverHttps);

        WebDriverWait wait = new WebDriverWait(driver, 600);
        By powerDiv = By.id("char-powers-content");
        wait.until(ExpectedConditions.presenceOfElementLocated(powerDiv));
        WebElement search_button = driver.findElement(powerDiv);
        String powerText = search_button.getText();
        String script = "return arguments[0].innerText";
        String hiddenPowers = (String) ((JavascriptExecutor) driver).executeScript(script, search_button);


        assertThat(powerText, notNullValue());
        assertThat(powerText, equalTo(""));
        assertThat(hiddenPowers, equalTo(expetectedPowers));

        driver.quit();

    }

    @Test()
    public void get3DManPower_withSeleniumWithoutWebBrowser() {

        String expetectedPowers = "Through concentration, Hal could merge the images of his brother imprinted on his glasses and thus cause his brother Chuck to reappear as a three-dimensional man, clad in an altered version of his experimental flight suit and endowed with physical abilities roughly three times greater than those of an ordinary human. Hal would fall into a trance-like state when Chuck appeared, and Chuck could only exist in the three-dimensional world for three hours at a time, after which Hal had to revive.\nAs the 3-D Man, Chandler possessed roughly three times the physical abilities and sensory acuity of an ordinary human in peak condition and is capable of slightly superhuman strength and speed. His stamina, durability, agility and reflexes are also estimated to be superhuman, namely roughly triple that of a human in peak physical condition. He could also sense Skrulls no matter what form they took.";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\dev\\marvelapi\\src\\test\\resources\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final String urlOverHttps = "http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=746d7e48cbc8d37cf4dd63c53296c3c1";
        driver.get(urlOverHttps);

        WebDriverWait wait = new WebDriverWait(driver, 600);
        By powerDiv = By.id("char-powers-content");
        wait.until(ExpectedConditions.presenceOfElementLocated(powerDiv));
        WebElement search_button = driver.findElement(powerDiv);
        String powerText = search_button.getText();
        String script = "return arguments[0].innerText";
        String hiddenPowers = (String) ((JavascriptExecutor) driver).executeScript(script, search_button);


        assertThat(powerText, notNullValue());
        assertThat(powerText, equalTo(""));
        assertThat(hiddenPowers, equalTo(expetectedPowers));

        driver.quit();

    }

}

