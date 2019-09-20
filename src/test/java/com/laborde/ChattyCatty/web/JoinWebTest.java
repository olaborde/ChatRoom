package com.laborde.ChattyCatty.web;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JoinWebTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String base;

    @Before
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\OML00\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        this.base = "http://localhost:" + port;
    }

    //2) For Join - Make sure to also test that the online count also increases by 1, if 2 users join one after the other.
    @Test
    public void joinTest() throws InterruptedException, Exception {

        driver.get(base + "/");
        String firstWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");


        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(tabs.size());
        driver.switchTo().window(tabs.get(0));


        // Perform task on second window

        // first user login in in new window
        WebElement usernameElement2 = driver.findElement(By.id("username"));
        usernameElement2.click();
        usernameElement2.sendKeys("User1");

        WebElement button2 = driver.findElement(By.id("button"));
        button2.click();
        Thread.sleep(2000);
        WebElement numOfUser2 = driver.findElement(By.id("numOfUser"));
        int secondNumOfUser = Integer.parseInt(numOfUser2.getText());
        //Assert.assertEquals(1, secondNumOfUser);

        // Second user login then the online count increases to 2 which is tested below, then user leave and onlineCount decreases to 1
        Thread.sleep(1000);
        String originalHandle = driver.getWindowHandle();
        for(String handle1 : driver.getWindowHandles()){
            driver.switchTo().window(handle1);
            driver = new ChromeDriver();
            Thread.sleep(1000);

            driver.get(base + "/");

            WebElement usernameElement = driver.findElement(By.id("username"));
            usernameElement.click();
            usernameElement.sendKeys("User2");

            WebElement button = driver.findElement(By.id("button"));
            button.click();
            Thread.sleep(1000);
            WebElement numOfUser = driver.findElement(By.id("numOfUser"));

            int firstNumOfUser = Integer.parseInt(numOfUser.getText());
            System.out.println(firstNumOfUser);
            Assert.assertEquals(2, firstNumOfUser);
            Thread.sleep(1000);



        }






    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
