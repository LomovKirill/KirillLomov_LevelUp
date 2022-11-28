package ru.levelp.at.homework7;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Отправка письма")
@Story("Создание и отправка письма, сломанный тест")
public class FailSendMailTest extends BaseTest {

    @Test
    public void creatingAndSendingEmail() {
        String subject = "Тест " + faker.toString();
        final String body = faker.toString();

        var loginMail = new LoginMailPage(driver);
        loginMail.openLogin();
        loginMail.switchFrame();
        loginMail.fillNameField(GetProperties.getProperty("mail.login"));
        loginMail.clickLogin();
        loginMail.fillPasswordField(GetProperties.getProperty("mail.password"));
        loginMail.clickLogin();
        assertThat(loginMail.getTitlePageAfterLogin()).isTrue();

        var mainFramePage = new MainFramePage(driver);
        mainFramePage.closePromo();

        var createMail = new CreateEmailPage(driver);
        createMail.clickWriteEmail();
        createMail.fieldTo(EMAIL);
        createMail.fieldSubject(subject);
        createMail.fieldBody(body);
        createMail.clickSend();
        createMail.clickCloseAfter();

        var openFolderEmail = new OpenFolderEmailAction(driver);
        openFolderEmail.openSent();
        assertThat(mainFramePage.getSubjectMail(subject)).isFalse();

        openFolderEmail.openYourselfFolder();
        assertThat(mainFramePage.getSubjectMail(subject)).isTrue();
        assertThat(mainFramePage.getEmail(YOUR_SELF_NAME)).isTrue();
        assertThat(mainFramePage.getBodyMail(body)).isTrue();

        var logoutMail = new AccountWindowPage(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
