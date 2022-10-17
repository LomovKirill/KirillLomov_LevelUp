package ru.levelp.at.homework4;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginMail {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@class=\"ph-login svelte-1hiqrvn\"]")
    private WebElement openLogin;

    @FindBy(name = "username")
    private WebElement fillNameField;

    @FindBy(name = "password")
    private WebElement fillPasswordField;

    @FindBy(className = "submit-button-wrap")
    private WebElement clickButton;

    public LoginMail(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        PageFactory.initElements(driver, this);
    }

    public void openLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(openLogin)).click();
    }

    public void fillNameField(String value) {
        wait.until(ExpectedConditions.visibilityOf(fillNameField)).sendKeys(value);
    }

    public void fillPasswordField(String value) {
        wait.until(ExpectedConditions.visibilityOf(fillPasswordField)).sendKeys(value);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(clickButton)).click();
    }

    public void switchFrame() {
        WebElement iframe = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        SleepUtils.sleep(1500);
        driver.switchTo().frame(iframe);
    }
}
