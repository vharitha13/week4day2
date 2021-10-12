package week4day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	static int sum = 0;
	static int count = 0;

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.myntra.com/");
		WebElement men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();
		driver.findElement(By.xpath("(//ul//li//a[text()='Jackets'])[1]")).click();
		String noOfItems = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		System.out.println("The total number of items under Mens->Jackets are:" + noOfItems);
		List<WebElement> prices = driver.findElements(By.xpath("//span[@class='categories-num']"));
		for (int i = 0; i < prices.size(); i++) {
			String category = prices.get(i).getText();
			String catValues = category.replaceAll("\\D", "");
			sum = sum + Integer.parseInt(catValues);
		}
		System.out.println("Sum of categories is :" + sum);
		String stringSum = String.valueOf(sum);
		if (noOfItems.contains(stringSum)) {
			System.out.println("sum of categories matches with the jacket count");
		} else {
			System.out.println("Sum of category doesn't matches with the jacket count");
		}
		driver.findElement(By.xpath("//ul[@class='categories-list']//li")).click();
		driver.findElement(By.className("brand-more")).click();
		driver.findElement(By.xpath("//input[@value='Duke']/following::div")).click();
		driver.findElement(By.xpath("//div[contains(@class,'titleBar')]//span")).click();
		Thread.sleep(1000);
		List<String> list1 = new ArrayList<String>();
		List<WebElement> listOfItems = driver.findElements(By.xpath("//div[contains(@class,'productMetaInfo')]/h3"));
		Thread.sleep(2000);
		for (WebElement webElement : listOfItems) {
			list1.add(webElement.getText());
		}
		for (int i = 0; i < list1.size(); i++) {

			if (!list1.get(i).contains("Duke")) {
				System.out.println("The list of items are not part of the Duke brand");
			} else {
				count = count + 1;
				if (count == list1.size()) {
					System.out.println("The list of items are part of Duke brand:" + list1);
				}
			}
		}
		WebElement sort = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		builder.moveToElement(sort).perform();
		driver.findElement(By.xpath("//div[@class='sort-sortBy']//ul//li[3]")).click();
		String price = driver.findElement(By.xpath("(//div//div//span[@class='product-discountedPrice'])[1]"))
				.getText();
		System.out.println("The price of the first item is:" + price);
		driver.findElement(By.xpath("(//div[contains(@class,'productMetaInfo')])[1]")).click();
		Set<String> winHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(winHandles);
		driver.switchTo().window(list.get(1));
		Thread.sleep(1000);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/firstproduct.png");
		FileUtils.copyFile(src, dst);
		driver.findElement(By.xpath("//div[contains(@class,'wishlist')]")).click();
		driver.quit();
	}
}
