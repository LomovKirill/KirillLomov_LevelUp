package ru.levelp.at.homework4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.levelp.at.homework4.CreateEmail;
import ru.levelp.at.homework4.DeleteEmail;
import ru.levelp.at.homework4.LoginMail;
import ru.levelp.at.homework4.LogoutMail;
import ru.levelp.at.homework4.OpenFolderEmail;
import ru.levelp.at.homework4.SleepUtils;

public class DeleteMailTest extends BaseTest {

    @Test
    public void creatingAndSendingAndRemovingEmail() {
        final String subject = faker.toString();
        final String body = faker.toString();

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
        createMail.clickSend();
        createMail.clickCloseAfter();

        var openFolderEmail = new OpenFolderEmail(driver);
        openFolderEmail.openYourselfMail();

        SleepUtils.sleep(2000);
        String actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']"
            + "//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase(subject);
        assertThat(driver.getPageSource().contains(EMAIL)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        var deleteMail = new DeleteEmail(driver);
        deleteMail.markMail();
        deleteMail.deleteMail();

        openFolderEmail.openBasket();
        SleepUtils.sleep(2000);
        actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']"
            + "//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase(subject);
        assertThat(driver.getPageSource().contains(EMAIL)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        var logoutMail = new LogoutMail(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
