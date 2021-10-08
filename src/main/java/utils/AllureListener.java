package utils;



import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit5.AllureJunit5;
import managers.DriverManager;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.lang.reflect.Method;
import java.util.Map;


public class AllureListener extends AllureJunit5 implements TestWatcher {

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] saveScreenShootPNG() {
        return ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        saveScreenShootPNG();
    }
}
