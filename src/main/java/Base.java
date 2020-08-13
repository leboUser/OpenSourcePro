import SelenideConf.SelenideConf;
import com.codeborne.selenide.Configuration;
import tools.APItool;
import tools.Report;
import tools.TestListener;

import static com.codeborne.selenide.Configuration.*;

public class Base  {


    public static APItool APItool = new APItool();
    public static TestListener reporter = new TestListener();
    public static SelenideConf selini = new SelenideConf();


    //Please use this Directory  to store screenshot
    public String getReportDirecty(){
        return reporter.getDic();
    }

}
