package ru.levelp.at.homework3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class SendMailTest extends BaseTest {

    @Test
    public void creatingAndSendingEmail() {
        String subject = "Тест как он есть";
        String body = faker.toString();

        var loginMail = new LoginMail(driver);
        loginMail.openLogin();
        loginMail.switchFrame();
        loginMail.fillNameField(LOGIN);
        loginMail.clickLogin();
        loginMail.fillPasswordField(PASSWORD);
        loginMail.clickLogin();

        SleepUtils.sleep(5000);
        assertThat(driver.getTitle()).contains(INBOX);

        var createMail = new CreateEmail(driver);
        createMail.clickWriteEmail();
        createMail.fieldTo(EMAIL);
        createMail.fieldSubject(subject);
        createMail.fieldBody(body);
        createMail.clickSend();
        createMail.clickCloseAfter();

        var openFolderEmail = new OpenFolderEmail(driver);
        openFolderEmail.openSent();
        SleepUtils.sleep(2000);
        String actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase("Self: " + subject);

        openFolderEmail.openYourselfFolder();
        SleepUtils.sleep(2000);
        actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase(subject);
        assertThat(driver.getPageSource().contains(EMAIL)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        var logoutMail = new LogoutMail(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
