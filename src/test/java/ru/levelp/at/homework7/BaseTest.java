package ru.levelp.at.homework7;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(FailTestExtension.class)
public abstract class BaseTest {

    protected static final String MAIL_RU_URL = "https://mail.ru/";
    protected static final String EMAIL = "klomovtest@mail.ru";
    protected static final String YOUR_SELF_NAME = "Кирилл Ломов";

    protected WebDriver driver;
    protected Faker faker;
    protected GetProperties prop;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        faker = new Faker();
        driver.navigate().to(MAIL_RU_URL);
        driver.manage().window().maximize();
        TestContext.getInstance().addObject("driver", driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
