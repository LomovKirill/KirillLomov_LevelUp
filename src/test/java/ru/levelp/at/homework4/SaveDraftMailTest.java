package ru.levelp.at.homework4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.levelp.at.homework4.CreateEmail;
import ru.levelp.at.homework4.LoginMail;
import ru.levelp.at.homework4.LogoutMail;
import ru.levelp.at.homework4.OpenFolderEmail;
import ru.levelp.at.homework4.SleepUtils;

public class SaveDraftMailTest extends BaseTest {

    @Test()
    public void creatingAndSavingAndSendingEmail() {
        final String subject = faker.toString();
        final String body = faker.book().toString();

        var loginMail = new LoginMail(driver);
        loginMail.openLogin();
        loginMail.switchFrame();
        loginMail.fillNameField(GetProperties.getProperty("mail.login"));
        loginMail.clickLogin();
        loginMail.fillPasswordField(GetProperties.getProperty("mail.password"));
        loginMail.clickLogin();
        SleepUtils.sleep(5000);
        assertThat(driver.getTitle()).contains(INBOX);

        driver.findElement(By.xpath("//*[@class='ph-project-promo-close-icon__container svelte-m7oyyo']")).click();

        var createMail = new CreateEmail(driver);
        createMail.clickWriteEmail();
        createMail.fieldTo(EMAIL);
        createMail.fieldSubject(subject);
        createMail.fieldBody(body);
        createMail.clickSave();
        createMail.clickClose();
        SleepUtils.sleep(2000);
        var openFolderEmail = new OpenFolderEmail(driver);
        openFolderEmail.openDraft();
        SleepUtils.sleep(2000);
        String actualDraft = driver.findElement(By.xpath("//div[@class='layout__main-frame']"
            + "//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualDraft).isEqualToIgnoringCase(subject);
        assertThat(driver.getPageSource().contains(subject)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        driver.findElement(By.xpath("//div[@role='rowgroup']/a[1]")).click();
        createMail.clickSend();
        createMail.clickCloseAfter();

        SleepUtils.sleep(2000);
        actualDraft = driver.findElement(By.xpath("//div[@class='dataset-letters__empty']"
            + "//span[@class='octopus__title']")).getText();
        assertThat(actualDraft).isEqualToIgnoringCase("У вас нет незаконченных" + "\n" + "или неотправленных писем");

        openFolderEmail.openSent();
        SleepUtils.sleep(2000);
        String actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']"
            + "//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase("Self: " + subject);

        var logoutMail = new LogoutMail(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
