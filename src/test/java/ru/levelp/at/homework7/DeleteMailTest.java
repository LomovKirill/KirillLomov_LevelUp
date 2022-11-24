package ru.levelp.at.homework7;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@Feature("Удаление письма")
@Story("Создание и удаление письма")
public class DeleteMailTest extends BaseTest {

    @Test()
    public void creatingAndSendingAndRemovingEmail() {
        final String subject = faker.toString();
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

        mainFramePage.openYourselfMail();

        assertThat(mainFramePage.getSubjectMail(subject)).isTrue();
        assertThat(mainFramePage.getEmail(YOUR_SELF_NAME)).isTrue();
        assertThat(mainFramePage.getBodyMail(body)).isTrue();

        var deleteMail = new DeleteEmailAction(driver);
        deleteMail.markMail();
        deleteMail.deleteMail();

        var openFolderEmail = new OpenFolderEmailAction(driver);
        openFolderEmail.openBasket();

        assertThat(mainFramePage.getSubjectMail(subject)).isTrue();

        var logoutMail = new AccountWindowPage(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
