package week4day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);
		driver.findElement(By.xpath("(//div//div[@data-component-type='s-search-result'])[2]"));
		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[2]")).getText();
		System.out.println("The price of the first product is:" + price);
		String customerReview = driver.findElement(By.xpath("(//a//span[@class='a-size-base'])[2]")).getText();
		System.out.println("The total number of customer ratings are:" + customerReview);
		WebElement rating = driver.findElement(By.xpath("(//a//span[@class='a-icon-alt'])[2]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(rating).click().perform();
		Thread.sleep(1000);
		String fiveStarPrcnt=driver.findElement(By.xpath("(//a[contains(@title,'reviews have 5 stars')])[3]")).getText();
		System.out.println("Five start percentage is: "+fiveStarPrcnt);
		driver.findElement(By.xpath("(//h2[contains(@class,'a-size-mini')])[2]")).click();
		Set<String> winHand = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(winHand);
		driver.switchTo().window(list.get(1));
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/Amazon.png");
		FileUtils.copyFile(src, dst);
		Thread.sleep(1000);
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(3000);
		String cartSubTotal = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		System.out.println("The cart total is:"+cartSubTotal);
		if (cartSubTotal.contains(price)) {
			System.out.println("Price matches:" + cartSubTotal);
		} else
			System.out.println("Price does not match:" + cartSubTotal);
		driver.quit();
	}

}
