package ru.levelp.at.homework7;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@Feature("Сохранение письма в черновик")
@Story("Создание письма, сохранение его в черновик с последующей отправкой")
public class SaveDraftMailTest extends BaseTest {

    @Test()
    public void creatingAndSavingAndSendingEmail() {
        final String subject = faker.toString();
        final String body = faker.book().toString();

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
        createMail.clickSave();
        createMail.clickClose();

        var openFolderEmail = new OpenFolderEmailAction(driver);
        openFolderEmail.openDraft();
        assertThat(mainFramePage.getSubjectMail(subject)).isTrue();
        assertThat(mainFramePage.getEmail(EMAIL)).isTrue();
        assertThat(mainFramePage.getBodyMail(body)).isTrue();

        mainFramePage.openMail();
        createMail.clickSend();
        createMail.clickCloseAfter();
        assertThat(mainFramePage.getEmptyDraft()).isTrue();

        openFolderEmail.openSent();
        assertThat(mainFramePage.getSubjectMail(subject)).isTrue();

        var logoutMail = new AccountWindowPage(driver);
        logoutMail.openLogin();
        logoutMail.exit();
    }
}
