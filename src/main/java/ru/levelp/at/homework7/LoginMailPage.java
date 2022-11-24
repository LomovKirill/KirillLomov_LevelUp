package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginMailPage extends BasePage<LoginMailPage> {

    @FindBy(xpath = "//*[@class=\"ph-login svelte-1hiqrvn\"]")
    private WebElement openLogin;

    @FindBy(name = "username")
    private WebElement fillNameField;

    @FindBy(name = "password")
    private WebElement fillPasswordField;

    @FindBy(className = "submit-button-wrap")
    private WebElement clickButton;

    protected LoginMailPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем карточку логина")
    public void openLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(openLogin)).click();
    }

    @Step("Заполняем поле Имя аккаунта")
    public void fillNameField(String value) {
        wait.until(ExpectedConditions.visibilityOf(fillNameField)).sendKeys(value);
    }

    @Step("Заполняем пароль")
    public void fillPasswordField(String value) {
        wait.until(ExpectedConditions.visibilityOf(fillPasswordField)).sendKeys(value);
    }

    @Step("Нажимаем войти")
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(clickButton)).click();
    }

    @Step("Переключаем на окно логина")
    public void switchFrame() {
        WebElement iframe = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        driver.switchTo().frame(iframe);
    }

    @Step("Проверяем успешный вход на почту")
    public Boolean getTitlePageAfterLogin() {
        return wait.until(ExpectedConditions.titleContains("Входящие"));
    }
}
