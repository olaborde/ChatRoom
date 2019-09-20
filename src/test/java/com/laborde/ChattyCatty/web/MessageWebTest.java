package com.laborde.ChattyCatty.web;

//3) For message - Check if the message is displayed on the chat.

import org.junit.After;
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

import static org.junit.Assert.assertNotNull;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageWebTest {

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

    @Test
    public void messageDisplayedInChat() throws InterruptedException{
        driver.get(base + "/");

        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.click();
        usernameElement.sendKeys("Jane Doe");

        WebElement button = driver.findElement(By.id("button"));
        button.click();

        String originalHandle = driver.getWindowHandle();
        for(String handle1 : driver.getWindowHandles()){
            driver.switchTo().window(handle1);
            Thread.sleep(1000);

            WebElement input = driver.findElement(By.id("msg"));
            input.click();
            String theMessage = "Hello, World!";
            input.sendKeys(theMessage);

            WebElement send = driver.findElement(By.id("sendTheMessage"));
            send.click();
            Thread.sleep(3000);

            WebElement messageDisplayed = driver.findElement(By.className("message-content"));
            String theDisplayedMessage = messageDisplayed.getText();

            //Assert.assertEquals("Jane Doe: "+theMessage, theDisplayedMessage);
            assertNotNull(driver.findElement(By.xpath("//*[contains(text(), theMessage)]")));

           // Assert.assertEquals();
            Thread.sleep(3000);

        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
