package com.laborde.ChattyCatty.webTest;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void joinTest() throws InterruptedException {
        driver.get(base + "/");

        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.click();
        usernameElement.sendKeys("User1");

        WebElement button = driver.findElement(By.id("button"));
        button.click();
        WebElement numOfUser = driver.findElement(By.id("numOfUser"));

        int firstNumOfUser = Integer.parseInt(numOfUser.getText());

        Assert.assertEquals(1, firstNumOfUser);



        String originalHandle = driver.getWindowHandle();
        for(String handle1 : driver.getWindowHandles()){
            driver.switchTo().window(handle1);
            Thread.sleep(1000);

            // Second user login in in different browser
            driver.get(base + "/");
            WebElement usernameElement2 = driver.findElement(By.id("username"));
            usernameElement2.click();
            usernameElement2.sendKeys("User2");

            WebElement button2 = driver.findElement(By.id("button"));
            button2.click();
            WebElement numOfUser2 = driver.findElement(By.id("numOfUser"));
            int secondNumOfUser = Integer.parseInt(numOfUser2.getText());
            Assert.assertEquals(2, secondNumOfUser);
            Thread.sleep(1000);

        }

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
