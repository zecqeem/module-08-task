package org.example.module08_1.util;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class ScreenshotHook implements IHookable {

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            ScreenshotReporter.attachScreenshots(testResult.getName());
        }
    }
}
