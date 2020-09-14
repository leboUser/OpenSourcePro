package Controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


 abstract public class Selinum  extends TestListener  {

    private WebDriver driver;


    public Selinum(String browsersType,boolean windowSize,String url,String testSuite,String testcase) throws Exception {
        super(testSuite,testcase);
        if (browsersType.toUpperCase().contentEquals("CHROME")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            if (windowSize) {
                this.driver.manage().window().maximize();
            }
            this.driver.navigate().to(url);
        }else if(browsersType.toUpperCase().contentEquals("FIREFOX")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
            if (windowSize) {
                this.driver.manage().window().maximize();
            }
            this.driver.navigate().to(url);
        }else if(browsersType.toUpperCase().contentEquals("EDGE")) {
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();
            if (windowSize) {
                this.driver.manage().window().maximize();
            }
            this.driver.navigate().to(url);
        }else {
            throw new Exception("unable to select browser");
        }

    }

    public void Webdriver(WebDriver driver){
        this.driver = driver;
    }
    public WebDriver driver(){
        return driver;
    }

    //Interacting Element
    private WebElement waitElement(By value){
                    try {
                            pause(1000);
                            WebDriverWait wait = new WebDriverWait(driver(), 1);
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(value));
                            WebElement element = driver.findElement(value);

                            moveElement(element );

                            return element;

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
            return this.driver.findElement(value);
    }

    private void moveElement(WebElement element){
        Actions action = new Actions(driver());
        action.moveToElement(element);
        action.perform();
    }


    protected void clickElement(By value ,boolean screenshot,String step)  {

        try {
            WebElement element;
            element = waitElement(value);
            element.click();

            if (screenshot) {
                TestWithScreenShot(true, driver(), step);
            } else {
                testPassOrFail(step, true,driver());
            }
        }catch (Exception e){
            e.getCause();
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage() , false,driver());
            }
        }
    }

     protected void selectElementByIndex(By value,int index,boolean screenshot,String step){
        try {

            Select element = new Select(waitElement(value));
            element.selectByIndex(index);
            if (screenshot) {
                TestWithScreenShot(true, driver(), step);
            } else {
                testPassOrFail(step, true,driver());
            }

        }catch (Exception e){
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
        }
    }

     protected void enterText(By value, String textToEnter,Boolean screenshot,String step)  {
        try {
                    WebElement element= waitElement(value);
                    element.sendKeys(textToEnter);
                    if (screenshot) {
                        TestWithScreenShot(true, driver(), step);
                    } else {
                        testPassOrFail(step, true,driver());
                    }

        }catch (Exception e){

            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
        }
    }


     protected void validateElementText(By value,String textToValidate,Boolean screenshot,String step) {
        try {


            WebElement element = waitElement(value);
            if (screenshot) {
                TestWithScreenShot(element.getText().equals(textToValidate), driver(), step);
            } else {
                testPassOrFail(step, element.getText().equals(textToValidate),driver());
            }



        }catch (Exception e){
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.toString());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
        }
    }

     protected String getTextFromElement(By value,boolean screenshot,String step) {
        try {

            WebElement element = waitElement(value);
           
            return element.getText();

        }catch (Exception e){
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
            return null;
        }
    }

     protected void GetlistElement(By value, String appear,Boolean screenshot,String step) {
        boolean result =false;
        try{

            waitElement(value);
            List<WebElement> elements = this.driver.findElements(value);
            for (WebElement elm:elements) {
               if( elm.getText().equals(appear)){
                   result =true;
                   if (screenshot) {
                       TestWithScreenShot(result, driver(), step);
                   } else {
                       testPassOrFail(step, result,driver());
                   }
               }
            }
        }catch(Exception e){
            if (screenshot) {
                TestWithScreenShot(false, driver(), e.getMessage());
            } else {
                testPassOrFail(e.getMessage(), false,driver());
            }
        }
    }





     protected void pause(int milliseconds){
        try{
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

     public boolean shutdown(){
        try {
            driver.close();
            driver.quit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            return false;
        }

    }


}
