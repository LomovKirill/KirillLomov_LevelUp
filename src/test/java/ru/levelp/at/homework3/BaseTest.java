package ru.levelp.at.homework3;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {

    protected static final String MAIL_RU_URL = "https://mail.ru/";
    protected static final String LOGIN = "klomovtest";
    protected static final String PASSWORD = "TestPassword123";
    protected static final String EMAIL = "klomovtest@mail.ru";
    protected static final String INBOX = "Входящие";

    protected WebDriver driver;
    protected Faker faker;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        faker = new Faker();
        driver.navigate().to(MAIL_RU_URL);
        driver.manage().window().maximize();
        SleepUtils.sleep(1500);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
