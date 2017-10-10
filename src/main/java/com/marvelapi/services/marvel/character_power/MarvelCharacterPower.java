package com.marvelapi.services.marvel.character_power;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marvelapi.config.MarvelAPIConfig;
import com.marvelapi.services.marvel.interfaces.CharacterPowerIdentity;
import com.swagger.marvelapi.services.marvel.model.Character;
import com.swagger.marvelapi.services.marvel.model.Url;

/**
 * CharacterPowerIdentity implementation to get Character Power from Wiki
 *
 */
@Component
// @Scope("singleton") Keep this in mind, cannot create more than one instance
public class MarvelCharacterPower implements CharacterPowerIdentity {

    private static final Logger logger = LoggerFactory.getLogger(MarvelCharacterPower.class);

    /**
     *
     */
    private static final String PHANTOMJS_EXE = "/phantomjs.exe";

    /**
     * script to be executed in order to
     */
    private static final String DIV_INNER_TEXT = "return arguments[0].innerText";

    /**
     *
     */
    private static final String WIKI_TYPE_URL = "wiki";

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    private static WebDriver driver;

    private String phantomExecutor;

    @PostConstruct
    public void init() throws Exception {

        phantomExecutor = MarvelCharacterPower.class.getResource(PHANTOMJS_EXE).getFile();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomExecutor);

        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @PreDestroy
    public void destroy() throws IOException {

        if (Objects.nonNull(driver)) {
            driver.quit();
            Runtime rt = Runtime.getRuntime();
            if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
                rt.exec("taskkill /F /IM phantomjs.exe");
            }
            else {
                rt.exec("kill -9 ");
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.marvelapi.services.marvel.interfaces.CharacterPowerIdentity#
     * getCharcaterPower(java.lang.Character)
     */
    @Override
    public synchronized String getCharacterPower(Character characterId) {

        String powerText = null;
        By powerDiv = By.id(marvelAPIConfig.getCharacterPowerWiki());

        if (Objects.isNull(phantomExecutor)) {
            return powerText;
        }


        Optional<Url> wikiURL = characterId.getUrls().stream().filter(url -> url.getType().equals(WIKI_TYPE_URL)).findFirst();
        if (wikiURL.isPresent() && Objects.nonNull(wikiURL.get().getUrl())) {

            driver.get(wikiURL.get().getUrl());

            WebDriverWait wait = new WebDriverWait(driver, 600);

            wait.until(ExpectedConditions.presenceOfElementLocated(powerDiv));
            WebElement searchButton = driver.findElement(powerDiv);
            powerText = searchButton.getText();
            if (Objects.isNull(powerText) || "".equals(powerText)) {
                powerText = (String) ((JavascriptExecutor) driver).executeScript(DIV_INNER_TEXT, searchButton);

            }

        }
        else {
            logger.debug("Wiki do not exist for character id " + characterId.getId());
        }
        logger.debug("Power  " + characterId.getId() + " is " + powerText);
        return powerText;
    }

}
