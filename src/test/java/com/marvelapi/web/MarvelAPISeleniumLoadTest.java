
package com.marvelapi.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
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
import org.springframework.context.annotation.Profile;

@Profile("live")
public class MarvelAPISeleniumLoadTest {

    private WebDriver driver;

    private Properties properties = new Properties();

    private String PHANTOMJS_EXECUTABLE_PATH_PROPERTY;

    @Before
    public void init() throws IOException {

        properties.load(MarvelAPIGoogleTranslate.class.getResourceAsStream("/application.properties"));

        PHANTOMJS_EXECUTABLE_PATH_PROPERTY = MarvelAPIGoogleTranslate.class.getResource("/phantomjs.exe").getFile();
    }

    @After
    public void quiteDriver() {

        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }

    @Test()
    public void givenBenParkerPowerWikiURL_getGetThePowerWithSeleniumWithoutWebBrowser_thenPowerIsNone() {

        String expetectedPowers = "None";
        By powerDiv = By.id(properties.getProperty("marvel.api.power.wiki"));
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOMJS_EXECUTABLE_PATH_PROPERTY);
        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final String _3dManPowerWikiURL = properties.getProperty("marvel.api.ben_parker_power_wiki_url");

        driver.get(_3dManPowerWikiURL);

        WebDriverWait wait = new WebDriverWait(driver, 600);

        wait.until(ExpectedConditions.presenceOfElementLocated(powerDiv));
        WebElement search_button = driver.findElement(powerDiv);
        String powerText = search_button.getText();
        String script = "return arguments[0].innerText";
        String hiddenPowers = (String) ((JavascriptExecutor) driver).executeScript(script, search_button);


        assertThat(powerText, notNullValue());
        assertThat(powerText, equalTo(""));
        assertThat(hiddenPowers, equalTo(expetectedPowers));

    }

    @Test()
    public void get3DManPower_getGetThePowerWithSeleniumWithoutWebBrowser_thenPowerIsCread3dDimention() {

        String expetectedPowers = "Through concentration, Hal could merge the images of his brother imprinted on his glasses and thus cause his brother Chuck to reappear as a three-dimensional man, clad in an altered version of his experimental flight suit and endowed with physical abilities roughly three times greater than those of an ordinary human. Hal would fall into a trance-like state when Chuck appeared, and Chuck could only exist in the three-dimensional world for three hours at a time, after which Hal had to revive.\nAs the 3-D Man, Chandler possessed roughly three times the physical abilities and sensory acuity of an ordinary human in peak condition and is capable of slightly superhuman strength and speed. His stamina, durability, agility and reflexes are also estimated to be superhuman, namely roughly triple that of a human in peak physical condition. He could also sense Skrulls no matter what form they took.";
        By powerDiv = By.id(properties.getProperty("marvel.api.power.wiki"));
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOMJS_EXECUTABLE_PATH_PROPERTY);
        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final String urlOverHttps = properties.getProperty("marvel.api.3d_man_power_wiki_url");
        driver.get(urlOverHttps);

        WebDriverWait wait = new WebDriverWait(driver, 600);
        wait.until(ExpectedConditions.presenceOfElementLocated(powerDiv));
        WebElement search_button = driver.findElement(powerDiv);
        String powerText = search_button.getText();
        String script = "return arguments[0].innerText";
        String hiddenPowers = (String) ((JavascriptExecutor) driver).executeScript(script, search_button);


        assertThat(powerText, notNullValue());
        assertThat(powerText, equalTo(""));
        assertThat(hiddenPowers, equalTo(expetectedPowers));

    }

}

