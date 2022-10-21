package ru.levelp.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SaveDraftMailTest {
    protected static final String MAIL_RU_URL = "https://mail.ru/";

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        driver.navigate().to(MAIL_RU_URL);
        driver.manage().window().maximize();
    }

    @Test
    public void creatingAndSavingAndSendingEmail() {
        driver.findElement(By.xpath("//*[@class=\"ph-login svelte-1hiqrvn\"]")).click();
        WebElement iframe = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.name("username")).sendKeys("klomovtest");
        driver.findElement(By.className("submit-button-wrap")).click();
        driver.findElement(By.name("password")).sendKeys("TestPassword123");
        driver.findElement(By.className("submit-button-wrap")).click();

        var implicitWaitDuration = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            assertThat(wait.until(titleContains("Входящие"))).isTrue();
        } finally {
            driver.manage().timeouts().implicitlyWait(implicitWaitDuration);
        }

        driver.findElement(By.xpath("//*[@class='ph-project-promo-close-icon__container svelte-m7oyyo']")).click();
        driver.findElement(By.xpath("//a[@href='/drafts/']")).click();
        driver.findElement(By.className("compose-button__txt")).click();
        driver.findElement(
            By.xpath("//div[@class='head_container--3W05z']//input[@class='container--H9L5q size_s--3_M-_']"))
              .sendKeys("klomovtest@mail.ru");
        driver.findElement(
            By.xpath("//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']"))
              .sendKeys("Тема письма 1");
        driver.findElement(By.xpath("//div[@role='textbox']/div[1]")).sendKeys("Тест задания 1");
        driver.findElement(By.xpath("//button[@data-test-id='save']")).click();
        driver.findElement(By.xpath("//button[@title='Закрыть']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            String actualDraft = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div"
                + "[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']"))).getText();
            assertThat(actualDraft).contains("Тема письма 1");
        } finally {
            driver.manage().timeouts().implicitlyWait(implicitWaitDuration);
        }

        assertThat(driver.getPageSource().contains("klomovtest@mail.ru")).isEqualTo(true);
        assertThat(driver.getPageSource().contains("Тест задания 1")).isEqualTo(true);

        driver.findElement(By.xpath("//div[@role='rowgroup']/a[1]")).click();
        driver.findElement(By.xpath("//button[@data-test-id='send']")).click();
        driver.findElement(By.xpath("//span[@title='Закрыть']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            String actualDraft = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div"
                + "[@class='dataset-letters__empty']//span[@class='octopus__title']"))).getText();
            assertThat(actualDraft).isEqualToIgnoringCase("У вас нет незаконченных"
                + "\n" + "или неотправленных писем");
        } finally {
            driver.manage().timeouts().implicitlyWait(implicitWaitDuration);
        }

        driver.findElement(By.xpath("//a[@href='/sent/']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            String actualDraft = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div"
                + "[@class='layout__main-frame']//a[1]//span[@class='ll-sj__normal']"))).getText();
            assertThat(actualDraft).isEqualToIgnoringCase("Self: " + "Тема письма 1");
        } finally {
            driver.manage().timeouts().implicitlyWait(implicitWaitDuration);
        }

        driver.findElement(By.xpath("//*[@data-testid='whiteline-account']")).click();
        driver.findElement(By.xpath("//*[@data-testid='whiteline-account-exit']")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
