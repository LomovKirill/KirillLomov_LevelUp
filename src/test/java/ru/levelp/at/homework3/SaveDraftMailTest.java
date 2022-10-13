package ru.levelp.at.homework3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.assertj.core.api.Assertions.assertThat;

public class SaveDraftMailTest extends BaseTest {

    @Test
    public void creatingAndSavingAndSendingEmail() {
        String subject = "Два дня тебя искал";
        String body = "А дело было в wait, обидно";

        var loginMail = new LoginMail(driver);
        loginMail.openLogin();
        loginMail.switchFrame();
        loginMail.fillNameField(LOGIN);
        loginMail.clickLogin();
        loginMail.fillPasswordField(PASSWORD);
        loginMail.clickLogin();

        SleepUtils.sleep(4000);
        assertThat(driver.getTitle()).contains("Входящие");
        driver.findElement(By.xpath("//*[@data-click-counter='116987833']")).click();

        var createMail = new CreateEmail(driver);
        createMail.clickWriteEmail();
        createMail.fieldTo(EMAIL);
        createMail.fieldSubject(subject);
        createMail.fieldBody(body);
        createMail.clickSave();
        createMail.clickClose();

        var openFolderEmail = new OpenFolderEmail(driver);
        openFolderEmail.openDraft();
        SleepUtils.sleep(2000);
        String actualDraft = driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualDraft).isEqualToIgnoringCase(subject);
        assertThat(driver.getPageSource().contains(EMAIL)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        driver.findElement(By.xpath("//div[@role='rowgroup']/a[1]")).click();
        createMail.clickSend();
        SleepUtils.sleep(2000);
        createMail.clickCloseAfter();

        SleepUtils.sleep(2000);
        actualDraft = driver.findElement(By.xpath("//div[@class='dataset-letters__empty']//span[@class='octopus__title']")).getText();
        assertThat(actualDraft).isEqualToIgnoringCase("У вас нет незаконченных" + "\n" + "или неотправленных писем");

        openFolderEmail.openSent();
        SleepUtils.sleep(2000);
        String actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase("Self: " + subject);

        var logoutMail = new LogoutMail(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
