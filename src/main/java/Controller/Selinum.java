package Controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


 abstract public class Selinum   {

    private WebDriver driver;
    private int screenCounter = 0;

    public Selinum(String browsertype,boolean windowsize) throws Exception {
        switch (browsertype.toUpperCase()){
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                this.driver =new ChromeDriver();
                if(windowsize){this.driver.manage().window().maximize();}
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                this.driver =new FirefoxDriver();
                if(windowsize){this.driver.manage().window().maximize();}
                break;
            default:
                throw new Exception("unable to select browser") ;

        }
    }

   public  Selinum(){
    }


    public boolean navigation(String url){
        try {

            this.driver.navigate().to(url);

            return true;

        }catch (Exception e){
            return false;
        }
    }
    public void Webdriver(WebDriver driver){
        this.driver = driver;
    }
    public WebDriver driver(){
        return driver;
    }

    //Interacting Element
    public boolean waitElement(By element){
        boolean found = false;
        int counter = 0;

        try {
                        while(!found && counter < 30){

                            WebDriverWait wait = new WebDriverWait(this.driver, 1);
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
                            found = true;
                            counter ++;
                            pause(1000);
                            if(found){
                                return true;
                            }
                        }


            throw new Exception("unable to find Element "+element.toString()) ;
        }
        catch (Exception e){
         e.printStackTrace( );
            return false;
            }
    }

    public boolean clickElement(By value){
        try {
            WebElement element ;
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            waitElement(value);
            wait.until(ExpectedConditions.elementToBeClickable(value));
            element = this.driver.findElement(value);
            element.click();

            return true;
        }catch (Exception e){
            e.getCause();
            return false;
        }
    }

    public boolean selectElementByIndex(By value,int index){
        try {

            waitElement(value);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            Select element ;

                    wait.until(ExpectedConditions.elementToBeClickable(value));
                    element = new Select(this.driver.findElement(value));
                    element.selectByIndex(index);


            return true;

        }catch (Exception e){
            e.getCause();
            return false;
        }
    }

    public boolean enterText(By value, String textToEnter){
        try {

                    waitElement(value);
                    WebDriverWait wait = new WebDriverWait(this.driver,1);
                    WebElement element;
                    wait.until(ExpectedConditions.elementToBeClickable(value));
                    element = this.driver.findElement(value);
                    element.sendKeys(textToEnter);

            return true;

        }catch (Exception e){
            e.getCause();
            return false;
        }
    }


    public boolean validateElementText(By value,String textToValidate){
        try {

            waitElement(value);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            WebElement element ;
                    wait.until(ExpectedConditions.elementToBeClickable(value));
                    element = this.driver.findElement(value);

            return element.getText().equals(textToValidate);

        }catch (Exception e){
            return false;
        }
    }

  public String getTextFromElement(By value){
        try {
            waitElement(value);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            WebElement element;
                    wait.until(ExpectedConditions.elementToBeClickable(value));
                    element = this.driver.findElement(value);
           
            return element.getText();

        }catch (Exception e){
            return "";
        }
    }

    public Boolean GetlistElement(By value, String appear){
        boolean result =false;
        try{

            waitElement(value);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            List<WebElement> elements;
                elements = this.driver.findElements(value);
            for (WebElement elm:elements) {
               if( elm.getText().equals(appear)){
                   result =true;
               }
            }
        }catch(Exception e){
            return false;
        }
        return result;
    }

    public String takeScreenshot(Boolean status, String getReportDirectory) throws IOException {
        screenCounter++;
        String imagePath =getReportDirectory;
        String relativePath = "Screenshots\\";

        new File(imagePath + relativePath).mkdirs();

        relativePath=relativePath+screenCounter + "_";
        if (status) {
            relativePath=relativePath+"PASSED";
        } else {
            relativePath = relativePath+"FAILED";
        }
        relativePath=relativePath+".png";

        imagePath=imagePath+relativePath;
        File screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotBase64,new File(imagePath));

        return imagePath;

    }



    public void pause(int milliseconds){
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
