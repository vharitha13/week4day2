package week4day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.snapdeal.com/");
		WebElement categ = driver.findElement(By.xpath("(//li[contains(@navindex,'6')]//a//span)[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(categ).perform();
		driver.findElement(By.xpath("(//a//span[text()='Sports Shoes'])[1]")).click();
		String text = driver.findElement(By.xpath("//h1[@category='Sports Shoes']//span")).getText();
		System.out.println("The total number of items under Sports shoes are:" + text);
		driver.findElement(By.xpath("(//ul[contains(@class,'child-cat-wrapper')])[3]//li[1]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'sort-drop')]//i")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']//li[2]")).click();
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='lfloat marR10']//span[2]"));
		List<Integer> price = new ArrayList<Integer>();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			price.add(Integer.parseInt(list.get(i).getAttribute("data-price")));
		}
		List<Integer> sortedPrice = new ArrayList<Integer>(price);
		Collections.sort(sortedPrice);
		if (price.equals(sortedPrice)) {
			System.out.println("items displayed are sorted correctly");
		} else {
			System.out.println("items displayed are not sorted correctly");
		}
		WebElement fromValue = driver.findElement(By.name("fromVal"));
		fromValue.clear();
		fromValue.sendKeys("900");
		WebElement toValue = driver.findElement(By.name("toVal"));
		toValue.clear();
		toValue.sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@data-name='Color_s']//label[@for='Color_s-Blue']")).click();
		Thread.sleep(1000);
		String filter = driver.findElement(By.xpath("(//div[@class='filters'])[1]")).getText();
		if (filter.contains("900") && filter.contains("1200") && filter.contains("Blue")) {
			System.out.println("The filters are applied" + filter);
		} else
			System.out.println("Filters are not applied");
		WebElement firstProduct = driver.findElement(By.xpath("(//section[contains(@class,'dp-fired')]//div)[1]"));
		builder.moveToElement(firstProduct).perform();
		driver.findElement(By.xpath("(//div//div[contains(text(),'Quick View')])[1]")).click();
		Thread.sleep(1000);
		String price1 = driver.findElement(By.xpath("//div[@class='lfloat']//div[2]//span[1]")).getText();
		System.out.println("The price of the item is:" + price1);
		String discount = driver.findElement(By.xpath("//div[@class='lfloat']//div[2]//span[2]")).getText();
		System.out.println("The discount percentage is:" + discount);
		File scrnshot = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/shoes.png");
		FileUtils.copyFile(scrnshot, dst);
		driver.close();
	}
}
