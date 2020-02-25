package amazonpopup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class LoginAmazon {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\P10408525.PDCDT01GY4ZVQ1\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		String url = "https://amazon.in/";
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Inspecting sign in option
		WebElement acctnlist = driver.findElement(By.xpath("//a[@id='nav-link-accountList']/span[1]"));

		Actions move2actnlist = new Actions(driver);
		move2actnlist.moveToElement(acctnlist).build().perform();

		/*
		 * Action mouseOverHome = move2actnlist.moveToElement(acctnlist).build();
		 * mouseOverHome.perform();
		 */

		// sign in
		driver.findElement(By.xpath("//*[@id='nav-flyout-ya-signin']/a/span")).click();

		driver.findElement(By.id("ap_email")).sendKeys("7058408881");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Muk$45la");
		driver.findElement(By.id("signInSubmit")).click();

		WebElement AfterloginList = driver.findElement(By.id("nav-link-accountList"));
		move2actnlist.moveToElement(AfterloginList).build().perform();

		// create a wish list option from dropdown
		driver.findElement(By.xpath("//span[contains(text(),'Create a Wish List')][@class='nav-text']")).click();
		
		

		// Clearing list Name in text Box "List name"
		WebElement Defaultlstname = driver.findElement(By.id("WLNEW_list_name"));
		Defaultlstname.clear();

		// inserted try catch as getting "Create a list option" at top right sometimes.
		/*
		 * try {
		 * 
		 * //Click on 'Create a List' at right top corner
		 * driver.findElement(By.xpath("//a[@id='createList']/..")).click(); } catch
		 * (Exception e) { System.out.println("Okay to go ahead"); }
		 */

		Defaultlstname.sendKeys("Electronics");

		driver.findElement(By.id("WLNEW_create")).click();

	}

}
