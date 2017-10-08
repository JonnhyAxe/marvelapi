/*
 * Copyright 2017 by Brisa Inovação e Tecnologia S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Brisa, SA ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Brisa.
 */
package com.marvelapi.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * <class description>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MarvelWebClient {

    @Test()
    public final void whenHttpsUrlIsConsumed_thenException() throws FailingHttpStatusCodeException, MalformedURLException, IOException {

        String power = null;
        final String urlOverHttps = "http://marvel.com/universe/Parker%2C_Ben_%28Uncle_Ben%29?utm_campaign=apiRef&utm_source=746d7e48cbc8d37cf4dd63c53296c3c1";
        final ResponseEntity<String> response = new RestTemplate().exchange(urlOverHttps, HttpMethod.GET, null, String.class);
        assertThat(response.getStatusCode().value(), equalTo(200));

        WebClient webClient = new WebClient();
        HtmlPage myPage = (HtmlPage) webClient.getPage(urlOverHttps);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.waitForBackgroundJavaScript(30 * 1000); /*
                                                           * will wait
                                                           * JavaScript to
                                                           * execute up to 30s
                                                           */

        DomElement powerDom = myPage.getHtmlPageOrNull().getElementById("char-powers-contect");

        // strip out your required data with a regex
        Pattern pattern = Pattern.compile(".*<div id=\"char-powers-contect\">(.*?)</div>.*");
        Matcher matcher = pattern.matcher(response.getBody());
        System.out.println(response.getBody());

        if (matcher.find()) {
            power = matcher.group(1);
        }

        assertThat(power, notNullValue());

    }

}
