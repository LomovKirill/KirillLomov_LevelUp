package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeleteEmailAction extends BasePage<DeleteEmailAction> {

    @FindBy(xpath = "//div[@role='rowgroup']/a[1]//button")
    private WebElement markMail;

    @FindBy(xpath = "//*[@data-title-shortcut='Del']")
    private WebElement deleteMail;

    protected DeleteEmailAction(WebDriver driver) {
        super(driver);
    }

    public void markMail() {
        wait.until(ExpectedConditions.elementToBeClickable(markMail)).click();
    }

    public void deleteMail() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteMail)).click();
    }
}
