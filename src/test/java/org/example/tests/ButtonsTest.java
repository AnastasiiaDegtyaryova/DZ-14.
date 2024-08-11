package org.example.tests;

import org.example.pages.ButtonsPage;
import org.example.pages.ElementsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ButtonsTest {
    private WebDriver driver;
    private ElementsPage elementsPage;
    private ButtonsPage buttonsPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/elements");

        elementsPage = PageFactory.initElements(driver, ElementsPage.class);
        buttonsPage = PageFactory.initElements(driver, ButtonsPage.class);

        elementsPage.clickOnButtonsSection();
    }

    @Test
    public void testClickMeButton() {
        // Прокрутка до кнопки Click Me і кліку
        WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click Me']"));

        // Використання JavascriptExecutor для прокрутки
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickMeButton);

        clickMeButton.click();

        // Отримання тексту повідомлення після кліку
        String message = buttonsPage.getClickMessageText();
        System.out.println("Message: " + message);
        assertEquals(message, "You have done a dynamic click");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
