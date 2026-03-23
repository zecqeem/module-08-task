package org.example.module08_1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);
    private static final String FOLDER_PATH = Configuration.getInstance().get("screenshot.path");
    public static String takeScreenshot(String testName){
        File tempFile = getScreenshot();
        Path targetPath = getTargetPath(testName);
        return saveScreenshot(tempFile,targetPath);
    }
    private static File getScreenshot(){
        log.debug("Taking Screenshot for the repository");
        WebDriver driver = DriverManager.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
    private static Path getTargetPath(String testName){
        String fileName = testName + "_" + getTimestamp() + ".png";
        return Paths.get(FOLDER_PATH + fileName);
    }
    private static String getTimestamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return LocalDateTime.now().format(formatter);
    }
    private static String saveScreenshot(File tempFile,Path targetPath){
        log.debug("Saving Screenshot for the repository");
        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(tempFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
