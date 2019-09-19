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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginWebTest {
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




    //1) For login - Check if the username that was typed is displayed on the UI as well.
    @Test
    public void getLogin() throws InterruptedException{
        driver.get(base + "/");

        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.click();
        usernameElement.sendKeys("John");

        System.out.println("####-->>"+usernameElement.getAttribute("value"));
        String typedUsername = usernameElement.getAttribute("value");

        WebElement button = driver.findElement(By.id("button"));
        button.click();


        Thread.sleep(1000 );


        String originalHandle = driver.getWindowHandle();
        for(String handle1 : driver.getWindowHandles()){
            driver.switchTo().window(handle1);
            Thread.sleep(1000);

            WebElement displayedUsernameElement = driver.findElement(By.id("username"));
            String displayedName = displayedUsernameElement.getText();
            System.out.println("******-->>"+displayedUsernameElement.getAttribute("value"));
            Assert.assertEquals(typedUsername, displayedName);

        }


    }


    // 3) For message - Check if the message is displayed on the chat.
//    @Test
//    public void messageTest(){
//
//
//    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
