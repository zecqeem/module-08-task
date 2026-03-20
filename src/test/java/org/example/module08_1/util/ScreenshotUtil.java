package org.example.module08_1.util;

import org.example.module08_1.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    public static String takeScreenshot(String testName){
        WebDriver driver = DriverManager.getDriver();
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String folderPath = "src/test/resources/screenshots/";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = testName + "_" + timestamp + ".png";
        Path targetPath = Paths.get(folderPath + fileName);
        try {
            Files.createDirectories(targetPath.getParent());

            Files.copy(tempFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
