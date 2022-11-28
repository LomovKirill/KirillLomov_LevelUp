package ru.levelp.at.homework7;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FailTestExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        var driver = (WebDriver) TestContext.getInstance().getObject("driver");
        screenshotFail(driver);
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = "png")
    private byte[] screenshotFail(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
