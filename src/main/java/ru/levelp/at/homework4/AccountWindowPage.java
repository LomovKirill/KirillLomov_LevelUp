package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountWindowPage extends BasePage<AccountWindowPage> {

    @FindBy(xpath = "//*[@data-testid='whiteline-account']")
    private WebElement openLogin;

    @FindBy(xpath = "//*[@data-testid='whiteline-account-exit']")
    private WebElement exit;

    protected AccountWindowPage(WebDriver driver) {
        super(driver);
    }

    public void openLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(openLogin)).click();
    }

    public void exit() {
        wait.until(ExpectedConditions.elementToBeClickable(exit)).click();
    }
}
