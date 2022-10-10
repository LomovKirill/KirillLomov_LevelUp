package ru.levelp.at.homework3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final String MAIL_URL = "https://mail.ru/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@class=\"ph-login svelte-1hiqrvn\"]")
    private WebElement open;

    @FindBy(xpath = "//*[@placeholder=\"Имя аккаунта\"]")
    private WebElement nameTextField;

    @FindBy(xpath = "//*[@placeholder=\"Пароль\"]")
    private WebElement passwordTextField;

    @FindBy(xpath = "//*[@class=\"submit-button-wrap\"]")
    private WebElement clickButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(MAIL_URL);
    }

    public void openLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(open)).click();
    }

    public void nameTextField(String value) {
        wait.until(ExpectedConditions.visibilityOf(nameTextField)).sendKeys(value);
    }

    public void passwordTextField(String value) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField)).sendKeys(value);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(clickButton)).click();
    }

    public void switchFrame() {
        WebElement iFrame = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        SleepUtils.sleep(1500);
        driver.switchTo().frame(iFrame);
    }

}
