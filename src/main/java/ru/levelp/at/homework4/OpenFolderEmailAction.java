package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OpenFolderEmailAction extends BasePage<OpenFolderEmailAction> {

    @FindBy(xpath = "//a[@href='/drafts/']")
    private WebElement openDraft;

    @FindBy(xpath = "//a[@href='/sent/']")
    private WebElement openSent;

    @FindBy(xpath = "//a[@href='/trash/']//div[2]")
    private WebElement openBasket;

    @FindBy(xpath = "//a[@data-folder-link-id='1']")
    private WebElement openYourselfFolder;

    public OpenFolderEmailAction(WebDriver driver) {
        super(driver);
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

    public void openYourselfFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(openYourselfFolder)).click();
    }
}
