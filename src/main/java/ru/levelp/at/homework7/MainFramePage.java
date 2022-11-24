package ru.levelp.at.homework7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainFramePage extends BasePage<MainFramePage> {

    @FindBy(xpath = "//*[@class='ph-project-promo-close-icon__container svelte-m7oyyo']")
    private WebElement closePromo;

    @FindBy(xpath = "//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']")
    private WebElement subjectMail;

    @FindBy(xpath = "//div[@class='layout__main-frame']//a[1]//span[@class='ll-crpt']")
    private WebElement emailMail;

    @FindBy(xpath = "//div[@class='layout__main-frame']//a[1]//span[@class='ll-sp__normal']")
    private WebElement bodyMail;

    @FindBy(xpath = "//div[@role='rowgroup']/div[4]")
    private WebElement openYourselfMail;

    @FindBy(xpath = "//div[@role='rowgroup']/a[1]")
    private WebElement openMail;

    @FindBy(xpath = "//div[@class='dataset-letters__empty']//span[@class='octopus__title']")
    private WebElement emptyMail;

    protected MainFramePage(WebDriver driver) {
        super(driver);
    }

    @Step("Закрываем промо акцию")
    public void closePromo() {
        wait.until(ExpectedConditions.elementToBeClickable(closePromo)).click();
    }

    @Step("Проверяем тему письма")
    public boolean getSubjectMail(String subject) {
        return wait.until(ExpectedConditions.textToBePresentInElement(subjectMail, subject));
    }

    @Step("Проверяем отправителя")
    public boolean getEmail(String email) {
        return wait.until(ExpectedConditions.textToBePresentInElement(emailMail, email));
    }

    @Step("Проверяем тело письма")
    public boolean getBodyMail(String body) {
        return wait.until(ExpectedConditions.textToBePresentInElement(bodyMail, body));
    }

    @Step("Открываем папку Входящие")
    public void openYourselfMail() {
        wait.until(ExpectedConditions.elementToBeClickable(openYourselfMail)).click();
    }

    @Step("Открываем письмо")
    public void openMail() {
        wait.until(ExpectedConditions.elementToBeClickable(openMail)).click();
    }

    @Step("Проверяем наличие письм в черновике")
    public boolean getEmptyDraft() {
        return wait.until(ExpectedConditions.textToBePresentInElement(emptyMail, "У вас нет незаконченных"
            + "\n" + "или неотправленных писем"));
    }
}
