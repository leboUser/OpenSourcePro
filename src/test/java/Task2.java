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
       // reporter.testsuite(testName,TestSuite);
       // SelenideLogger.addListener(testName, new AllureSelenide().screenshots(true));
        //SelenideConf.selenideConf(getReportDirecty());

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
        Selinum selinum = new Selinum("chrome",true);
        TestListener report = new TestListener(testInfo.getTestClass().get().getName(),testInfo.getTestMethod().get().getName());
        selinum.navigation("http://www.way2automation.com/angularjs-protractor/banking/#/login");
        BankOP bankOP = new BankOP();

        //Test Cases steps
        report.testStepPassOrFail(bankOP.customerLogin(1,selinum.driver()), "Able to login?");
        report.TestWithScreenShot(bankOP.depsitAmount("1500",selinum.driver()),selinum,"Able to deposit amount?");

        //close Logoff
        bankOP.OffLogin(selinum.driver());
        //flush report
        report.flush();
        selinum.shutdown();
    }

   /* @Test
    @DisplayName("Test 2")
    void Test2()  {
        open("http://www.way2automation.com/angularjs-protractor/banking/#/login");
       for(int x=1;x<5;x++){
        customerLogin(String.valueOf(x));
        DepsitAmount("1500");
        reporter.TestcompleteWithScreenShot("Step completed "+x);
        OffLogin();
       }
    }

        @Test
        @DisplayName("Test 3")
        void Test3()  {
            open("http://www.way2automation.com/angularjs-protractor/banking/#/login");
            customerLogin(1);
            DepsitAmount("1500");
            transactions("1500");
            withDraw("1500");
            reporter.TestcompleteWithScreenShot("Step completed");
            OffLogin();

        }
    @Test
    @DisplayName("Test 4")
    void Test4() throws IOException {
        open("http://www.way2automation.com/angularjs-protractor/banking/#/login");
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader location = new BufferedReader(
                new FileReader("src/main/resources/customer.json"));
        Customer customerObj = mapper.readValue(location, Customer.class);
        customerLogin(customerObj.getID());
        DepsitAmount(customerObj.getDeposited());
        transactions(customerObj.getDeposited());
        withDraw(customerObj.getWithdrawn());
        reporter.TestcompleteWithScreenShot("Step completed");
        OffLogin();

    }*/



}
