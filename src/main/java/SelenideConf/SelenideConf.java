package SelenideConf;

import com.codeborne.selenide.Configuration;


public class SelenideConf  {

    public static void selenideConf(String reportDic){
        Configuration.startMaximized=true;
        Configuration.screenshots=true;
        Configuration.reportsFolder=reportDic;
        Configuration.fastSetValue=false;

    }
}
