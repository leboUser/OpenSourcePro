import BankOP.Customer;
import SelenideConf.SelenideConf;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.*;

import static BankOP.BankOP.*;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith({ScreenShooterExtension.class})
public class Task2 extends Base {

    @BeforeEach
    void beforeTestRun(TestInfo testInfo){
        String TestSuite = testInfo.getTestClass().get().getCanonicalName();
        String testName = testInfo.getTestMethod().get().getName();
        reporter.testsuite(testName,TestSuite);
        SelenideLogger.addListener(testName, new AllureSelenide().screenshots(true));
        SelenideConf.selenideConf(getReportDirecty());

    }


    @AfterEach
  public  void afterEach(){

    }

    @AfterAll
   static void afterAll(){
        closeWindow();
        closeWebDriver();
        reporter.flush();
    }

    @Test
    @DisplayName("Test 1")
    void Test1()  {
        open("http://www.way2automation.com/angularjs-protractor/banking/#/login");
        customerLogin("1");
        DepsitAmount("1500");
        reporter.TestcompleteWithScreenShot("Step completed");
        OffLogin();
    }

    @Test
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
            customerLogin("1");
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

    }



}
