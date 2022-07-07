package dungcc.base;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Screenshot {
    WebDriver driver;

    @Test
    public void test () throws InterruptedException, IOException
    {
        //Capture entire page screenshot and then store it to destination drive
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("D:\\screenshot.jpg"));
        System.out.print("Screenshot is captured and stored in your D: Drive");
    }
}
