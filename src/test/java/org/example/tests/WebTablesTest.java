package org.example.tests;

import org.example.pages.WebTablesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class WebTablesTest {
    private WebDriver driver;
    private WebTablesPage webTablesPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");

        webTablesPage = new WebTablesPage(driver);
    }

    @Test
    public void testAddAndEditRecord() {
        // Приховати рекламу
        try {
            WebElement adFrame = driver.findElement(By.xpath("//div[@id='google_ads_iframe_/21849154601,22343295815/Ad.Plus-Anchor_0__container__']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", adFrame);
        } catch (Exception e) {
            // Реклама не знайдена або її закрити неможливо, продовжуємо тест
        }

        // Натиснути кнопку ADD
        webTablesPage.clickAddButton();

        // Заповнити форму
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@mailinator.com";
        int age = 20;
        int salary = 1000;
        String department = "Department A";

        webTablesPage.fillForm(firstName, lastName, email, age, salary, department);
        webTablesPage.submitForm();

        // Очікування на видимість останнього доданого запису
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addedFirstNameElement = webTablesPage.getAddedFirstNameElement();
        wait.until(ExpectedConditions.visibilityOf(addedFirstNameElement));

        // Перевірка, що запис додано
        String addedFirstName = webTablesPage.getAddedFirstName();
        assertEquals(addedFirstName, firstName, "First name should match the added record");

        // Прокрутка до кнопки "Edit" і клік на неї
        WebElement editButton = driver.findElement(By.xpath("//span[@id='edit-record-4']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
        editButton.click();

        // Редагування запису
        String newFirstName = "Jane";
        webTablesPage.editFirstName(newFirstName);

        // Перевірка, що запис був оновлений
        String editedFirstName = webTablesPage.getAddedFirstName();
        assertEquals(editedFirstName, newFirstName, "First name should match the edited record");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

