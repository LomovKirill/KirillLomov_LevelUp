package ru.levelp.at.homework3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenFolderEmail {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(linkText = "Черновики")
    private WebElement openDraft;

    @FindBy(linkText = "Отправленные")
    private WebElement openSent;

    @FindBy(xpath = "//a[@href='/trash/']")
    private WebElement openBasket;

    @FindBy(xpath = "//div[@role='rowgroup']/div[4]")
    private WebElement openYourselfMail;

    @FindBy(xpath = "//a[@data-folder-link-id='1']")
    private WebElement openYourselfFolder;

    public OpenFolderEmail(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        PageFactory.initElements(driver, this);
    }

    public void openDraft() {
        wait.until(ExpectedConditions.elementToBeClickable(openDraft)).click();
    }

    public void openSent() {
        wait.until(ExpectedConditions.elementToBeClickable(openSent)).click();
    }

    public void openBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(openBasket)).click();
    }

    public void openYourselfMail() {
        wait.until(ExpectedConditions.elementToBeClickable(openYourselfMail)).click();
    }

    public void openYourselfFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(openYourselfFolder)).click();
    }
}
