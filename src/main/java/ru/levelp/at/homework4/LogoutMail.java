package ru.levelp.at.homework4;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutMail {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@data-testid='whiteline-account']")
    private WebElement openLogin;

    @FindBy(xpath = "//*[@data-testid='whiteline-account-exit']")
    private WebElement exit;

    public LogoutMail(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        PageFactory.initElements(driver, this);
    }

    public void openLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(openLogin)).click();
    }

    public void exit() {
        wait.until(ExpectedConditions.elementToBeClickable(exit)).click();
    }
}
