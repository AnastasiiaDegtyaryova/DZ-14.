package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement clickMeButton;

    @FindBy(id = "dynamicClickMessage")
    private WebElement clickMessage;

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickClickMeButton() {
        clickMeButton.click();
    }

    public String getClickMessageText() {
        return clickMessage.getText();
    }
}
