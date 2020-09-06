package Controller;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


abstract class Report  {

    private static final String formatStr = "%n%-24s %-20s %-60s %-25s";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports report =  new ExtentReports();
    public String reportDirctory;
    public String testcaseFolder =null;


    Report(String testcaseFolder) {
        ReportTestate(testcaseFolder);
       // htmlReporter.config().setAutoCreateRelativePathMedia(true);
        report.attachReporter(this.htmlReporter);
    }

    public void ReportTestate(String testcaseFolder){
        this.testcaseFolder=testcaseFolder;
        htmlReporter =  new ExtentHtmlReporter( setReportDirectory(testcaseFolder) +"extend.html");
    }

    private static String getCurTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
        return format.format(date);
    }
    public String getReportDirectory() {
        return reportDirctory;
    }

    public String setReportDirectory(String testcaseFolder) {
        return reportDirctory = System.getProperty("user.dir") + "\\Reports\\" +  testcaseFolder+ "\\" + getCurTime()+"\\";
    }

    public void flush(){
        report.flush();
    }







}