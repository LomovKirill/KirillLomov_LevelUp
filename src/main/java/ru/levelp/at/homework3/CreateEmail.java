package ru.levelp.at.homework3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateEmail {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "compose-button__txt")
    private WebElement clickWriteEmail;

    @FindBy(xpath = "//div[@class='head_container--3W05z']//input[@class='container--H9L5q size_s--3_M-_']")
    private WebElement fieldTo;

    @FindBy(xpath = "//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']")
    private WebElement fieldSubject;

    @FindBy(xpath = "//div[@role='textbox']/div[1]")
    private WebElement fieldBody;

    @FindBy(xpath = "//button[@data-test-id='send']")
    private WebElement clickSend;

    @FindBy(xpath = "//button[@data-test-id='save']")
    private WebElement clickSave;

    @FindBy(xpath = "//button[@title='Закрыть']")
    private WebElement clickClose;

    @FindBy(xpath = "//span[@title='Закрыть']")
    private WebElement clickCloseAfter;


    public CreateEmail(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        PageFactory.initElements(driver, this);
    }

    public void clickWriteEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(clickWriteEmail)).click();
    }

    public void fieldTo(String value) {
        wait.until(ExpectedConditions.visibilityOf(fieldTo)).sendKeys(value);
    }

    public void fieldSubject(String value) {
        wait.until(ExpectedConditions.visibilityOf(fieldSubject)).sendKeys(value);
    }

    public void fieldBody(String value) {
        wait.until(ExpectedConditions.visibilityOf(fieldBody)).sendKeys(value);
    }

    public void clickSend() {
        wait.until(ExpectedConditions.elementToBeClickable(clickSend)).click();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(clickSave)).click();
    }

    public void clickClose() {
        wait.until(ExpectedConditions.elementToBeClickable(clickClose)).click();
    }

    public void clickCloseAfter() {
        wait.until(ExpectedConditions.elementToBeClickable(clickCloseAfter)).click();
    }
}
