package ru.levelp.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SaveDraftMailTest {
    protected static final String MAIL_RU_URL = "https://mail.ru/";

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(MAIL_RU_URL);
        driver.manage().window().maximize();
        SleepUtils.sleep(1500);
    }

    @Test
    public void creatingAndSavingAndSendingEmail() {
        driver.findElement(By.xpath("//*[@class=\"ph-login svelte-1hiqrvn\"]")).click();
        WebElement iFrame = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        driver.switchTo().frame(iFrame);
        SleepUtils.sleep(1000);
        driver.findElement(By.name("username")).sendKeys("klomovtest");
        SleepUtils.sleep(1000);
        driver.findElement(By.className("submit-button-wrap")).click();
        SleepUtils.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("TestPassword123");
        SleepUtils.sleep(1000);
        driver.findElement(By.className("submit-button-wrap")).click();
        SleepUtils.sleep(4000);
        assertThat(driver.getTitle()).containsAnyOf("Входящие");
        driver.findElement(By.xpath("//*[@class='ph-project-promo-close-icon__container svelte-m7oyyo']")).click();

        driver.findElement(By.className("compose-button__txt")).click();
        SleepUtils.sleep(1000);
        driver.findElement(
            By.xpath("//div[@class='head_container--3W05z']//input[@class='container--H9L5q size_s--3_M-_']"))
              .sendKeys("klomovtest@mail.ru");
        SleepUtils.sleep(1000);
        driver.findElement(
            By.xpath("//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']"))
              .sendKeys("Тема письма");
        SleepUtils.sleep(1000);
        driver.findElement(By.xpath("//div[@role='textbox']/div[1]")).sendKeys("Тест задания 1");
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
        assertThat(actualDraft).isEqualToIgnoringCase("Тема письма");
        assertThat(driver.getPageSource().contains("Тема письма")).isEqualTo(true);
        assertThat(driver.getPageSource().contains("Тест задания 1")).isEqualTo(true);

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
        assertThat(actualSubject).isEqualToIgnoringCase("Self: " + "Тема письма");

        driver.findElement(By.xpath("//*[@data-testid='whiteline-account']")).click();
        SleepUtils.sleep(1000);
        driver.findElement(By.xpath("//*[@data-testid='whiteline-account-exit']")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
