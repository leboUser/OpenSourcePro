package tools;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.screenshot;

import java.io.IOException;


public  class  TestListener   {

    private ExtentTest test;
    private ExtentTest node;
    private Report testsuites;
    private String testCase;

//nee fix
    public boolean testsuite(String testCases,String testSuite) {
        this.testCase = testCases;
        testsuites = new Report(testSuite+"/"+testCases);
        test = testsuites.report.createTest(testCase);

        return true;
    }
    public String getDic(){
        return testsuites.reportDirctory;
    }

    public void testcaseCreation(String testcaseName){
        test = testsuites.report.createTest(testcaseName);
    }


    public void testcomplete(String reasonP_F,boolean result){

        if(result){
            this.test.pass(reasonP_F);
            Assertions.assertTrue(true);
        }else{
            this.test.fail(reasonP_F);
            Assertions.assertTrue(false);
        }
    }

    public void testPassOrFail(String reasonP_F,boolean result){

        if(result){
            this.test.pass(reasonP_F);
            Assertions.assertTrue(true);
        }else{
            this.test.fail(reasonP_F);
            Assertions.assertTrue(false);
        }
    }

    public void TestcompleteWithScreenShot(String reasonP_F) {
        try {
            test.pass(reasonP_F, MediaEntityBuilder.createScreenCaptureFromPath(screenshot(reasonP_F)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testStep(String step,String description){
        this.node = test.createNode(step,description);
    }
    public void flush(){
        testsuites.report.flush();
    }

}