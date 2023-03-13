package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;


public class ReportAPI {

    ExtentReports extent = new ExtentReports();
    ExtentTest test;

    public void createReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Report/API.html");
        spark.config().setDocumentTitle("Report");
        spark.config().setReportName("Report");
        extent.attachReporter(spark);
    }

    public void createTest(String info) {
        test = extent.createTest(info, info);
        test.log(Status.PASS, "Test Passed!");

    }

    public void endExecution() {
        extent.flush();
    }


    // reference image saved to disk
}
