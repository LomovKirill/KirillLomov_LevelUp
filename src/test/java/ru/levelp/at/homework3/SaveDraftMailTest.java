package ru.levelp.at.homework3;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SaveDraftMailTest {
        protected static final String MAIL_RU_URL = "https://mail.ru/";
        protected static final String LOGIN = "klomovtest";
        protected static final String PASSWORD = "TestPassword123";
        protected static final String EMAIL = "klomovtest@mail.ru";
        protected static final String INBOX = "Входящие";

        protected WebDriver driver;

        @BeforeEach
        public void setUp () {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.navigate().to(MAIL_RU_URL);
            driver.manage().window().maximize();
            SleepUtils.sleep(1500);
        }

        @Test
        public void creatingAndSavingAndSendingEmail () {
            final String subject = "Теса письма";
            final String body = "Тест задания 1";

            driver.findElement(By.xpath("//*[@class=\"ph-login svelte-1hiqrvn\"]")).click();
            WebElement iFrame = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
            driver.switchTo().frame(iFrame);
            SleepUtils.sleep(1000);
            driver.findElement(By.name("username")).sendKeys(LOGIN);
            SleepUtils.sleep(1000);
            driver.findElement(By.className("submit-button-wrap")).click();
            SleepUtils.sleep(1000);
            driver.findElement(By.name("password")).sendKeys(PASSWORD);
            SleepUtils.sleep(1000);
            driver.findElement(By.className("submit-button-wrap")).click();
            SleepUtils.sleep(4000);
            assertThat(driver.getTitle()).contains(INBOX);
            driver.findElement(By.xpath("//*[@class='ph-project-promo-close-icon__container svelte-m7oyyo']")).click();

            driver.findElement(By.className("compose-button__txt")).click();
            SleepUtils.sleep(1000);
            driver.findElement(
                By.xpath("//div[@class='head_container--3W05z']//input[@class='container--H9L5q size_s--3_M-_']"))
                  .sendKeys(EMAIL);
            SleepUtils.sleep(1000);
            driver.findElement(
                By.xpath("//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']"))
                  .sendKeys(subject);
            SleepUtils.sleep(1000);
            driver.findElement(By.xpath("//div[@role='textbox']/div[1]")).sendKeys(body);
            SleepUtils.sleep(1000);
            driver.findElement(By.xpath("//button[@data-test-id='save']")).click();
            SleepUtils.sleep(1000);
            driver.findElement(By.xpath("//button[@title='Закрыть']")).click();
            SleepUtils.sleep(1000);
            driver.findElement(By.xpath("//a[@href='/drafts/']")).click();
            SleepUtils.sleep(1000);

            String actualDraft =
                driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']"))
                      .getText();
            SleepUtils.sleep(1000);
            assertThat(actualDraft).isEqualToIgnoringCase(subject);
            assertThat(driver.getPageSource().contains(subject)).isEqualTo(true);
            assertThat(driver.getPageSource().contains(body)).isEqualTo(true);

            driver.findElement(By.xpath("//div[@role='rowgroup']/a[1]")).click();
            SleepUtils.sleep(1000);
            driver.findElement(By.xpath("//button[@data-test-id='send']")).click();
            SleepUtils.sleep(1000);
            driver.findElement(By.xpath("//span[@title='Закрыть']")).click();
            SleepUtils.sleep(1000);

            actualDraft =
                driver.findElement(By.xpath("//div[@class='dataset-letters__empty']//span[@class='octopus__title']"))
                      .getText();
            SleepUtils.sleep(1000);
            assertThat(actualDraft)
                .isEqualToIgnoringCase("У вас нет незаконченных" + "\n" + "или неотправленных писем");

            driver.findElement(By.xpath("//a[@href='/sent/']")).click();
            SleepUtils.sleep(1000);
            String actualSubject =
                driver.findElement(By.xpath("//div[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']"))
                      .getText();
            assertThat(actualSubject).isEqualToIgnoringCase("Self: " + subject);

            driver.findElement(By.xpath("//*[@data-testid='whiteline-account']")).click();
            SleepUtils.sleep(1000);
            driver.findElement(By.xpath("//*[@data-testid='whiteline-account-exit']")).click();
        }

        @AfterEach
        public void tearDown() {
            driver.quit();
        }
}
