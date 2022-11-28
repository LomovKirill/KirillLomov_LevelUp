package ru.levelp.at.homework7;

import io.qameta.allure.Step;
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

    @Step("Открываем окно логина")
    public void openLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(openLogin)).click();
    }

    @Step("Нажимаем выход")
    public void exit() {
        wait.until(ExpectedConditions.elementToBeClickable(exit)).click();
    }
}
