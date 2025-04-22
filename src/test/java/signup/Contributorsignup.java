package signup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Contributorsignup {
	public static void main(String[] args) throws Throwable, Throwable {
		FileInputStream fis=new FileInputStream("src/test/resources/Propertyfile.properties");
		Properties pro=new Properties();
		pro.load(fis);
	 	WebDriver driver=null;
		String BROWSER = pro.getProperty("browser");
	    String URL = pro.getProperty("url");
		String PHONE = pro.getProperty("phone");
		
		
		
		if(BROWSER.equalsIgnoreCase("CHROME")) {
			WebDriverManager.chromedriver().setup();
			 driver=new ChromeDriver();
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		else if(BROWSER.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			 driver=new EdgeDriver();
			 driver.manage().window().maximize();	
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
		}else {
			WebDriverManager.firefoxdriver().setup();
			 driver=new FirefoxDriver();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			 driver.manage().window().maximize();
		}
		Random ran=new Random();
		int rannum = ran.nextInt(1000);
		
		
        driver.findElement(By.xpath("//span[.='Sign up']")).click();
        driver.findElement(By.xpath("//span[.='Sign up as contributors']")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
       
        driver.findElement(By.xpath("//input[@placeholder='Enter phone number']")).sendKeys(PHONE);
        
        FileInputStream fis1=new FileInputStream("src/test/resources/exceldata.xlsx");
        Workbook book = WorkbookFactory.create(fis1);
        Sheet sheet = book.getSheet("contributor");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(1);
        String name = cell.getStringCellValue()+rannum;
        
        
       
      
        
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("password")).sendKeys("Test@123");
      //  driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
}

