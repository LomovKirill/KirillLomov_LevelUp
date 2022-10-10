package ru.levelp.at.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class SendMailTest {
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
    public void creatingAndSavingAndSendingLetter() {
        driver.findElement(By.xpath("//*[@class=\"ph-login svelte-1hiqrvn\"]")).click();
        SleepUtils.sleep(1000);
        WebElement iFrame = driver.findElement(By.cssSelector("iframe.ag-popup__frame__layout__iframe"));
        SleepUtils.sleep(1500);
        driver.switchTo().frame(iFrame);
        SleepUtils.sleep(1500);
        driver.findElement(By.xpath("//*[@placeholder=\"Имя аккаунта\"]")).sendKeys("klomovtest");
        driver.findElement(By.xpath("//*[@class=\"submit-button-wrap\"]")).click();
        SleepUtils.sleep(100);
        driver.findElement(By.xpath("//*[@placeholder=\"Пароль\"]")).sendKeys("TestPassword123");
        driver.findElement(By.xpath("//*[@class=\"submit-button-wrap\"]")).click();
        SleepUtils.sleep(3500);
        assertThat(driver.getTitle()).contains("Входящие");
        driver.findElement(By.xpath("//*[@data-click-counter=\"116987833\"]")).click();
        driver.findElement(By.xpath("//*[@href=\"/compose/\"]")).click();
        driver.findElement(By.xpath("//*[@class=\"container--H9L5q size_s--3_M-_\"]")).sendKeys("klomovtest@mail.ru");
        SleepUtils.sleep(1500);
    }
}
