package com.HID.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener implements ConcurrentEventListener {
    private ExtentReports extent;
    private Map<String, ExtentTest> featureMap = new HashMap<>();
    private ThreadLocal<ExtentTest> scenario = new ThreadLocal<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::testRunStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::testRunFinished);
        publisher.registerHandlerFor(TestCaseStarted.class, this::testCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::testCaseFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::testStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::testStepFinished);
    }

    private void testRunStarted(TestRunStarted event) {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    private void testRunFinished(TestRunFinished event) {
        extent.flush();
    }

    private void testCaseStarted(TestCaseStarted event) {
        ExtentTest feature = extent.createTest(event.getTestCase().getName());
        featureMap.put(event.getTestCase().getId().toString(), feature);
    }

    private void testCaseFinished(TestCaseFinished event) {
        extent.flush();
    }

    private void testStepStarted(TestStepStarted event) {
        String stepName = event.getTestStep().getCodeLocation();
        ExtentTest scenarioTest = featureMap.get(event.getTestCase().getId().toString());
        ExtentTest step = scenarioTest.createNode(stepName);
        scenario.set(step);
    }

    private void testStepFinished(TestStepFinished event) {
        io.cucumber.plugin.event.Status status = event.getResult().getStatus();
        switch (status) {
            case PASSED:
                scenario.get().pass("Step passed");
                break;
            case FAILED:
                scenario.get().fail(event.getResult().getError());
                break;
            case SKIPPED:
                scenario.get().skip("Step skipped");
                break;
            default:
                scenario.get().skip("Step status unknown");
        }
    }
}