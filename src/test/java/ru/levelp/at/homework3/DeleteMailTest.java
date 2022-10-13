package ru.levelp.at.homework3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteMailTest extends BaseTest{

    @Test
    public void creatingAndSendingAndRemovingEmail() {
        String subject = faker.toString();
        String body = faker.toString();

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
        createMail.clickSend();
        createMail.clickCloseAfter();

        var openFolderEmail = new OpenFolderEmail(driver);
        openFolderEmail.openYourselfMail();

        SleepUtils.sleep(2000);
        String actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase(subject);
        assertThat(driver.getPageSource().contains(EMAIL)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        var deleteMail = new DeleteEmail(driver);
        deleteMail.markMail();
        deleteMail.deleteMail();

        SleepUtils.sleep(2000);
        openFolderEmail.openBasket();
        actualSubject = driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']")).getText();
        assertThat(actualSubject).isEqualToIgnoringCase(subject);
        assertThat(driver.getPageSource().contains(EMAIL)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        var logoutMail = new LogoutMail(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
