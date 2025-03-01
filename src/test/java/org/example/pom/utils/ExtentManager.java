package org.example.pom.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public static ExtentReports getReportInstance() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ExtentReport.html");
        reporter.config().setReportName("Automation Test Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
