import Controller.Selinum;
import Controller.TestListener;
import Model.BankOP;
import Model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.*;
import java.util.HashMap;

import static Model.BankOP.*;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith({ScreenShooterExtension.class})
public class Task2  {

    @BeforeEach
    void beforeTestRun(TestInfo testInfo){
        String TestSuite = testInfo.getTestClass().get().getCanonicalName();
        String testName = testInfo.getTestMethod().get().getName();
    }


    @AfterEach
  public  void afterEach(){

    }

    @AfterAll
   static void afterAll(){
    }

    @Test
    @DisplayName("Test 1")
    void Test1(TestInfo testInfo) throws Exception {
        BankOP bankOP = new BankOP("chrome",true);
        TestListener report = new TestListener(testInfo.getTestClass().get().getName(),testInfo.getTestMethod().get().getName());
        bankOP.navigation("http://www.way2automation.com/angularjs-protractor/banking/#/login");


        //Test Cases steps
        report.testStepPassOrFail(bankOP.customerLogin(1), "Able to login?");
        report.TestWithScreenShot(bankOP.depsitAmount("1500"),bankOP,"Able to deposit amount?");

        //close Logoff
        bankOP.OffLogin();
        //flush report
        report.flush();
        bankOP.shutdown();
    }

    @Test
    @DisplayName("Test 2")
    void Test2(TestInfo testInfo) throws Exception {
        TestListener report = new TestListener(testInfo.getTestClass().get().getName(),testInfo.getTestMethod().get().getName());
        BankOP bankOP = new BankOP("chrome",true);
        bankOP.navigation("http://www.way2automation.com/angularjs-protractor/banking/#/login");

       for(int x=1;x<5;x++){
           bankOP.customerLogin(x);
           report.TestWithScreenShot(bankOP.depsitAmount("1500"),bankOP,"Step completed "+x);
           bankOP.OffLogin();
       }
       bankOP.shutdown();
       report.flush();
    }

        @Test
        @DisplayName("Test 3")
        void Test3(TestInfo testInfo)throws Exception  {
            TestListener report = new TestListener(testInfo.getTestClass().get().getName(),testInfo.getTestMethod().get().getName());
            BankOP bankOP = new BankOP("chrome",true);
            bankOP.navigation("http://www.way2automation.com/angularjs-protractor/banking/#/login");
            bankOP.customerLogin(1);
            bankOP.depsitAmount("1500");
            bankOP.transactions("1500");
            report.TestWithScreenShot(bankOP.withDraw("1500"),bankOP,"Step completed");
            bankOP.OffLogin();
        }

    @Test
    @DisplayName("Test 4")
    void Test4(TestInfo testInfo) throws Exception {
        TestListener report = new TestListener(testInfo.getTestClass().get().getName(),testInfo.getTestMethod().get().getName());
        BankOP bankOP = new BankOP("chrome",true);
        bankOP.navigation("http://www.way2automation.com/angularjs-protractor/banking/#/login");
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader location = new BufferedReader(
                new FileReader("src/main/resources/customer.json"));
        Customer customerObj = mapper.readValue(location, Customer.class);
        bankOP.customerLogin(Integer.parseInt(customerObj.getID()));
        bankOP.depsitAmount(customerObj.getDeposited());
        bankOP.transactions(customerObj.getDeposited());

        report.TestWithScreenShot(bankOP.withDraw(customerObj.getWithdrawn()),bankOP,"Step completed");
        bankOP.OffLogin();

    }



}
