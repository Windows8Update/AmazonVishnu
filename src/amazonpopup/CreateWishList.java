package amazonpopup;

import org.testng.annotations.Test;

import com.pagesobject.WishListPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CreateWishList
{
	static String url = "https://amazon.in/";
	WebDriver driver;

	@BeforeClass
	public void beforeClass()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\P10408525.PDCDT01GY4ZVQ1\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement AfterloginList = driver.findElement(By.id("nav-link-accountList")); //Sign in Hover
		AfterloginList.click();
		try {
			AfterloginList.click();
			AfterloginList.click();
		} catch (Exception e) {
			// TODO: handle exception
		}		
		driver.findElement(By.id("ap_email")).sendKeys("7058408881");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Muk$45la");
		driver.findElement(By.id("signInSubmit")).click();

		  String captchaText = "To better protect your account, please re-enter your password and then enter the characters as they are shown in the image below.";
		  try {
			  
			  WebElement CaptchaHeadTxt = driver.findElement(By.xpath("//span[@class= 'a-list-item']")); // Captcha msg
			
			  
		   while(captchaText.equals(CaptchaHeadTxt.getText())) { 
		    
		      //logger.trace("We are on captcha page");

		      // prompt user to enter captcha
		      String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
		      WebElement CaptchaTextbox = driver.findElement(By.id("auth-captcha-guess"));
		      CaptchaTextbox.sendKeys(captchaVal1);
		      

		      
		      
		      WebElement Password = driver.findElement(By.id("ap_password"));
		      Password.sendKeys("Muk$45la");
		      WebElement Loginbutton = driver.findElement(By.id("signInSubmit"));
		      Loginbutton.click();
		    		    }
		  } catch (Exception e) {
		   System.out.println("Captcha didn't appear..");
		  }
		
	}

	@Test(priority = 1) // -------------------------------------- Default Shopping List
	public void FirstTest() throws InterruptedException
	{
		
		/*
		 * WP = new WishListPage(driver);
		WP.HoverAndClickOnCreateList();
		 * 
		 */
		Actions move2actnlist = new Actions(driver);
		WebElement AfterloginList = driver.findElement(By.id("nav-link-accountList")); // Hello Test
		//Thread.sleep(3000);
		
		move2actnlist.moveToElement(AfterloginList).build().perform(); //hover to get "Create a list" option
		
		if(driver.findElement(By.xpath("//span[text()='Create a Wish List' and @class='nav-text']")).isDisplayed()) {
		}else {
		try {
			AfterloginList.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Create a Wish List' and @class='nav-text']")).click(); // clicking "create list option wish

		try
		{
			driver.findElement(By.xpath("//span[@id='createList']/span/a")).click(); // clicking "create list" option when there is no list
		} 
		catch (Exception e) 
		{
			System.out.println("Create list background button did not appear");
		}

		Thread.sleep(2000);
		WebElement Createbtn = driver.findElement(By.xpath("//span[@class='a-button a-button-primary']")); // clicking "Create a List" option
		Createbtn.click();

		String explistname1 = "Private";
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class = 'a-color-base hover-as-link a-text-normal' and contains(text(),'Private')]")).getText(), explistname1);

	}

	@Test(dependsOnMethods = "FirstTest") // --------------------------------------Electronics
	public void SecondTest() throws InterruptedException
	{
		WebElement CreateList = driver.findElement(By.xpath("//a[@id = 'createList' and @class ='a-link-normal createListTrigger' ]")); // create list at top right corner
		CreateList.click();
		WebElement Defaultlstname = driver.findElement(By.xpath("//input[@type='text' and @value = 'Shopping List 1' ]")); // list name
		Defaultlstname.clear();
		Defaultlstname.sendKeys("Electronics");
		driver.findElement(By.xpath("//input[@class = 'a-button-input a-declarative' and @type= 'submit' and @aria-labelledby = 'WLNEW_privacy_public-announce']")).click(); // Public Button in Create a List
		driver.findElement(By.xpath("//span[@data-action='reg-create-submit']//input[@type='submit']")).click();
		String explistname2 = "Public";
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Electronics')]/ancestor::a//div//span[contains(text(),'Public')]")).getText(), explistname2);
	}

	@Test(dependsOnMethods = "SecondTest") // -------------------------------------------Grocery
	public void ThirdTest() throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement CreateList1 = driver.findElement(By.xpath("//a[@id = 'createList' and @class ='a-link-normal createListTrigger' ]"));
		CreateList1.click();
		WebElement Defaultlstname2 = driver.findElement(By.xpath("//input[@type='text' and @value = 'Shopping List 1' ]"));
		Defaultlstname2.clear();
		Defaultlstname2.sendKeys("Grocery");
		driver.findElement(By.xpath("//span[@data-action='reg-create-submit']//input[@type='submit']")).click();
		String explistname3 = "Private";
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Grocery')]/ancestor::a//div//span[contains(text(),'Private')]")).getText(), explistname3);
	}

	@Test(dependsOnMethods = "ThirdTest") // ------------------------------------------------Phones
	public void FourthTest() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 11 pro max 256gb" + Keys.ENTER);
		driver.findElement(By.xpath("//span[text() = 'Apple iPhone 11 Pro Max (512GB) - Space Grey']")).click();
		Thread.sleep(2000);
		String MainWindow = driver.getWindowHandle();	// switching to another tab
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext())
		{
		String ChildWindow = i1.next();

		if (!MainWindow.equalsIgnoreCase(ChildWindow))
			{
			driver.switchTo().window(ChildWindow);

			driver.findElement(By.xpath("//input[@id = 'add-to-wishlist-button']")).click();
			driver.findElement(By.xpath("//a[@id = 'atwl-dd-create-list']")).click();
			WebElement Defaultlstname3 = driver.findElement(By.xpath("//input[@type='text' and @value = 'Shopping List 1' ]"));
			Defaultlstname3.clear();
			Defaultlstname3.sendKeys("Phones");

			driver.findElement(By.xpath("//span[@data-action='reg-create-submit']//input[@type='submit']")).click();
			driver.findElement(By.xpath("//button[@type='button' and text()='Continue shopping']")).click();
			driver.close();
			}
		}
		
		driver.switchTo().window(MainWindow);
		Actions move3actnlist = new Actions(driver);
		WebElement AfterPhoneList = driver.findElement(By.id("nav-link-accountList"));
		Thread.sleep(2000);
		move3actnlist.moveToElement(AfterPhoneList).build().perform();
		
		if(driver.findElement(By.xpath("//span[@class='nav-text' and text()='Phones']")).isDisplayed()) {
		}else {
		try {
			Thread.sleep(2000);
			AfterPhoneList.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		
		Thread.sleep(2000);
		//AfterPhoneList.click();
		driver.findElement(By.xpath("//span[@class='nav-text' and text()='Phones']")).click(); // clicking Phones after hover
		String explistname4 = "Private";
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Phones')]/ancestor::a//div//span[contains(text(),'Private')]")).getText(), explistname4);
	}

	@Test(dependsOnMethods = "FourthTest") // ---------------------------------------------------Phones2
	public void FifthTest() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 11 pro max 256gb" + Keys.ENTER);
		driver.findElement(By.xpath("//span[text() = 'Apple iPhone 11 Pro Max (512GB) - Space Grey']")).click();
		String MainWindow1 = driver.getWindowHandle();	// switching to another tab
		Set<String> s2 = driver.getWindowHandles();
		Iterator<String> i2 = s2.iterator();
		Thread.sleep(3000);
		
		while (i2.hasNext())
		{
			String ChildWindow1 = i2.next();
			if (!MainWindow1.equalsIgnoreCase(ChildWindow1))
			{
				driver.switchTo().window(ChildWindow1);
				driver.findElement(By.xpath("//input[@id = 'add-to-wishlist-button']")).click();
				driver.findElement(By.xpath("//a[@id = 'atwl-dd-create-list']")).click();
				WebElement Defaultlstname4 = driver.findElement(By.xpath("//input[@type='text' and @value = 'Shopping List 1' ]"));
				Defaultlstname4.clear();
				Defaultlstname4.sendKeys("Phones2");
				driver.findElement(By.xpath("//input[@class = 'a-button-input a-declarative' and @type= 'submit' and @aria-labelledby = 'WLNEW_privacy_public-announce']")).click(); // Public Button in Create a List
				driver.findElement(By.xpath("//span[@data-action='reg-create-submit']//input[@type='submit']")).click(); // clicking on Create List on Popup Box
				driver.findElement(By.xpath("//button[@type='button' and text()='Continue shopping']")).click();
				driver.close();
			}

		}

		driver.switchTo().window(MainWindow1);
		Actions move5actnlist = new Actions(driver);
		WebElement AfterPhone2List = driver.findElement(By.id("nav-link-accountList"));
		Thread.sleep(2000);
		move5actnlist.moveToElement(AfterPhone2List).build().perform();

		if(driver.findElement(By.xpath("//span[@class='nav-text' and text()='Phones2']")).isDisplayed()) {
		}else {
		try {
			Thread.sleep(2000);
			AfterPhone2List.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		Thread.sleep(2000);
		//AfterPhone2List.click();
		driver.findElement(By.xpath("//span[@class='nav-text' and text()='Phones2']")).click(); // clicking Phones after hover
		String explistname5 = "Public";
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Phones2')]/ancestor::a//div//span[contains(text(),'Public')]")).getText(), explistname5);
	
	}

	@BeforeMethod
	public void beforeMethod()
	{

	}

	@AfterMethod
	public void afterMethod() throws InterruptedException
	{

	}

	@AfterClass
	public void afterClass() throws InterruptedException
	{
		try 
		{ 
		while (driver.findElements(By.xpath("//*[@id='overflow-menu-popover-trigger']/div[2]")).size() > 0)
			{
				Thread.sleep(2000);
				Actions moredropdown = new Actions(driver);
				WebElement MoreOption = driver.findElement(By.xpath("//*[@id=\"overflow-menu-popover-trigger\"]/div[2]")); // More Element
				moredropdown.moveToElement(MoreOption).build().perform();
				driver.findElement(By.xpath("//a[@id = 'editYourList']")).click(); // manage list element
				WebElement Element = driver.findElement(By.xpath("//*[@id=\"list-settings-container\"]/span/span/span/input"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", Element);
				Element.click();
				Thread.sleep(2000);
				WebElement ElementYESbutton = driver.findElement(By.xpath("//input[@name='submit.save' and @type=\"submit\"]"));
				JavascriptExecutor js1 = (JavascriptExecutor) driver;				
				js1.executeScript("arguments[0].click();", ElementYESbutton);
				//System.out.println("CLicking on Delete button");
			}
		} 
		catch (Exception e)	// switching to another tab
		{ 
		}
		driver.quit();
	}

}
