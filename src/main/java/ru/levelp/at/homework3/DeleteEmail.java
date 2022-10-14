package ru.levelp.at.homework3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteEmail {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//div[@role='rowgroup']/a[1]//button")
    private WebElement markMail;

    @FindBy(xpath = "//*[@data-title-shortcut='Del']")
    private WebElement deleteMail;

    public DeleteEmail(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        PageFactory.initElements(driver, this);
    }

    public void markMail() {
        wait.until(ExpectedConditions.elementToBeClickable(markMail)).click();
    }

    public void deleteMail() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteMail)).click();
    }
}
