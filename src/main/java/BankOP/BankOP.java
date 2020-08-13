package BankOP;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BankOP {

    static String  customerlogin ="//button[text()='Customer Login']";
    static String Offlogin = "//button[text()='Logout']";
    static String yourNamedropDown ="//select[@id='userSelect']";
    static String loginCustomer = "//button[text()='Login']";
    static  String despsit ="//button[contains(text(),'Deposit')]";
    static String welcomeText ="//strong[contains(text(),'Welcome')]";
    static String Transaction ="//button[contains(text(),'Transactions')]";
    static String withdraw ="//button[contains(text(),'Withdrawl')]";
    static String enterAmount ="//input[@type='number']";
    static String buttonDeposit = "//button[text()='Deposit']";
    static String buttonWithdraw = "//button[text()='Withdraw']";
    static String balance ="//strong[2]";
    static String textTransactionSuccessful ="//span[contains(text(),'Transaction successful')]";
    static String textDepositSuccessful ="//span[contains(text(),'Deposit Successful')]";
    static  String dateTime ="//a[@href='#']";
    static String endDateTransactionTable ="//input[@name='end']";
    static String TransactionTable ="//tbody";
    static String home="//button[text()='Home']";
    static String back ="//button[contains(text(),'Back')]";

    public static SelenideElement customerLogin(String user){
        $(new By.ByXPath(customerlogin)).click();
        $(new By.ByXPath(yourNamedropDown)).selectOptionByValue(String.valueOf(user));
        $(new By.ByXPath(loginCustomer)).click();
        return  $(new By.ByXPath(welcomeText)).shouldHave(text("Welcome"));
    }
    public static SelenideElement OffLogin(){
        $(new By.ByXPath(Offlogin)).click();
        $(new By.ByXPath(yourNamedropDown)).shouldBe(appear);
         $(new By.ByXPath(home)).click();
        return $(new By.ByXPath(customerlogin)).shouldBe(appear);
    }

    public static SelenideElement DepsitAmount(String amount){
        $(new By.ByXPath(despsit)).click();
        $(new By.ByXPath(enterAmount)).sendKeys(amount);
        $(new By.ByXPath(buttonDeposit)).click();
        return $(new By.ByXPath(textDepositSuccessful)).shouldBe(appear);
    }

    public static SelenideElement transactions(String amount){
        $(new By.ByXPath(Transaction)).click();
        $(new By.ByXPath(dateTime)).click();
        $$(new By.ByXPath(TransactionTable)).get(0).shouldHave(text(amount));
        $(new By.ByXPath(back)).click();
        return  $(new By.ByXPath(welcomeText)).shouldHave(text("Welcome"));
    }

    public static SelenideElement withDraw(String amount){
        $(new By.ByXPath(withdraw)).click();
        $(new By.ByXPath(enterAmount)).sendKeys(amount);
        $(new By.ByXPath(buttonWithdraw)).click();
        return $(new By.ByXPath(textTransactionSuccessful)).shouldHave(text("Transaction successful"));

    }









}
