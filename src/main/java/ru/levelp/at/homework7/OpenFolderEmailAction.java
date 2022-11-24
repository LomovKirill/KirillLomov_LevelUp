package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OpenFolderEmailAction extends BasePage<OpenFolderEmailAction> {

    @FindBy(xpath = "//a[@href='/drafts/?']")
    private WebElement openDraft;

    @FindBy(xpath = "//a[@href='/sent/?']")
    private WebElement openSent;

    @FindBy(xpath = "//a[@href='/trash/?']//div[2]")
    private WebElement openBasket;

    @FindBy(xpath = "//a[@data-folder-link-id='1']")
    private WebElement openYourselfFolder;

    protected OpenFolderEmailAction(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие папки \"Черновик\"")
    public void openDraft() {
        wait.until(ExpectedConditions.elementToBeClickable(openDraft)).click();
    }

    @Step("Открытие папки \"Отправленные\"")
    public void openSent() {
        wait.until(ExpectedConditions.elementToBeClickable(openSent)).click();
    }

    @Step("Открытие папки \"Корзина\"")
    public void openBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(openBasket)).click();
    }

    @Step("Открытие созданной папки")
    public void openYourselfFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(openYourselfFolder)).click();
    }
}
