package Controller;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;


public class TestListener extends Report  {

    private ExtentTest test;
    private ExtentTest node;
    private String testCase;

//nee fix
    public TestListener(String testCases,String testSuite) {
        super(testSuite+"\\"+testCases);
        this.testCase = testCases;
        test = report.createTest(testCase);
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

    public void TestWithScreenShot(boolean condition,Selinum selinum,String reasonP_F) {
        try {
            if(condition) {
                test.pass(reasonP_F,MediaEntityBuilder.createScreenCaptureFromPath(selinum.takeScreenshot(condition,reportDirctory)).build());
                Assertions.assertTrue(true);
            }else{
                MediaEntityBuilder.createScreenCaptureFromPath(selinum.takeScreenshot(condition,reportDirctory)).build();
                Assertions.assertTrue(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void testStepPassOrFail(boolean condition,String description){
        if(condition){
            test.pass(description);
        }else{
            test.fail(description);
        }
    }

}