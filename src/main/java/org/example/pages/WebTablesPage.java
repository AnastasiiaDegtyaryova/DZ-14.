package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebTablesPage {
    private WebDriver driver;

    @FindBy(id = "addNewRecordButton")
    private WebElement addButton;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "age")
    private WebElement ageInput;

    @FindBy(id = "salary")
    private WebElement salaryInput;

    @FindBy(id = "department")
    private WebElement departmentInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    // Перевірка останнього доданого запису
    @FindBy(xpath = "//div[@class='rt-tr-group'][4]//div[@class='rt-td'][1]")
    private WebElement addedFirstName;

    @FindBy(xpath = "//div[@class='rt-tr-group'][4]//span[@title='Edit']")
    private WebElement editButton;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void fillForm(String firstName, String lastName, String email, int age, int salary, String department) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        ageInput.sendKeys(String.valueOf(age));
        salaryInput.sendKeys(String.valueOf(salary));
        departmentInput.sendKeys(department);
    }

    public void submitForm() {
        submitButton.click();
    }

    public String getAddedFirstName() {
        return addedFirstName.getText().trim();
    }

    public WebElement getAddedFirstNameElement() {
        return addedFirstName;
    }

    public void clickEditButton() {
        editButton.click();
    }

    public void editFirstName(String newFirstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(newFirstName);
        submitButton.click();
    }
}
