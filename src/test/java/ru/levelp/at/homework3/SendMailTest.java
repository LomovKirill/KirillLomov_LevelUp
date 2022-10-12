package ru.levelp.at.homework3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

public class SendMailTest extends BaseTest {

    String subject = "Два дня тебя искал";
    String body = "А дело было в wait, обидно";

    @Test
    public void creatingAndSavingAndSendingEmail() {

        var loginMail = new LoginMail(driver);
        loginMail.openLogin();
        loginMail.switchFrame();
        loginMail.nameField(login);
        loginMail.clickLogin();
        loginMail.passwordField(password);
        loginMail.clickLogin();

        SleepUtils.sleep(4000);
        assertThat(driver.getTitle()).contains("Входящие");
        driver.findElement(By.xpath("//*[@data-click-counter=\"116987833\"]")).click();

        var createMail = new CreateEmail(driver);
        createMail.clickWriteEmail();
        createMail.fieldTo(email);
        createMail.fieldSubject(subject);
        createMail.fieldBody(body);
        createMail.clickSave();
        createMail.clickClose();

        driver.findElement(By.linkText("Черновики")).click();
        assertThat(driver.getPageSource().contains(email)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(subject)).isEqualTo(true);
        assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

        driver.findElement(By.xpath("//div[@role='rowgroup']/a[1]")).click();
        createMail.clickSend();
        SleepUtils.sleep(2000);
        createMail.clickCloseAfter();

        driver.findElement(By.linkText("Отправленные")).click();

        var logoutMail = new LogoutMail(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
