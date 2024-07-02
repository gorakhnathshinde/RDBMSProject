package webtestbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import static utility.Util.IMPLICITLY_WAIT;
import static utility.Util.PAGE_LOAD_TIME;

public class WebTestBase {

    public static WebDriver driver;
                                                 //declare variable
    public static Properties prop;    //access config file

   public WebTestBase() {
       prop = new Properties();            //initialize file
       FileInputStream fileInputStream = null;

       try {
           fileInputStream = new FileInputStream(System.getProperty("user.dir" + "/src/main/resources/properties/config.properties"));
       } catch (Exception e) {
           e.printStackTrace();
       }
       try {
           prop.load(fileInputStream);
       }
       catch(Exception e){
           e.printStackTrace();
       }
   }

   //Browser initialize in this method
   public void initialization(){
       String browserName = prop.getProperty("browser");

       if(browserName.equalsIgnoreCase("chrome")){
           System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir" + "/src/main/resources/driver/chromedriver.exe"));
           driver = new ChromeDriver();
       } else if (browserName.equalsIgnoreCase("firefox")){
           System.setProperty("webdriver.firefox.driver",System.getProperty("user.dir" + "/src/main/resources/driver/geckodriver.exe"));
           driver = new FirefoxDriver();
       } else if (browserName.equalsIgnoreCase("edge")){
           System.setProperty("webdriver.edge.driver",System.getProperty("user.dir" + "/src/main/resources/driver/edgedriver.exe"));
           driver = new EdgeDriver();
       }
       else{
           throw new RuntimeException("please select the correct browser name");
       }
       driver.get(prop.getProperty("url"));
       driver.manage().window().maximize();
       driver.manage().deleteAllCookies();
       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(IMPLICITLY_WAIT));                    //wait time frame
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PAGE_LOAD_TIME));
   }
}
