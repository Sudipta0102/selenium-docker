package com.pseudofunc.listener;

import com.pseudofunc.util.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

    /*
    This method attempts to take screenshot and attach the same in TestNG
    report.
    First roadblock to do that is to get hold of the WebDriver information
    under which the test has failed. To solve this, in the abstracttest class,
    under setupdriver method the driver is being initialized for every test.
    There we can get hold of the driver information for the test because every thread
    will be creating its own driver and closing the same upon the completion.

    So to achieve that, ITestContext is passed as argument in the setupdriver method
    that will let us create a key value pair of driver information.

    Also, remember the Listener tag in the abstracttest class.

     */
    @Override
    public void onTestFailure(ITestResult result) {
        // result.getTestContext().getAttribute(Constants.DRIVER);
        // Object obj = result.getTestContext().getAttribute(Constants.DRIVER);
        TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
        String screenShot = driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
        String htmlImage = String.format(htmlImageFormat, screenShot);

        Reporter.log(htmlImage);
    }
}
